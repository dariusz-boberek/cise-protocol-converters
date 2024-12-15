package eu.cise.ivef;

import eu.cise.converters.exceptions.ConversionException;
import eu.cise.converters.utils.XMLDataCustomComparisonStrategy;
import eu.cise.datamodel.v1.entity.cargo.CargoType;
import eu.cise.datamodel.v1.entity.event.Event;
import eu.cise.datamodel.v1.entity.event.LocationRoleInEventType;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.location.Location;
import eu.cise.datamodel.v1.entity.location.PortLocation;
import eu.cise.datamodel.v1.entity.metadata.Metadata;
import eu.cise.datamodel.v1.entity.movement.Movement;
import eu.cise.datamodel.v1.entity.movement.MovementType;
import eu.cise.datamodel.v1.entity.object.Objet;
import eu.cise.datamodel.v1.entity.period.Period;
import eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.datamodel.v1.entity.vessel.VesselType;
import eu.cise.ivef.generated.MSGVesselData;
import eu.cise.ivef.generated.Pos;
import eu.cise.ivef.generated.PosReport;
import eu.cise.ivef.generated.StaticData;
import eu.cise.ivef.generated.TaggedItem;
import eu.cise.ivef.generated.VesselData;
import eu.cise.ivef.generated.Voyage;
import eu.cise.ivef.translators.VesselDataTranslator;
import eu.cise.ivef.translators.mappers.CargoTypeMapping;
import eu.cise.ivef.translators.mappers.ImmutableBidiMultiMap;
import eu.cise.ivef.translators.mappers.NavStatusMapping;
import eu.cise.ivef.translators.mappers.SensorTypeMapping;
import eu.cise.ivef.translators.mappers.ShipTypeMapping;
import eu.cise.ivef.translators.utils.BigNumberUtil;
import eu.cise.ivef.translators.utils.SpeedConverter;
import eu.cise.servicemodel.v1.message.Message;
import eu.cise.servicemodel.v1.message.PullRequest;
import eu.cise.servicemodel.v1.message.Push;
import eu.cise.servicemodel.v1.message.XmlEntityPayload;
import eu.eucise.xml.DefaultXmlMapper;
import eu.eucise.xml.XmlMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.xml.sax.SAXException;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;

import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eu.cise.converters.utils.XMLDataUtils.EQUALITY_TOLERANCE;
import static eu.cise.converters.utils.XMLDataUtils.getXMLsDifference;
import static eu.cise.converters.utils.XMLDataUtils.readResource;
import static eu.cise.converters.utils.XMLDataUtils.validateAgainstXsd;
import static eu.cise.ivef.Constants.IVEF_AIS_SHIP_TYPE_PROPERTY_NAME;
import static eu.cise.ivef.Constants.IVEF_ATTENTION_LEVEL_PROPERTY_NAME;
import static eu.cise.ivef.Constants.IVEF_CREATION_DATE_PROPERTY_NAME;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_LOST_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_RATEOFTURN_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_SOURCE_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_OBJECTTYPE_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCENAME_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCE_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_TAGGEDITEM_AIS_SHIP_TYPE_KEY;
import static eu.cise.ivef.ConversionUtils.getValueFromCiseMetadata;
import static eu.cise.ivef.ConversionUtils.getValueFromTaggedItem;
import static eu.cise.ivef.translators.VesselDataTranslator.convertNationalitiesCISEtoIVEF;
import static eu.cise.ivef.translators.utils.BigNumberUtil.bigIntegerToString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.emptyIterable;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


class IVEFConverterTest {

    //INFO: this exclusion may need to be filled out:
    public static final List<String> CISE_EXCEPTIONS = Arrays.asList();

    //TODO: this exclusion need to be revisited: TaggedItem - in XMLs it compares lists in same (naive) order, if one of XML have different order or extra rows XML will not be matched.
    public static final ComparisonResult SKIPP_COMPARISION = null;

    //INFO: IVEF_EXCEPTIONS contains XPaths in format: "/MSG_VesselData/Body/VesselData/Voyage/@ETA".
    //INFO: Impl of eu.cise.converters.utils.XMLDataUtils.isComparisonInExceptionsList is having all taggedItems except of:
    //  -  taggItem carrying "creationDate" turned off
    //  -  taggItem carrying "AIS_SHIP_TYPE" turned off
    private static final Logger logger = LoggerFactory.getLogger(IVEFConverterTest.class);
    private static final List<String> IVEF_EXCEPTIONS = new ArrayList();
    private static XmlMapper xmlMapper;
    private static NavStatusMapping navStatusMapping = new NavStatusMapping();
    private static SensorTypeMapping sensorTypeMapping = new SensorTypeMapping();
    //    private static TypeMapper<CargoType, BigInteger> cargoTypeMapper = new TypeMapper<>(CargoTypeEnum.class);
    private static CargoTypeMapping cargoTypeMapping = new CargoTypeMapping();
    private static ShipTypeMapping shipTypeMapping = new ShipTypeMapping();
    private boolean shouldCompareNextNode = false;
    private final XMLDataCustomComparisonStrategy customStrategy = comparison -> {
        Comparison.Detail controlDetails = comparison.getControlDetails();
        Comparison.Detail testDetails = comparison.getTestDetails();
        if (controlDetails == null || testDetails == null) {
            return ComparisonResult.DIFFERENT;
        }
        if (comparison.getType() == ComparisonType.ATTR_VALUE) {
            return handleAttributeComparison(controlDetails, testDetails);
        } else if (isKeyComparison(controlDetails, testDetails)) {
            return handleKeyComparison(controlDetails, testDetails);
        } else if (isNextNodeComparison(controlDetails, testDetails)) {
            return handleNextNodeComparison(controlDetails, testDetails);
        }
        return SKIPP_COMPARISION;
    };
    private Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair;

    @BeforeAll
    public static void beforeAll() {
        xmlMapper = new DefaultXmlMapper();
    }

    private static void checkIVEFVoyageDestinationVsCISEVoyageMovementPortLocation(Vessel ciseVessel, VesselData ivefVessel) {
        // get port location names from CISE Vessel
        Set<String> portLocations = ciseVessel.getInvolvedEventRels().stream()
                .map(Objet.InvolvedEventRel::getEvent)
                .filter(event -> event instanceof Movement)
                .map(event -> event.getLocationRels().stream().findFirst().get())
                .filter(locationRel -> locationRel.getLocationRole() == LocationRoleInEventType.END_PLACE)
                .map(Event.LocationRel::getLocation)
                .filter(location -> location instanceof PortLocation)
                .map(portLocation -> ((PortLocation) portLocation).getPortName())
                .collect(Collectors.toSet());
        // get destination Voyages from IVEF vessel
        Set<String> ivefDestinations = ivefVessel.getVoyage().stream().map(voyage -> voyage.getDestination()).collect(Collectors.toSet());
        // compare the two sets
        assertEquals(portLocations, ivefDestinations);
//                .findAny().or(fail("No PortLocation found!")).stream()
//                .forEach(location -> assertEquals(ivefVessel.getVoyage().get(0).getDestination(), ((PortLocation) location).getPortName()));
    }

    private ComparisonResult handleNextNodeComparison(Comparison.Detail controlDetails, Comparison.Detail testDetails) {
        shouldCompareNextNode = false;
        if (compareXMLValues(controlDetails.getTarget().getNodeValue(), testDetails.getTarget().getNodeValue())) {
            return ComparisonResult.SIMILAR;
        } else {
            return ComparisonResult.DIFFERENT;
        }
    }

    private boolean isNextNodeComparison(Comparison.Detail controlDetails, Comparison.Detail testDetails) {
        if (!shouldCompareNextNode) {
            return false;
        }
        return isDetailComparisonValid(controlDetails) || isDetailComparisonValid(testDetails);
    }

    private boolean isDetailComparisonValid(Comparison.Detail detail) {
        return detail.getXPath() != null && detail.getXPath().endsWith("/@Value") && detail.getTarget() != null && detail.getTarget().getNodeValue() != null;
    }

    private ComparisonResult handleKeyComparison(Comparison.Detail controlDetails, Comparison.Detail testDetails) {
        Attr controlAttr = (Attr) controlDetails.getTarget();
        Attr testAttr = (Attr) testDetails.getTarget();

        // section for IVEF_ATTENTION_LEVEL_PROPERTY_NAME
        if (IVEF_ATTENTION_LEVEL_PROPERTY_NAME.equals(controlAttr.getValue()) || IVEF_ATTENTION_LEVEL_PROPERTY_NAME.equals(testAttr.getValue())) {
            return ComparisonResult.SIMILAR;
        }

        // section for IVEF_CREATION_DATE_PROPERTY_NAME
        else if (IVEF_CREATION_DATE_PROPERTY_NAME.equals(controlAttr.getValue()) && IVEF_CREATION_DATE_PROPERTY_NAME.equals(testAttr.getValue())) {
            shouldCompareNextNode = true;
            return ComparisonResult.SIMILAR;
        }

        // section for IVEF_AIS_SHIP_TYPE_PROPERTY_NAME
        else if (IVEF_AIS_SHIP_TYPE_PROPERTY_NAME.equals(controlAttr.getValue()) && IVEF_AIS_SHIP_TYPE_PROPERTY_NAME.equals(testAttr.getValue())) {
            shouldCompareNextNode = true;
            return ComparisonResult.SIMILAR;
        }

        // For values not covered in the sections above.
        else if (!IVEF_ATTENTION_LEVEL_PROPERTY_NAME.equals(controlAttr.getValue()) && !IVEF_CREATION_DATE_PROPERTY_NAME.equals(controlAttr.getValue()) && !IVEF_AIS_SHIP_TYPE_PROPERTY_NAME.equals(controlAttr.getValue())) {
            return SKIPP_COMPARISION;
        }

        return ComparisonResult.DIFFERENT;
    }

    private boolean isKeyComparison(Comparison.Detail controlDetails, Comparison.Detail testDetails) {
        return (controlDetails.getXPath() != null && controlDetails.getXPath().endsWith("/@Key") && controlDetails.getTarget() instanceof Attr) || (testDetails.getXPath() != null && testDetails.getXPath().endsWith("/@Key") && testDetails.getTarget() instanceof Attr);
    }

    private ComparisonResult handleAttributeComparison(Comparison.Detail controlDetails, Comparison.Detail testDetails) {
        if ("Breadth".equals(controlDetails.getTarget().getLocalName()) || "Breadth".equals(testDetails.getTarget().getLocalName())) {
            try {
                int controlInt = (int) Math.ceil(Double.parseDouble(controlDetails.getTarget().getNodeValue()));
                int testInt = (int) Math.ceil(Double.parseDouble(testDetails.getTarget().getNodeValue()));
                if (controlInt == testInt) {
                    return ComparisonResult.SIMILAR;
                }
            } catch (NumberFormatException ex) {
                return ComparisonResult.DIFFERENT;
            }
        }
        return SKIPP_COMPARISION;
    }

    @Test
    public void it_converts_single_minimal_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_mini.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverterImpl converter = new IVEFConverterImpl();
        XmlEntityPayload xmlEntityPayload = (XmlEntityPayload) pushMessage.getPayload();
        Vessel ciseVessel = (Vessel) xmlEntityPayload.getAnies().get(0);

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        List<VesselData> vesselDataList = ivefMessage.getBody().getVesselData();
        assertEquals(1, vesselDataList.size());
        StaticData staticData = vesselDataList.get(0).getStaticData().get(0);
        assertNotNull(staticData.getIMO());
        assertEquals(ciseVessel.getIMONumber(), staticData.getIMO().intValue());
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));

    }

    @Test
    public void it_converts_multipleNationalities_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_multipleNationalities.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);

        //when
        IVEFConverterImpl converter = new IVEFConverterImpl();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_many_minimal_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_manyVessels_mini.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);

        //when
        IVEFConverterImpl converter = new IVEFConverterImpl();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_four_rich_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_fourVessel_rich.xml"); // used also to test ShipTypes
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        assertNotNull(pushMessage);
        IVEFConverterImpl converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_CISE_Vessel_to_IVEF_withNotMapped_CargoType_Value() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_Vessel_with_not_mapped_CargoType.xml"); // used also to test ShipTypes
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverterImpl converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertTrue(ivefMessage.getBody() != null &&
                ivefMessage.getBody().getVesselData() != null &&
                !ivefMessage.getBody().getVesselData().isEmpty() &&
                ivefMessage.getBody().getVesselData().get(0).getVoyage() != null &&
                !ivefMessage.getBody().getVesselData().get(0).getVoyage().isEmpty() &&
                ivefMessage.getBody().getVesselData().get(0).getVoyage().get(0).getCargoType() != null &&
                ivefMessage.getBody().getVesselData().get(0).getVoyage().get(0).getCargoType().equals(cargoTypeMapping.getDefaultIVEFValue()) &&
                ivefMessage.getBody().getVesselData().get(0).getTaggedItem() != null &&
                !ivefMessage.getBody().getVesselData().get(0).getTaggedItem().isEmpty() &&
                ivefMessage.getBody().getVesselData().get(0).getTaggedItem().get(0).getKey().equals(cargoTypeMapping.getIVEFTaggedItemKey()) &&
                ivefMessage.getBody().getVesselData().get(0).getTaggedItem().get(0).getValue().equals(CargoType.MOBILE_SELF_PROPELLED_UNITS.value()));
    }


    @Test
    public void it_converts_Push_oneVessel_AISShipType_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_AISShipType.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        assertNotNull(pushMessage);
        IVEFConverterImpl converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }


    @Test
    public void it_converts_multiple_rich_Cise_Vessel_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        String ciseXML = readResource("cisemessages/PullRequest_multipleVessels_rich.xml");
        PullRequest pullRequest = xmlMapper.fromXML(ciseXML);

        IVEFConverterImpl converter = new IVEFConverterImpl();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pullRequest);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        assertNotNull(ivefMessage);
        assertMessagesEqual(pullRequest, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_oneVessel_mini_from_IVEFMessage_to_CiseMessage() throws IOException, URISyntaxException, JAXBException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_mini.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_oneVessel_with_multiple_country_flags_from_IVEFMessage_to_CiseMessage() throws IOException, URISyntaxException, JAXBException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_countryFlagCase.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_oneVessel_with_multiple_country_flags_from_IVEFMessage_to_CiseMessage_having_placeholder_country() throws IOException, URISyntaxException, JAXBException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_countryFlagCase_withGenericPlaceholder.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_oneVessel_with_multiple_country_flags_from_IVEFMessage_to_CiseMessage_having_null_value_in_country() throws IOException, URISyntaxException, JAXBException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_countryFlagCase_nullValue.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }


    @Test
    public void it_converts_oneVessel_mini_with_CreationDate_from_IVEFMessage_to_CiseMessage() throws IOException, URISyntaxException, JAXBException {

        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_mini_with_CreationDate.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    //TODO: check if still there are missing mapping fields (compare against: it_performs_round_trip_conversion_for_IVEF_oneVessel_rich_correctedBasedOnXSD_xml)
    @Test
    public void it_converts_IVEF_oneVessel_rich_correctedBasedOnXSD_from_IVEFMessage_to_CiseMessage() throws IOException, URISyntaxException, JAXBException {

        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_rich_correctedBasedOnXSD.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_IVEF_oneVessel_mini_with_AISTaggedItem_from_IVEFMessage_to_CiseMessage() throws IOException, URISyntaxException, JAXBException {

        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_mini_with_AISTaggedItem.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertThat(messageListPair.getRight(), is(emptyIterable()));

        XmlMapper tempMapper = new DefaultXmlMapper.Pretty();
        logger.debug("new CISE XML: {}", tempMapper.toXML(ciseMessage));
    }

    @Test
    // For values from CISE that does not match with IVEF value we map it into TaggedItem, putting the default value in PosReport.NavStatus, for example:
    // <VesselData>
    //   <StaticData ... NavStatus="15" ... />
    //	 <TaggedItem Key="CISE_NAV_STATUS" Value=""AISSART_SEEKING_TO_ATTRACT_ATTENTION""/>
    // </VesselData>"
    public void it_converts_Cise_Vessel_with_not_mapped_navigational_status_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_CiseSpecificNavigationalStatus.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        assertNotNull(pushMessage);
        IVEFConverterImpl converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertEquals(ivefMessage.getBody().getVesselData().get(0).getPosReport().getNavStatus(), navStatusMapping.getDefaultIVEFValue());
        assertEquals(ivefMessage.getBody().getVesselData().get(0).getTaggedItem().get(0).getKey(), navStatusMapping.getIVEFTaggedItemKey());
        assertEquals(ivefMessage.getBody().getVesselData().get(0).getTaggedItem().get(0).getValue(), NavigationalStatusType.IN_DISTRESS_OR_REQUIRING_ASSISTANCE.value());
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_Cise_Vessel_with_not_mapped_sensorType_to_IVEF_MSGVesselData() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_CiseSpecificSensorType.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        assertNotNull(pushMessage);
        IVEFConverterImpl converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertNotNull(ivefMessage);
        assertMessagesEqual(pushMessage, ivefMessage);
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }


    //TODO: generated CISE message have correct payload with vessel but everything else apart from payload was randomly generated in eu.cise.ivef.IVEFConverterImpl.convertFromIvef
    @Test
    @Disabled
    public void it_performs_round_trip_conversion_for_Push_oneVessel_mini() throws IOException, URISyntaxException {

        //given
        String ciseXML = readResource("cisemessages/Push_oneVessel_mini.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message againConvertedCiseMessage = messageListPair.getLeft();

        //then
        String newCiseXML = xmlMapper.toXML(againConvertedCiseMessage);
        logger.debug("new CISE XML: {}", newCiseXML);
        Assertions.assertTrue(getXMLsDifference(ciseXML, newCiseXML, CISE_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
        assertThat(messageListPair.getRight(), is(emptyIterable()));

    }

    //TODO: generated CISE message have incomplete fields due to translation blind-spots (from CISE to IVEF and back).
    @Test
    @Disabled
    public void it_performs_round_trip_conversion_for_Push_twoVessel_rich() throws IOException, URISyntaxException {
        //given
        String ciseXML = readResource("cisemessages/Push_fourVessel_rich.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message againConvertedCiseMessage = messageListPair.getLeft();

        //then
        String newCiseXML = xmlMapper.toXML(againConvertedCiseMessage);
        logger.debug("new CISE XML: {}", newCiseXML);
        Assertions.assertTrue(getXMLsDifference(ciseXML, newCiseXML, CISE_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_converts_from_Push_VoyageDestination_to_IVEF() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/PushVesselWithVoyageDestination.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertMessagesEqual(pushMessage, ivefMessage);

        // roundTrip conversion
        MSGVesselData msgVesselDataFromString = IVEFHelper.stringToData(IVEFHelper.dataToString(ivefMessage));

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(msgVesselDataFromString);
        Message ciseMessageFromIVEF = messageListPair.getLeft();
        //then
        assertMessagesEqual(ciseMessageFromIVEF, ivefMessage);
    }


    @Test
    public void it_performs_round_trip_conversion_for_oneVoyage_VoyageDestination() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_oneVoyageWithDestination.xml");
    }

    @Test
    public void it_converts_multipleVoyage_with_different_Draughts_adding_log_for_multiple_voyages_and_keeping_only_the_first_one() throws IOException, URISyntaxException, JAXBException, SAXException {
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_multipleVoyageWithDifferentDraught.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);

        //then
        Message ciseMessage = messageListPair.getLeft();
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();
        Vessel vessel = (Vessel) payload.getAnies().get(0);
        checkMultipleOccurrenceCommentInCISEMetadata(vessel.getMetadatas(), VesselDataTranslator.MULTIPLE_VOYAGE_FOUND);
        assertTrue(vessel.getDraught() != null && vessel.getDraught() == 18.5);
    }

    @Test
    public void it_converts_multipleVoyage_with_different_PersonsOnBoard_adding_log_for_multiple_voyages_and_keeping_only_the_first_one() throws IOException, URISyntaxException, JAXBException, SAXException {
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_multipleVoyageWithDifferentPersonsOnBoard.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);

        //then
        Message ciseMessage = messageListPair.getLeft();
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();
        Vessel vessel = (Vessel) payload.getAnies().get(0);
        checkMultipleOccurrenceCommentInCISEMetadata(vessel.getMetadatas(), VesselDataTranslator.MULTIPLE_VOYAGE_FOUND);
        assertTrue(vessel.getTotalPersonsOnBoard() != null && vessel.getTotalPersonsOnBoard() == 18);
    }


    @Test
    public void it_converts_from_CISE_MultipleVoyages_to_IVEF_keeping_only_the_first_movement() throws IOException, URISyntaxException, JAXBException {
        //given
        String ciseXML = readResource("cisemessages/PushVesselWithMultipleVoyages.xml");
        Push pushMessage = xmlMapper.fromXML(ciseXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<MSGVesselData, List<ConversionException>>
                msgVesselDataListPair = converter.convertToIvef(pushMessage);
        MSGVesselData ivefMessage = msgVesselDataListPair.getLeft();

        //then
        assertTrue(ivefMessage.getBody().getVesselData().get(0).getVoyage().size()==1);
        Voyage voyage = ivefMessage.getBody().getVesselData().get(0).getVoyage().get(0);
        assertTrue(voyage !=null && "Le Havre".equals(voyage.getDestination()));

        checkMultipleOccurrenceCommentInIVEFTaggedItems(ivefMessage.getBody().getVesselData().get(0).getTaggedItem(), VesselDataTranslator.MULTIPLE_VOYAGE_FOUND);
    }


    private void checkMultipleOccurrenceCommentInCISEMetadata(List<Metadata> metadataList, String comment) {
        boolean foundValue = false;
        for (Metadata metadata : metadataList) {
            if (comment.equals(metadata.getComments())) {
                foundValue = true;
            }
        }
        if (foundValue == false) {
            fail("Metadata indicating multiple voyages warning not found!");
        }
    }

    private void checkMultipleOccurrenceCommentInIVEFTaggedItems(List<TaggedItem> taggedItems, String comment) {
        boolean foundValue = false;
        for (TaggedItem taggedItem : taggedItems) {
            if (VesselDataTranslator.IVEF_CONVERSION_WARNING.equals(taggedItem.getKey()) && comment.equals(taggedItem.getValue())) {
                foundValue = true;
            }
        }
        if (!foundValue) {
            fail("TaggedItem indicating multiple voyages warning not found!");
        }
    }

    @Test
    public void it_converts_multipleVoyages_with_different_CargoTypes_adding_log_for_multiple_voyages_and_keeping_only_the_first_one() throws IOException, URISyntaxException, JAXBException, SAXException {
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_multipleVoyageWithDifferentCargoType.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();
        Vessel vessel = (Vessel) payload.getAnies().get(0);
        checkMultipleOccurrenceCommentInCISEMetadata(vessel.getMetadatas(), VesselDataTranslator.MULTIPLE_VOYAGE_FOUND);
        assertTrue(vessel.getCargoRel() != null &&
                vessel.getCargoRel().getCargo() != null &&
                vessel.getCargoRel().getCargo().getCargoType() == CargoType.OTHER);
    }


    @Test
    void it_converts_IVEF_oneVesselWithMultipleShipTypes_into_CISE_with_multiple_ShipTypes() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVesselWithMultipleShipTypes.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);

        //when
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(ciseMessage, ivefMessage);
        XmlEntityPayload xmlEntityPayload = (XmlEntityPayload) ciseMessage.getPayload();
        Vessel ciseVessel = (Vessel) xmlEntityPayload.getAnies().get(0);
        assertEquals(3, ciseVessel.getShipTypes().size());
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_mini_with_CreationDate_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_mini_with_CreationDate.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);

        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        List<String> exceptions = Arrays.asList("/MSG_VesselData/Body/VesselData/StaticData/@Id", "/MSG_VesselData/Body/VesselData/StaticData/@Source");
        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, exceptions, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_mini_with_AISTaggedItem_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_mini_with_AISTaggedItem.xml");
        logger.debug("ivefXML before:  {}", ivefXML);

        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);

        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        List<String> exceptions = Arrays.asList("/MSG_VesselData/Body/VesselData/StaticData/@Id", "/MSG_VesselData/Body/VesselData/StaticData/@Source");

        logger.debug("ivefXML after:{}", newIvefXML);

        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, exceptions, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_SensorTypeEnum_DefinedMappings_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_SensorTypeEnum_DefinedMappings.xml");
    }


    //TODO: not using the testRoundTripConversion for the big message because the Breadth is not considered equal if compared directly to CISE since we have an
    // integer in CISE but in IVEF we have double
    @Test
    public void it_performs_round_trip_conversion_for_IVEF_big_correctedBasedOnXSD_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_big_correctedBasedOnXSD.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);
        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, IVEF_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_BreadthPrecision_Case_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_BreadthPrecision_Case.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();

        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);

        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, IVEF_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_SOGPrecision_Case_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_SOGPrecision_Case.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);
        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, IVEF_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_rich_correctedBasedOnXSD_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_rich_correctedBasedOnXSD.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_ShipTypeEnum_DefinedMappings_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_ShipTypeEnum_DefinedMappings.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_NavigationalStatusTypeEnum_DefinedMappings_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_NavigationalStatusTypeEnum_DefinedMappings.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_CargoTypeEnum_DirectMapping_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_CargoTypeEnum_DirectMapping.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_missingNavProperty_Case_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_missingNavProperty_Case.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_lostPropertyCase_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_lostPropertyCase.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_rateOfTurnPropertyCase_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_rateOfTurnPropertyCase.xml");
    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_ObjectTypePropertyCase_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        testRoundTripConversion("ivefmessages/IVEF_oneVessel_ObjectTypePropertyCase.xml");
    }

    public void testRoundTripConversion(String resourceName) throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource(resourceName);
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        logger.debug("ciseMessage: \n{}", xmlMapper.toXML(ciseMessage));

        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);
        logger.debug("newIvefXML: \n{}", newIvefXML);

        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");

        assertMessagesEqual(ciseMessage, ivefMessage);
        assertMessagesEqual(ciseMessage, againConvertedIvefMessage);

        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, IVEF_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }

    //TODO: new test:  cise to ivef to cise, check equality with assertMessagesEqual only

    @Test
    public void it_converts_from_IVEF_MSGVesselData_to_Cise_Vessel() throws IOException, URISyntaxException, JAXBException {

        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_rich_correctedBasedOnXSD.xml");
        MSGVesselData vesselData = IVEFHelper.stringToData(ivefXML);

        String marshalledIVEFXML = IVEFHelper.dataToString(vesselData);
        Assertions.assertTrue(getXMLsDifference(ivefXML, marshalledIVEFXML, null, customStrategy).isEmpty());

        Assertions.assertEquals(1, vesselData.getBody().getVesselData().size());
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(vesselData);
        Message convertedMessage = messageListPair.getLeft();

        //then
        assertMessagesEqual(convertedMessage, vesselData);
        assertThat(messageListPair.getRight(), is(emptyIterable()));
    }

    @Test
    public void it_checks_if_getXMLsDifference_will_correctly_find_XMLs_to_be_different() throws IOException, URISyntaxException, JAXBException {

        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_rich_correctedBasedOnXSD.xml");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();


        //when
        Message ciseMessage = converter.convertFromIvef(ivefMessage).getLeft();
        Message ciseMessageAltered = converter.convertFromIvef(ivefMessage).getLeft();

        XmlEntityPayload xmlEntityPayload = (XmlEntityPayload) ciseMessageAltered.getPayload();
        Vessel ciseVessel = (Vessel) xmlEntityPayload.getAnies().get(0);
        ciseVessel.setMMSI(1234L);

        MSGVesselData ivefMessageExpected = converter.convertToIvef(ciseMessage).getLeft();
        MSGVesselData ivefMessageAltered = converter.convertToIvef(ciseMessageAltered).getLeft();

        //then
        String ivefMessageExpectedXML = IVEFHelper.dataToString(ivefMessageExpected);
        String ivefMessageAlteredXML = IVEFHelper.dataToString(ivefMessageAltered);
        String diffResult = getXMLsDifference(ivefMessageExpectedXML, ivefMessageAlteredXML, IVEF_EXCEPTIONS, customStrategy);

        assertThat(diffResult, not(isEmptyOrNullString()));
        assertThat(diffResult, containsString("538007768"));
        assertThat(diffResult, containsString("1234"));

    }

    @Test
    public void it_performs_round_trip_conversion_for_IVEF_oneVessel_oneVoyage_ETA_xml() throws IOException, URISyntaxException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("ivefmessages/IVEF_oneVessel_oneVoyage_ETA.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        IVEFConverter<Message, MSGVesselData> converter = new IVEFConverterImpl();

        //when
        Pair<Message, List<ConversionException>> messageListPair = converter.convertFromIvef(ivefMessage);
        Message ciseMessage = messageListPair.getLeft();
        Pair<MSGVesselData, List<ConversionException>> msgVesselDataListPair = converter.convertToIvef(ciseMessage);
        MSGVesselData againConvertedIvefMessage = msgVesselDataListPair.getLeft();

        //then
        String newIvefXML = IVEFHelper.dataToString(againConvertedIvefMessage);
        logger.debug("newIvefXML: \n{}", newIvefXML);

        validateAgainstXsd(newIvefXML, "xsd/xsd_0_1_5.xsd");
        assertMessagesEqual(ciseMessage, ivefMessage);
        assertMessagesEqual(ciseMessage, againConvertedIvefMessage);

        Assertions.assertTrue(getXMLsDifference(ivefXML, newIvefXML, IVEF_EXCEPTIONS, customStrategy).isEmpty(), "The returned string should not be empty");
        assertThat(messageListPair.getRight(), is(emptyIterable()));
        assertThat(msgVesselDataListPair.getRight(), is(emptyIterable()));
    }


    private void assertMessagesEqual(Message ciseMessage, MSGVesselData ivefMessage) throws JAXBException {
        // convert cise to String and back again
        ciseMessage = xmlMapper.fromXML(xmlMapper.toXML(ciseMessage));

        ivefMessage = IVEFHelper.stringToData(IVEFHelper.dataToString(ivefMessage));

        //TODO: this mapping tests need to be revisited - HEADER EQUALITY not done
        //TODO: this mapping tests need to be revisited - BODY EQUALITY (WITHOUT PAYLOAD) not done
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();

        List<VesselData> ivefVesselsList = ivefMessage.getBody().getVesselData();
        List<Vessel> ciseVesselList = payload.getAnies().stream().filter(obj -> obj instanceof Vessel).map(obj -> (Vessel) obj).collect(Collectors.toList());

        assertEquals(ciseVesselList.size(), ivefVesselsList.size());

        for (int i = 0; i < ciseVesselList.size(); i++) {
            assertVesselsEquals(ciseVesselList.get(i), ivefVesselsList.get(i));
        }
    }

    //TODO: joining coma can be tested only when deserializing ivef
    private void assertVesselsEquals(Vessel ciseVessel, VesselData ivefVessel) {
        testTaggedItemsAndMetadata(ciseVessel, ivefVessel);
        testCreationDate((Objet) ciseVessel, ivefVessel);
        testPosReport(ciseVessel, ivefVessel);
        testStaticData(ciseVessel, ivefVessel);
        testVoyageData(ciseVessel, ivefVessel);
    }

    private void testTaggedItemsAndMetadata(Vessel ciseVessel, VesselData ivefVessel) {
        // get the AIS_SHIP_TYPE from ivefVessel and check that there exists metadata on ciseVessel
        testTaggedItem(ciseVessel, ivefVessel, IVEF_AIS_SHIP_TYPE_PROPERTY_NAME, VESSELDATA_TAGGEDITEM_AIS_SHIP_TYPE_KEY);
    }

    private void testTaggedItem(Vessel ciseVessel, VesselData ivefVessel, String ivefPropertyExactName, String ciseMetadataKey) {
        compareProperty(
                ivefVessel.getTaggedItem().stream()
                        .filter(taggedItem -> taggedItem.getKey().equals(ivefPropertyExactName))
                        .findFirst()
                        .map(TaggedItem::getValue)
                        .orElse(null),
                getValueFromCiseMetadata(ciseVessel.getMetadatas(), ciseMetadataKey));
    }

    private void testVoyageData(Vessel ciseVessel, VesselData ivefVessel) {
        //IVEF Voyage.Draught <-> CISE /Vessel/Draught
        compareDistinctExtractedNumericValuesWithCiseValue(ivefVessel.getVoyage().stream(), t -> t.getDraught(), ciseVessel.getDraught());
        //IVEF Voyage.PersonsOnBoard <-> CISE /Vessel/TotalPersonsOnBoard
        compareDistinctExtractedNumericValuesWithCiseValue(ivefVessel.getVoyage().stream(), voyage -> voyage.getPersonsOnBoard(), ciseVessel.getTotalPersonsOnBoard());
        //IVEF Voyage.CargoType <-> CISE /Vessel/CargoRel/Cargo/CargoType
        if (ivefVessel.getVoyage() != null && ivefVessel.getVoyage().size() > 0) {
            Voyage voyage = ivefVessel.getVoyage().get(0);
            if (ciseVessel.getCargoRel() != null && ciseVessel.getCargoRel().getCargo() != null &&
                    (ciseVessel.getCargoRel().getCargo().getCargoType() != null || voyage.getCargoType() != null)) {
                compareMappingValues(
                        voyage.getCargoType(),
                        ciseVessel.getCargoRel().getCargo().getCargoType(),
                        cargoTypeMapping,
                        ciseVessel.getCargoRel().getCargo().getMetadatas(),
                        ivefVessel.getTaggedItem()
                );
            }
        }
        //IVEF Voyage.Destination <-> CISE /Vessel/InvolvedEvent/Movement/LocationRel/LocationRole=EndPlace and /Vessel/InvolvedEvent/Movement/LocationRel/PortLocation/PortName
        if (ciseVessel.getInvolvedEventRels() != null
                && !ciseVessel.getInvolvedEventRels().isEmpty()
                && ivefVessel.getVoyage().stream().map(Voyage::getDestination).anyMatch(destination -> destination != null)
        ) {
            checkIVEFVoyageDestinationVsCISEVoyageMovementPortLocation(ciseVessel, ivefVessel);
        }

        Set<XMLGregorianCalendar> ciseETASet = getCiseETASetFromCiseMovements(ciseVessel);
        Set<XMLGregorianCalendar> ivefETASet = ivefVessel.getVoyage().stream()
                .map(voyage -> voyage.getETA())
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        if (!ciseETASet.equals(ivefETASet)) {
            logger.debug("ivef values: {}, cise values: {}", ivefETASet, ciseETASet);
            fail("parameters are not equal");
        }
    }

    private Set<XMLGregorianCalendar> getCiseETASetFromCiseMovements(Vessel ciseVessel) {
        Optional<List<Objet.InvolvedEventRel>> optionalInvolvedEventRels = Optional.ofNullable(ciseVessel.getInvolvedEventRels());
        if (!optionalInvolvedEventRels.isPresent() || optionalInvolvedEventRels.get().isEmpty()) {
            return Collections.emptySet();
        }

        return optionalInvolvedEventRels.get().stream()
                .map(Objet.InvolvedEventRel::getEvent)
                .filter(event -> event instanceof Movement)
                .filter(movement -> ((Movement) movement).getMovementType() == MovementType.VOYAGE)
                .flatMap(movement -> movement.getLocationRels().stream())
                .filter(locationRel -> locationRel.getDateTime() != null)
                .map(locationRel -> locationRel.getDateTime())
                .filter(dateTime -> dateTime.getStartDate() != null && dateTime.getStartTime() != null)
                .map(currentPeriod -> VesselDataTranslator.combineDateAndTime(currentPeriod))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

    }

    private void testStaticData(Vessel ciseVessel, VesselData ivefVessel) {
        List<StaticData> staticDataList = ivefVessel.getStaticData();
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> staticData.getCallsign(), ciseVessel.getCallSign());
        compareDistinctExtractedNumericValuesWithCiseValue(staticDataList.stream(), t -> t.getMMSI(), ciseVessel.getMMSI());
        compareDistinctExtractedNumericValuesWithCiseValue(staticDataList.stream(), t -> t.getIMO(), ciseVessel.getIMONumber());
        compareDistinctExtractedNumericValuesWithCiseValue(staticDataList.stream(), staticData -> staticData.getLength(), ciseVessel.getLength());
        compareDistinctExtractedNumericValuesWithCiseValue(staticDataList.stream(), staticData -> staticData.getBreadth(), ciseVessel.getBreadth());
        compareDistinctExtractedStringsWithCiseStringList(staticDataList.stream(), staticData -> staticData.getShipName(), ciseVessel.getNames());
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> staticData.getCountryFlag(), ciseVessel.getNationalities().isEmpty() ? null : convertNationalitiesCISEtoIVEF(ciseVessel.getNationalities()));
        staticDataList.forEach(staticData -> compareProperty(StringUtils.isBlank(staticData.getCountryFlag()) ? null : staticData.getCountryFlag(), convertNationalitiesCISEtoIVEF(ciseVessel.getNationalities())));
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> staticData.getId(), getValueFromCiseMetadata(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_ID_KEY));
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> staticData.getSourceName(), getValueFromCiseMetadata(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_SOURCENAME_KEY));
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> BigNumberUtil.bigIntegerToString(staticData.getSource()), getValueFromCiseMetadata(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_SOURCE_KEY));
        compareShipTypes(ciseVessel, ivefVessel);
        compareDistinctExtractedStringValuesWithCiseStringValue(staticDataList.stream(), staticData -> BigNumberUtil.bigIntegerToString(staticData.getObjectType()), getValueFromCiseMetadata(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_OBJECTTYPE_KEY));
    }


    private void testPosReport(Vessel ciseVessel, VesselData ivefVessel) {
        PosReport ivefPosReport = ivefVessel.getPosReport();
        if (ivefPosReport != null) {
            if (ciseVessel.getNavigationalStatus() != null || ivefPosReport.getNavStatus() != null) {
                compareMappingValues(ivefPosReport.getNavStatus(), ciseVessel.getNavigationalStatus(), navStatusMapping, ciseVessel.getMetadatas(), ivefVessel.getTaggedItem());
            }
        }

        if ((ciseVessel.getLocationRels() != null && !ciseVessel.getLocationRels().isEmpty()) ||
                (ivefVessel.getPosReport() != null && ivefVessel.getPosReport().getPos() != null)) {
            if (ciseVessel.getLocationRels() == null || ciseVessel.getLocationRels().isEmpty()) {
                logger.debug("CISE Vessel LocationRels: null or empty, IVEF Vessel PosReport: {}", ivefVessel.getPosReport());
                fail("CISE has no location but IVEF does");
            }
            if (ivefVessel.getPosReport() == null || ivefVessel.getPosReport().getPos() == null) {
                logger.debug("IVEF Vessel PosReport: null or Pos: null, CISE Vessel LocationRels: {}", ciseVessel.getLocationRels());
                fail("IVEF has no position but CISE does");
            }


            Objet.LocationRel locationRel = ciseVessel.getLocationRels().get(0);

            // test PosReport.UpdateTime and LocationRel.PeriodOfTime
            if (ivefPosReport.getUpdateTime() != null || locationRel.getPeriodOfTime() != null) {
                Period currentPeriod = locationRel.getPeriodOfTime();
                compareProperty(ivefPosReport.getUpdateTime(), VesselDataTranslator.combineDateAndTime(currentPeriod));
            }

            Location location = ciseVessel.getLocationRels().get(0).getLocation();
            // test Location metadata

            compareProperty(bigIntegerToString(ivefPosReport.getId()), getValueFromCiseMetadata(location.getMetadatas(), VESSELDATA_POSREPORT_ID_KEY));
            compareProperty(bigIntegerToString(ivefPosReport.getSourceId()), getValueFromCiseMetadata(location.getMetadatas(), VESSELDATA_POSREPORT_SOURCE_ID_KEY));
            // compare heading and orientation
            compareProperty(ivefPosReport.getOrientation(), locationRel.getHeading());
            Geometry ciseGeometry = location.getGeometries().get(0);
            // compare UpdSensorType
            if (ivefPosReport.getUpdSensorType() != null || locationRel.getSensorType() != null) {
//                compareTypesWithMapping(locationRel.getSensorType(), ivefPosReport.getUpdSensorType(), sensorTypeMapping, locationRel.getMetadatas());
                compareMappingValues(ivefPosReport.getUpdSensorType(), locationRel.getSensorType(), sensorTypeMapping, locationRel.getMetadatas(), ivefVessel.getTaggedItem());
            }
            Pos ivefPos = ivefPosReport.getPos();
            compareProperty(new BigDecimal(ciseGeometry.getLongitude()), ivefPos.getLong()); // Longitude
            compareProperty(new BigDecimal(ciseGeometry.getLatitude()), ivefPos.getLat()); // Latitude
            Objet.LocationRel ciseLocationRel = ciseVessel.getLocationRels().get(0);
            compareProperty(ciseLocationRel.getSOG(), SpeedConverter.convertMetersPerSecondToKnots(ivefPosReport.getSOG()));
            compareProperty(new BigDecimal(ciseLocationRel.getCOG()), ivefPosReport.getCOG());
            compareProperty(getValueFromCiseMetadata(location.getMetadatas(), VESSELDATA_POSREPORT_LOST_KEY), ivefPosReport.getLost());

            if (ivefPosReport.getRateOfTurn() != null) {
                compareProperty(ivefPosReport.getRateOfTurn(), Double.parseDouble(getValueFromCiseMetadata(location.getMetadatas(), VESSELDATA_POSREPORT_RATEOFTURN_KEY)));
            }

        }
    }

    /**
     * Comparison rule:
     * - If a value is a default one we look for specific value in CISE Metadata or IVEF TaggeItem, and we do
     * the comparison between the specific value and the counterpart value.
     * - If there is no default value we should be in the case of direct mapping, so we do the plain comparison
     * between the two values.
     */
    private <I, C> void compareMappingValues(I valueIVEF, C valueCISE, ImmutableBidiMultiMap<I, C> mappingMap, List<Metadata> metadataList, List<TaggedItem> taggedItems) {
        String specificCISEValueFromIVEFTaggedItem;
        String specificIVEFValueFromCISEMetadata;
        assertNotNull(valueIVEF);
        assertNotNull(valueCISE);
        if (mappingMap.isDefaultCiseType(valueCISE)) {
            // look for specific value in metadata
            specificIVEFValueFromCISEMetadata = getValueFromCiseMetadata(metadataList, mappingMap.getCISEMetadataCommentsKey());
            if (specificIVEFValueFromCISEMetadata != null) {
                assertEquals(valueIVEF.toString(), specificIVEFValueFromCISEMetadata);
                return;
            }
        }
        if (mappingMap.isDefaultIVEFType(valueIVEF)) {
            // look for specific value in TaggedItems
            specificCISEValueFromIVEFTaggedItem = getValueFromTaggedItem(mappingMap.getIVEFTaggedItemKey(), taggedItems);
            if (specificCISEValueFromIVEFTaggedItem != null) {
                assertEquals(valueCISE, mappingMap.getCISEEnumFromStringValue(specificCISEValueFromIVEFTaggedItem));
                return;
            }
        }
        assertTrue(mappingMap.compareValues(valueIVEF, valueCISE));
    }

    private void compareShipTypes(Vessel vessel, VesselData vesselData) {
        List<VesselType> shipTypeCISEValues = vessel.getShipTypes();
        List<StaticData> staticDataIVEFList = vesselData.getStaticData();

        // no value present
        if (shipTypeCISEValues.isEmpty() && (staticDataIVEFList.isEmpty() || (staticDataIVEFList.size() == 1 && staticDataIVEFList.get(0).getShipType() == null))) {
            return;
        }

        try {
            // case of unique value in CISE shipType
            if (shipTypeCISEValues.size() == 1 && (staticDataIVEFList.size() == 1 && staticDataIVEFList.get(0).getShipType() != null)) {
                System.out.println("StaticDataId: " + staticDataIVEFList.get(0).getId());
                BigInteger shipTypeIVEFValue = staticDataIVEFList.get(0).getShipType();
                VesselType shipTypeCISEValue = shipTypeCISEValues.get(0);

                // case of not mapping values in CISE to IVEF
                if (shipTypeMapping.isDefaultIVEFType(shipTypeIVEFValue)) {
                    // look for specific CISE value in taggedItem
                    String specificCISEValue = getValueFromTaggedItem(shipTypeMapping.getIVEFTaggedItemKey(), vesselData.getTaggedItem());
                    // here we check whether the specific value correspond to valid CISE value
                    if (specificCISEValue != null) {
                        shipTypeMapping.getCISEEnumFromStringValue(specificCISEValue);
                    }
                    return;
                }
                // case of not mapping values in IVEF to CISE
                if (shipTypeMapping.isDefaultCiseType(shipTypeCISEValue)) {
                    // look for specific IVEF value in CISE Metadata
                    String specificValueFromCISEMetadata = getValueFromCiseMetadata(vessel.getMetadatas(), shipTypeMapping.getCISEMetadataCommentsKey());
                    // here we check whether the specific value correspond to valid IVEF value
                    if (shipTypeMapping.getCISEValue(BigInteger.valueOf(Long.parseLong(specificValueFromCISEMetadata))) != null) {
                        return;
                    }
                }

                if (shipTypeMapping.compareValues(shipTypeIVEFValue, shipTypeCISEValue)) {
                    return;
                }
            }
            // case of multiple values in CISE shipType
            if (shipTypeCISEValues.size() > 1 && (staticDataIVEFList.size() == 1 && staticDataIVEFList.get(0).getShipType() != null)) {
                System.out.println("StaticDataId: " + staticDataIVEFList.get(0).getId());
                BigInteger shipTypeIVEFValue = staticDataIVEFList.get(0).getShipType();

                // case of not mapping values in CISE to IVEF
                if (shipTypeMapping.isDefaultIVEFType(shipTypeIVEFValue)) {
                    // look for specific CISE value in taggedItem
                    String specificCISEValue = getValueFromTaggedItem(shipTypeMapping.getIVEFTaggedItemKey(), vesselData.getTaggedItem());
                    // here we check whether the specific values correspond to valid CISE values
                    if (specificCISEValue != null) {
                        String[] specificCISEValues = specificCISEValue.split(",");
                        for (String ciseValue : specificCISEValues) {
                            shipTypeMapping.getCISEEnumFromStringValue(ciseValue);
                        }
                    }
                    return;
                }
            }
        } catch (IllegalArgumentException e) {
            fail("Ship types do not match");
        }

        fail("Ship types do not match");
    }


    private void testCreationDate(Objet ciseVessel, VesselData ivefVessel) {
        Optional<String> maybeIvefCreationDate = ivefVessel.getTaggedItem().stream().filter(t -> t.getKey().equals("creationDate")).map(TaggedItem::getValue).findFirst();

        List<Metadata> metadataList = ciseVessel.getMetadatas();
        Optional<XMLGregorianCalendar> maybeCiseCreationDate = metadataList.isEmpty() ? Optional.empty() : Optional.ofNullable(metadataList.get(0).getCreationDate());

        if (maybeIvefCreationDate.isPresent() && maybeCiseCreationDate.isPresent()) {
            ZonedDateTime ivefCreationDate = ZonedDateTime.parse(maybeIvefCreationDate.get(), DateTimeFormatter.ISO_DATE_TIME).withZoneSameInstant(ZoneOffset.UTC);
            ZonedDateTime ciseCreationDate = maybeCiseCreationDate.get().toGregorianCalendar().toZonedDateTime().withZoneSameInstant(ZoneOffset.UTC);
            assertEquals(ciseCreationDate, ivefCreationDate, "CISE Creation Date: " + ciseCreationDate + ", IVEF Creation Date: " + ivefCreationDate);
        } else if (maybeIvefCreationDate.isPresent()) {
            logger.debug("IVEF Creation Date: {}, CISE Creation Date: null", maybeIvefCreationDate.get());
            fail("IVEF has creation date (" + maybeIvefCreationDate.get() + ") but CISE does not");
        } else if (maybeCiseCreationDate.isPresent()) {
            logger.debug("IVEF Creation Date: null, CISE Creation Date: {}", maybeCiseCreationDate.get());
            fail("CISE has creation date (" + maybeCiseCreationDate.get().toXMLFormat() + ") but IVEF does not");
        }
    }

    /*
     * Processes a Stream of data points, extracting numeric values from each data point using
     * the provided extraction function. It verifies that there is only one distinct extracted value across
     * all data points, and compares this value with the provided CISE numeric value.
     */
    private <T> void compareDistinctExtractedNumericValuesWithCiseValue(Stream<T> stream, Function<T, Number> valueExtractor, Number ciseValue) {
        List<Double> values = stream.map(valueExtractor).filter(Objects::nonNull).map(Number::doubleValue).collect(Collectors.toList());
        long distinctCount = values.stream().distinct().count();
        if (distinctCount > 1) {
            logger.debug("Multiple distinct values: {}", values);
            fail("parameters are not equal. Values: " + values);
        } else if (distinctCount == 1) {
            Double ivefValue = values.stream().findFirst().orElse(null);
            if (ciseValue != null) {
                assertTrue(equalsWithTolerance(ivefValue, ciseValue.doubleValue()), "parameters are not equal. IVEF Value: " + ivefValue + ", CISE Value: " + ciseValue);
            } else {
                logger.debug("IVEF Value: {}, CISE Value: null", ivefValue);
                fail("IVEF has value (" + ivefValue + ") but CISE does not");
            }
        } else {
            if (ciseValue != null) {
                logger.debug("IVEF Value: null, CISE Value: {}", ciseValue);
                fail("CISE has value (" + ciseValue + ") but IVEF does not");
            }
        }
    }

    /*
     * Processes a Stream of data points, extracting string values from each data point using
     * the provided extraction function. It compares the Set of distinct extracted string values
     * with the provided list of CISE string values to verify they are equal.
     */
    private <T> void compareDistinctExtractedStringsWithCiseStringList(Stream<T> stream, Function<T, String> valueExtractor, List<String> ciseValues) {
        Set<String> ivefValues = stream.map(valueExtractor).filter(Objects::nonNull).collect(Collectors.toSet());
        Set<String> ciseValuesSet = (ciseValues == null || ciseValues.isEmpty()) ? Collections.emptySet() : new HashSet<>(ciseValues);
        if (!ivefValues.equals(ciseValuesSet)) {
            logger.debug("ivef values: {}, cise values: {}", ivefValues, ciseValuesSet);
            fail("parameters are not equal");
        }
    }

    /*
     * This function processes a Stream of data points, extracting string values from each data point using
     * the provided extraction function. It collects all the distinct extracted string values into a Set, and
     * checks whether the provided CISE string value is present in this Set.
     */
    private <T> void compareDistinctExtractedStringValuesWithCiseStringValue(Stream<T> streamIVEFValues, Function<T, String> valueExtractor, String ciseValue) {
        Set<String> ivefValuesSet = streamIVEFValues.map(valueExtractor).filter(Objects::nonNull).collect(Collectors.toSet());

        if (ciseValue != null && !ivefValuesSet.contains(ciseValue)) {
            logger.debug("ivef values: {}, cise value: {}", ivefValuesSet, ciseValue);
            fail("parameters are not equal");
        }
    }

    private void compareProperty(Object ivefValue, Object ciseValue) {
        if (ivefValue != null) {
            if (ciseValue != null) {
                if (ivefValue instanceof Number && ciseValue instanceof Number) {
                    Number ivefValueNumber = (Number) ivefValue;
                    Number ciseValueNumber = (Number) ciseValue;
                    assertTrue(equalsWithTolerance(ivefValueNumber.doubleValue(), ciseValueNumber.doubleValue()),
                            "parameters are not equal. IVEF Value: " + ivefValueNumber + ", CISE Value: " + ciseValueNumber);
                } else {
                    assertEquals(ivefValue, ciseValue);
                }
            } else {
                logger.debug("IVEF value: {}, CISE value: null", ivefValue);
                fail("IVEF has value (" + ivefValue + ") but CISE does not");
            }
        } else {
            if (ciseValue != null) {
                logger.debug("IVEF value: null, CISE value: {}", ciseValue);
                fail("CISE has value (" + ciseValue + ") but IVEF does not");
            }
        }
    }

    private boolean compareXMLValues(String firstValue, String secondValue) {
        if (firstValue == null && secondValue == null) {
            return true;
        }
        if (firstValue == null || secondValue == null) {
            return false;
        }

        try {
            double firstNum = Double.parseDouble(firstValue);
            double secondNum = Double.parseDouble(secondValue);
            return equalsWithTolerance(firstNum, secondNum);
        } catch (RuntimeException e1) {
            try {
                ZonedDateTime dateTime1 = ZonedDateTime.parse((CharSequence) firstValue);
                ZonedDateTime dateTime2 = ZonedDateTime.parse((CharSequence) secondValue);
                return dateTime1.toInstant().equals(dateTime2.toInstant());
            } catch (RuntimeException e2) {
            }
            return firstValue.equals(secondValue);
        }
    }

    /*
     * This function processes a Stream of data points, extracting enumeration values from each data point using
     * the provided extraction function. It then verifies that there is only one distinct extracted value across
     * all data points, and compares this value with the provided CISE enumeration value.
     */
    private <T, CE extends Enum<CE>> void compareDistinctExtractedEnumValuesWithCiseEnumValue(Stream<T> stream, Function<T, CE> valueExtractor, CE ciseEnumValue) {
        List<CE> values = stream.map(valueExtractor).filter(Objects::nonNull).collect(Collectors.toList());
        long distinctCount = values.stream().distinct().count();
        if (distinctCount > 1) {
            logger.debug("Multiple distinct values: {}", values);
            fail("parameters are not equal. Values: " + values);
        } else if (distinctCount == 1) {
            CE ivefEnumValue = values.stream().findFirst().orElse(null);
            if (ciseEnumValue != null) {
                if (!ivefEnumValue.equals(ciseEnumValue)) {
                    logger.debug("IVEF value: {}, CISE value: {}", ivefEnumValue, ciseEnumValue);
                    fail("parameters are not equal. IVEF Value: " + ivefEnumValue + ", CISE Value: " + ciseEnumValue);
                }
            } else {
                logger.debug("IVEF value: {}, CISE value: null", ivefEnumValue);
                fail("IVEF has value (" + ivefEnumValue + ") but CISE does not");
            }
        } else {
            if (ciseEnumValue != null) {
                logger.debug("IVEF value: null, CISE value: {}", ciseEnumValue);
                fail("CISE has value (" + ciseEnumValue + ") but IVEF does not");
            }
        }
    }

    private boolean equalsWithTolerance(double a, double b) {
        boolean result = Math.abs(a - b) < EQUALITY_TOLERANCE;
        if (!result) {
            logger.debug("Values were not equal: a=" + a + " , b=" + b);
        }
        return result;
    }

}