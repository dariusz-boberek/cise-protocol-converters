package eu.cise.ivef.translators;

import eu.cise.converters.exceptions.ConversionException;
import eu.cise.datamodel.v1.entity.cargo.Cargo;
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
import eu.cise.datamodel.v1.entity.object.SensorType;
import eu.cise.datamodel.v1.entity.object.Vehicle;
import eu.cise.datamodel.v1.entity.period.Period;
import eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.datamodel.v1.entity.vessel.VesselType;
import eu.cise.ivef.generated.Pos;
import eu.cise.ivef.generated.PosReport;
import eu.cise.ivef.generated.StaticData;
import eu.cise.ivef.generated.TaggedItem;
import eu.cise.ivef.generated.VesselData;
import eu.cise.ivef.generated.Voyage;
import eu.cise.ivef.translators.mappers.CargoTypeMapping;
import eu.cise.ivef.translators.mappers.ImmutableBidiMultiMap;
import eu.cise.ivef.translators.mappers.NavStatusMapping;
import eu.cise.ivef.translators.mappers.SensorTypeMapping;
import eu.cise.ivef.translators.mappers.ShipTypeMapping;
import eu.cise.ivef.translators.utils.SpeedConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static eu.cise.ivef.Constants.IVEF_AIS_SHIP_TYPE_PROPERTY_NAME;
import static eu.cise.ivef.Constants.IVEF_CREATION_DATE_PROPERTY_NAME;
import static eu.cise.ivef.Constants.METADATA_CUSTOM_ARRAY_DELIMITER;
import static eu.cise.ivef.Constants.METADATA_CUSTOM_KV_DELIMITER;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_ID_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_LOST_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_RATEOFTURN_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_SOURCE_ID_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_POSREPORT_SOURCE_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_ID_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_OBJECTTYPE_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCENAME_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCENAME_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCE_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_STATICDATA_SOURCE_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_TAGGEDITEM_AIS_SHIP_TYPE_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_ID_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_ID_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_SOURCENAME_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_SOURCENAME_KEY;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_SOURCE_DEFAULT_VALUE;
import static eu.cise.ivef.Constants.VESSELDATA_VOYAGE_SOURCE_KEY;
import static eu.cise.ivef.translators.utils.BigNumberUtil.bigDecimalToDouble;
import static eu.cise.ivef.translators.utils.BigNumberUtil.bigDecimalToString;
import static eu.cise.ivef.translators.utils.BigNumberUtil.bigIntegerToString;
import static eu.cise.ivef.translators.utils.BigNumberUtil.toBigDecimal;
import static eu.cise.ivef.translators.utils.BigNumberUtil.toBigInteger;


/**
 * Implements the CiseDataTranslator interface specifically for translating data between
 * Vessel (a CISE Entity) and VesselData (IVEF format).
 */
public class VesselDataTranslator implements CiseDataTranslator<Vessel, VesselData> {

    private static final Logger logger = LoggerFactory.getLogger("VesselTranslator");
    public static String MULTIPLE_STATIC_DATA_FOUND = "FoundMultipleStaticData";
    public static String MULTIPLE_VOYAGE_FOUND = "FoundMultipleVoyages";
    public static String IVEF_CONVERSION_WARNING = "CONVERSION_WARNING";
    private static Map<String, String> localeISO3Map;
    private static Map<String, String> localeISO2Map;
    private final NavStatusMapping navStatusMapping = new NavStatusMapping();
    private final ShipTypeMapping shipTypeMapping = new ShipTypeMapping();
    private final CargoTypeMapping cargoTypeMapping = new CargoTypeMapping();
    private final SensorTypeMapping sensorTypeMapping = new SensorTypeMapping();

    /**
     * Constructor for VesselDataTranslator.
     * Initializes a new instance of the VesselDataTranslator class,
     * setting up the necessary state for translating vessel data between different formats.
     */
    public VesselDataTranslator() {
        initCountryCodeMapping();
    }

    /**
     * Combines date and time from a CISE Period object into an XMLGregorianCalendar
     * useful for converting CISE-specific time representations into a XML format.
     *
     * @param cisePeriod The CISE period.
     * @return An XMLGregorianCalendar representing the combined date and time.
     */
    public static XMLGregorianCalendar combineDateAndTime(Period cisePeriod) {
        XMLGregorianCalendar dateOnly = cisePeriod.getStartDate();
        XMLGregorianCalendar timeOnly = cisePeriod.getStartTime();
        DatatypeFactory factory = null;
        try {
            factory = DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException ex) {
            throw new ConversionException("Unable to create XmlGregorianCalendar factory", ex);
        }

        XMLGregorianCalendar combinedDateTime = factory.newXMLGregorianCalendar();
        combinedDateTime.setYear(dateOnly.getYear());
        combinedDateTime.setMonth(dateOnly.getMonth());
        combinedDateTime.setDay(dateOnly.getDay());
        combinedDateTime.setHour(timeOnly.getHour());
        combinedDateTime.setMinute(timeOnly.getMinute());
        combinedDateTime.setSecond(timeOnly.getSecond());
        combinedDateTime.setMillisecond(timeOnly.getMillisecond());
        combinedDateTime.setTimezone(timeOnly.getTimezone());

        return combinedDateTime;
    }

    /**
     * Converts a list of nationalities from CISE format to flattened IVEF format.
     *
     * @param nationalities A list of nationalities in CISE format.
     * @return A string representing {@code eu.cise.ivef.Constants.METADATA_CUSTOM_ARRAY_DELIMITER} separated nationalities in IVEF format.
     */
    public static String convertNationalitiesCISEtoIVEF(List<String> nationalities) {
        String nationalitiesIVEF = String.join(METADATA_CUSTOM_ARRAY_DELIMITER, nationalities.stream().map(nationality -> iso2CountryCodeToIso3CountryCode(nationality)).collect(Collectors.toList()));

        return StringUtils.isBlank(nationalitiesIVEF) ? null : nationalitiesIVEF;
    }

    private static String iso3CountryCodeToIso2CountryCode(String iso3CountryCode) {
        String country;
        if ((country = localeISO3Map.get(iso3CountryCode)) != null) {
            return country;
        } else {
            if (iso3CountryCode != null) {
                return StringUtils.rightPad(StringUtils.substring(iso3CountryCode, 0, 1), 2, 'X');
            } else {
                return "XX";
            }
        }
    }

    private static String iso2CountryCodeToIso3CountryCode(String iso2CountryCode) {
        String country;
        if ((country = localeISO2Map.get(iso2CountryCode)) != null) {
            return country;
        } else {
            if (iso2CountryCode != null) {
                return StringUtils.rightPad(iso2CountryCode, 3, 'X');
            } else {
                return "XXX";
            }
        }
    }

    /**
     * Translates a Vessel (CISE Entity) into VesselData (IVEF format).
     *
     * @param ciseVessel The vessel object in CISE format.
     * @return The translated vessel object in IVEF format.
     * @throws ConversionException If any issue arises during the conversion process.
     */
    @Override
    public VesselData translate(Vessel ciseVessel) throws ConversionException {
        VesselData ivefVessel = new VesselData();
        convertCreationDateCISEToIVEF(ciseVessel, ivefVessel);
        convertAISShipTypeCISEToIVEF(ciseVessel, ivefVessel);
        convertPosReportAndLocationCISEToIVEF(ciseVessel, ivefVessel);
        convertStaticDataCISEToIVEF(ciseVessel, ivefVessel);
        convertVoyageDataCISEToIVEF(ciseVessel, ivefVessel);
        return ivefVessel;
    }

    /**
     * Translates VesselData (IVEF format) into a Vessel (CISE Entity).
     *
     * @param ivefVessel The vessel data in IVEF format.
     * @return The translated vessel object in CISE format.
     */
    @Override
    public Vessel translate(VesselData ivefVessel) {
        Vessel ciseVessel = new Vessel();
        convertCreationDateIVEFToCISE(ivefVessel, (Objet) ciseVessel);
        convertAISShipTypeIVEFToCISE(ivefVessel, (Objet) ciseVessel);
        convertPosReportAndLocationIVEFToCISE(ciseVessel, ivefVessel);
        convertStaticDataIVEFToCISE(ivefVessel, ciseVessel);
        convertVoyageDataIVEFToCISE(ciseVessel, ivefVessel);
        return ciseVessel;
    }

    /**
     * Retrieves the name of this translator instance. Method is used to obtain a unique name
     * that identifies the specific translator.
     *
     * @return The name of this translator instance.
     */
    @Override
    public String getName() {
        return Vessel.class.getSimpleName();
    }

    private void convertCreationDateCISEToIVEF(Vessel ciseVessel, VesselData ivefVesselData) {
        List<Metadata> metadataList = ((Objet) ciseVessel).getMetadatas();
        if (!metadataList.isEmpty() && metadataList.get(0).getCreationDate() != null) {
            XMLGregorianCalendar ciseVesselCreationDate = metadataList.get(0).getCreationDate();
            TaggedItem ivefCreationDate = new TaggedItem();
            ivefCreationDate.setKey(IVEF_CREATION_DATE_PROPERTY_NAME);
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            ivefCreationDate.setValue(formatter.format(ciseVesselCreationDate.toGregorianCalendar().toZonedDateTime()));
            ivefVesselData.getTaggedItem().add(ivefCreationDate);
        } //TODO: if there are no metadata in the Vessel, then we should use the CISE CreationDate
    }

    private void convertAISShipTypeCISEToIVEF(Vessel ciseVessel, VesselData ivefVesselData) {
        List<Metadata> metadataList = ((Objet) ciseVessel).getMetadatas();
        Optional<String> maybeRichIvefValue = getValueOfMetadataWithComment(metadataList, VESSELDATA_TAGGEDITEM_AIS_SHIP_TYPE_KEY);
        if (maybeRichIvefValue.isPresent()) {
            TaggedItem ivefAISShipTypeTaggedItem = new TaggedItem();
            ivefAISShipTypeTaggedItem.setKey(IVEF_AIS_SHIP_TYPE_PROPERTY_NAME);
            ivefAISShipTypeTaggedItem.setValue(maybeRichIvefValue.get());
            ivefVesselData.getTaggedItem().add(ivefAISShipTypeTaggedItem);
        }
    }

    private void convertPosReportAndLocationCISEToIVEF(Vessel ciseVessel, VesselData ivefVesselData) throws ConversionException {
        PosReport posReport = null;
        NavigationalStatusType navStatusCISEValue = ciseVessel.getNavigationalStatus();
        if (navStatusCISEValue != null) {
            posReport = createPosReport();
            ivefVesselData.setPosReport(posReport);

            BigInteger navStatusIVEFValue = navStatusMapping.getIVEFValue(navStatusCISEValue);
            if (navStatusMapping.isDefaultCiseType(navStatusCISEValue)) {
                Optional<String> specificIVEFValue = getValueOfMetadataWithComment(ciseVessel.getMetadatas(), navStatusMapping.getCISEMetadataCommentsKey());
                if (specificIVEFValue.isPresent()) {
                    navStatusIVEFValue = new BigInteger(specificIVEFValue.get());
                }
            }
            // if sensorTypeIVEFValue is default value then we add the taggedItem to store original CISE value
            if (navStatusMapping.isDefaultIVEFType(navStatusIVEFValue)) {
                TaggedItem navStatusTaggedItem = new TaggedItem();
                navStatusTaggedItem.setKey(navStatusMapping.getIVEFTaggedItemKey());
                navStatusTaggedItem.setValue(ciseVessel.getNavigationalStatus().value());
                ivefVesselData.getTaggedItem().add(navStatusTaggedItem);
            }
            posReport.setNavStatus(navStatusIVEFValue);
        }
        List<Objet.LocationRel> locationRels = ciseVessel.getLocationRels();
        if (!locationRels.isEmpty()) {
            if (locationRels.size() > 1) {
                throw new ConversionException("Unable to translate vessel with multiple locations into IVEF");
            }
            Objet.LocationRel ciseLocationRel = locationRels.get(0);

            if (posReport == null) {
                posReport = createPosReport();
                ivefVesselData.setPosReport(posReport);
            }
            if (ciseLocationRel.getPeriodOfTime() != null) {
                Period currentPeriod = ciseLocationRel.getPeriodOfTime();
                posReport.setUpdateTime(combineDateAndTime(currentPeriod));
            }
            if (ciseLocationRel.getHeading() != null) {
                posReport.setOrientation(new BigDecimal(ciseLocationRel.getHeading()));
            }
            if (ciseLocationRel.getSOG() != null) {
                posReport.setSOG(new BigDecimal(SpeedConverter.convertKnotsToMetersPerSecond(ciseLocationRel.getSOG())));
            }
            if (ciseLocationRel.getCOG() != null) {
                BigDecimal unboundedFloatingValue = new BigDecimal(ciseLocationRel.getCOG());
                posReport.setCOG(unboundedFloatingValue.setScale(1, RoundingMode.HALF_UP));
            }
            if (ciseLocationRel.getSensorType() != null) {
                SensorType sensorTypeCISEValue = ciseLocationRel.getSensorType();
                if (sensorTypeCISEValue != null) {
                    BigInteger sensorTypeIVEFValue = sensorTypeMapping.getIVEFValue(sensorTypeCISEValue);
                    if (sensorTypeMapping.isDefaultCiseType(sensorTypeCISEValue)) {
                        Optional<String> specificIVEFValue = getValueOfMetadataWithComment(ciseLocationRel.getMetadatas(), sensorTypeMapping.getCISEMetadataCommentsKey());
                        if (specificIVEFValue.isPresent()) {
                            sensorTypeIVEFValue = new BigInteger(specificIVEFValue.get());
                        }
                    }
                    // if sensorTypeIVEFValue is default value then we add the taggedItem to store original CISE value
                    if (sensorTypeMapping.isDefaultIVEFType(sensorTypeIVEFValue)) {
                        TaggedItem sensorTypeTaggedItem = new TaggedItem();
                        sensorTypeTaggedItem.setKey(sensorTypeMapping.getIVEFTaggedItemKey());
                        sensorTypeTaggedItem.setValue(sensorTypeCISEValue.value());
                        ivefVesselData.getTaggedItem().add(sensorTypeTaggedItem);
                    }
                    posReport.setUpdSensorType(sensorTypeIVEFValue);
                }
            }
            Location ciseLocation = ciseLocationRel.getLocation();
            if (ciseLocation != null) {
                List<Geometry> geometries = ciseLocation.getGeometries();
                if (geometries != null && !geometries.isEmpty()) {
                    if (geometries.size() > 1) {
                        throw new ConversionException("Unable to translate vessel with multiple geometries into IVEF");
                    }
                    Geometry geometry = geometries.get(0);
                    Pos pos = new Pos();
                    posReport.setPos(pos);
                    pos.setLong(toBigDecimal(geometry.getLongitude()));
                    pos.setLat(toBigDecimal(geometry.getLatitude()));
                }
                getValueOfMetadataWithComment(ciseLocation.getMetadatas(), VESSELDATA_POSREPORT_LOST_KEY).ifPresent(posReport::setLost);
                Optional<String> maybeRateOfTurn = getValueOfMetadataWithComment(ciseLocation.getMetadatas(), VESSELDATA_POSREPORT_RATEOFTURN_KEY);
                if (maybeRateOfTurn.isPresent()) {
                    String rateOfTurnAsString = maybeRateOfTurn.get();
                    try {
                        posReport.setRateOfTurn(Double.parseDouble(rateOfTurnAsString));
                    } catch (NumberFormatException e) {
                        throw new ConversionException("Could not parse " + rateOfTurnAsString + " to a Double", e);
                    }
                }
            }
        }
        if (posReport != null) {
            getBigIntegerFromLocationRelMetadata(locationRels, VESSELDATA_POSREPORT_ID_KEY).ifPresent(posReport::setId);
            getBigIntegerFromLocationRelMetadata(locationRels, VESSELDATA_POSREPORT_SOURCE_ID_KEY).ifPresent(posReport::setSourceId);
        }
    }

    private PosReport createPosReport() {
        PosReport posReport = new PosReport();
        posReport.setId(new BigInteger(VESSELDATA_POSREPORT_ID_DEFAULT_VALUE));
        posReport.setSourceId(new BigInteger(VESSELDATA_POSREPORT_SOURCE_ID_DEFAULT_VALUE));
        return posReport;
    }

    private Optional<BigInteger> getBigIntegerFromLocationRelMetadata(List<Objet.LocationRel> locationRels, String key) {
        if (!locationRels.isEmpty()) {
            Location location = locationRels.get(0).getLocation();
            if (location != null && location.getMetadatas() != null && !location.getMetadatas().isEmpty()) {
                return getValueOfMetadataWithComment(location.getMetadatas(), key).map(BigInteger::new);
            }
        }
        return Optional.empty();
    }

    private Optional<String> getValueOfMetadataWithComment(List<Metadata> metadataList, String key) {
        Metadata foundMetadata = null;
        for (Metadata metadata : metadataList) {
            if (metadata.getComments() == null) {
                continue;
            }
            if (metadata.getComments().startsWith(key + METADATA_CUSTOM_KV_DELIMITER)) {
                foundMetadata = metadata;
                break;
            }
        }
        if (foundMetadata != null) {
            return Optional.of(foundMetadata.getComments().split(METADATA_CUSTOM_KV_DELIMITER)[1]);
        } else {
            return Optional.empty();
        }
    }

    private void convertStaticDataCISEToIVEF(Vessel ciseVessel, VesselData vesselData) {
        List<StaticData> staticDataList = vesselData.getStaticData();
        StaticData staticData = createStaticData();

        if (ciseVessel.getNames() != null && !ciseVessel.getNames().isEmpty()) {
            String shipName = String.join(METADATA_CUSTOM_ARRAY_DELIMITER, ciseVessel.getNames());
            staticData.setShipName(shipName);
        }
        if (ciseVessel.getNationalities() != null && !ciseVessel.getNationalities().isEmpty()) {
            String nationalities = convertNationalitiesCISEtoIVEF(ciseVessel.getNationalities());
            staticData.setCountryFlag(nationalities); //TODO: we need to figure out the format of nationalities XXX or XX.
        }
        if (StringUtils.isNotBlank(ciseVessel.getCallSign())) {
            staticData.setCallsign(ciseVessel.getCallSign()); // CallSign
        }
        if (ciseVessel.getMMSI() != null) {
            staticData.setMMSI(toBigInteger(ciseVessel.getMMSI())); // MMSI
        }
        if (ciseVessel.getIMONumber() != null) {
            staticData.setIMO(toBigInteger(ciseVessel.getIMONumber())); // IMO
        }
        if (ciseVessel.getLength() != null) {
            staticData.setLength(toBigDecimal(ciseVessel.getLength())); // Length
        }
        if (ciseVessel.getBreadth() != null) {
            staticData.setBreadth(toBigDecimal(ciseVessel.getBreadth())); // Breadth
        }

        List<Metadata> metadataList = ciseVessel.getMetadatas();

        // ShipType
        List<VesselType> shipTypeCISEListValues = ciseVessel.getShipTypes();
        if (shipTypeCISEListValues != null && !shipTypeCISEListValues.isEmpty()) {
            if (shipTypeCISEListValues.size() == 1) {
                VesselType shipTypeCISEValue = shipTypeCISEListValues.get(0);
                if (shipTypeCISEValue != null) {
                    BigInteger shipTypeIVEFValue = shipTypeMapping.getIVEFValue(shipTypeCISEValue);
                    // if CISE value is the default value then search for specific value in metadata
                    if (shipTypeMapping.isDefaultCiseType(shipTypeCISEValue)) {
                        Optional<String> maybeRichIvefValue = getValueOfMetadataWithComment(metadataList, shipTypeMapping.getCISEMetadataCommentsKey());
                        if (maybeRichIvefValue.isPresent()) {
                            try {
                                shipTypeIVEFValue = new BigInteger(maybeRichIvefValue.get());
                            } catch (NumberFormatException e) {
                                throw new ConversionException("Could not parse " + maybeRichIvefValue.get() + " to a Double");
                            }
                        }
                    }
                    // if the mapped IVEF value is the default value we store the specific CISE value in IVEF tagged Item
                    if (shipTypeMapping.isDefaultIVEFType(shipTypeIVEFValue)) {
                        TaggedItem shipTypeTaggedItem = new TaggedItem();
                        shipTypeTaggedItem.setKey(shipTypeMapping.getIVEFTaggedItemKey());
                        shipTypeTaggedItem.setValue(shipTypeCISEValue.value());
                        vesselData.getTaggedItem().add(shipTypeTaggedItem);
                    }
                    staticData.setShipType(shipTypeIVEFValue);
                }
            } else {
                // if we have more than one CISE value we compose add taggedItem with the list of values from CISE values and set the shipType IVEF value to default
                String shipTypeValuesCommaSeparatedStringList = String.join(",", shipTypeCISEListValues.stream().map(st -> st.value()).collect(Collectors.toList()));

                TaggedItem shipTypeTaggedItem = new TaggedItem();
                shipTypeTaggedItem.setKey(shipTypeMapping.getIVEFTaggedItemKey());
                shipTypeTaggedItem.setValue(shipTypeValuesCommaSeparatedStringList);
                vesselData.getTaggedItem().add(shipTypeTaggedItem);

                staticData.setShipType(shipTypeMapping.getDefaultIVEFValue());
            }
        }

        Optional<String> maybeObjectType = getValueOfMetadataWithComment(metadataList, VESSELDATA_STATICDATA_OBJECTTYPE_KEY);
        if (maybeObjectType.isPresent()) {
            String objectTypeAsString = maybeObjectType.get();
            try {
                staticData.setObjectType(new BigInteger(objectTypeAsString));
            } catch (NumberFormatException e) {
                throw new ConversionException("Could not parse " + objectTypeAsString + " to a Double");
            }
        }
        if (isStaticDataFilled(staticData)) {
            staticDataList.add(staticData);
            for (StaticData currentStaticData : staticDataList) {
                getValueOfMetadataWithComment(metadataList, VESSELDATA_STATICDATA_ID_KEY).ifPresent(currentStaticData::setId);
                getValueOfMetadataWithComment(metadataList, VESSELDATA_STATICDATA_SOURCENAME_KEY).ifPresent(currentStaticData::setSourceName);
                getValueOfMetadataWithComment(metadataList, VESSELDATA_STATICDATA_SOURCE_KEY).map(BigInteger::new).ifPresent(currentStaticData::setSource);
            }
        }
    }

    private boolean isStaticDataFilled(StaticData staticData) {
        return staticData.getShipName() != null || staticData.getCountryFlag() != null || staticData.getCallsign() != null || staticData.getMMSI() != null || staticData.getIMO() != null || staticData.getLength() != null || staticData.getBreadth() != null || staticData.getShipType() != null || staticData.getObjectType() != null;
    }

    private StaticData createStaticData() {
        StaticData staticData = new StaticData();
        staticData.setSource(new BigInteger(VESSELDATA_STATICDATA_SOURCE_DEFAULT_VALUE));
        staticData.setSourceName(VESSELDATA_STATICDATA_SOURCENAME_DEFAULT_VALUE);
        staticData.setId(VESSELDATA_STATICDATA_ID_DEFAULT_VALUE);
        return staticData;
    }

    private void convertVoyageDataCISEToIVEF(Vessel ciseVessel, VesselData vesselData) {
        Double draught = ciseVessel.getDraught();
        Integer totalPersonsOnBoard = ciseVessel.getTotalPersonsOnBoard();
        CargoType cargoType = getCargoType(ciseVessel);
        List<Metadata> cargoMetadataList = getCargoMetadataList(ciseVessel);

        // TODO: how should we behave when we have more than one Movement of type voyage in CISE Vessel?
        //       For the moment we decided to force to use only the first movement as we do for IVEF to CISE conversion
        //       and to add a tagged item with a warning
        Movement movement = getFirstMovementOfTypeVoyage(ciseVessel);
        if (movement != null || draught != null || totalPersonsOnBoard != null || cargoType != null) {
            Voyage voyage = createVoyage();
            vesselData.getVoyage().add(voyage);

            setCommonPropertiesOnVoyage(draught, totalPersonsOnBoard, cargoType, vesselData, cargoMetadataList);

            if (movement != null) {
                List<Metadata> metadataList = movement.getMetadatas();
                getValueOfMetadataWithComment(metadataList, VESSELDATA_VOYAGE_ID_KEY).ifPresent(voyage::setId);
                getValueOfMetadataWithComment(metadataList, VESSELDATA_VOYAGE_SOURCENAME_KEY).ifPresent(voyage::setSourceName);
                getValueOfMetadataWithComment(metadataList, VESSELDATA_VOYAGE_SOURCE_KEY).map(BigInteger::new).ifPresent(voyage::setSource);
                populateIVEFVoyageDataDestination(voyage, (Movement) movement);
                movement.getLocationRels().stream().findAny().ifPresent(locationRel -> {
                    if (locationRel.getDateTime() != null) {
                        voyage.setETA(combineDateAndTime(locationRel.getDateTime()));
                    }
                });
            }
            if (ciseVessel.getInvolvedEventRels().size() > 1) {
                addIvefMetadataOrThrowErrorOnExistingKV(vesselData.getTaggedItem(), IVEF_CONVERSION_WARNING, MULTIPLE_VOYAGE_FOUND);
            }
        }

    }

    private Movement getFirstMovementOfTypeVoyage(Vessel ciseVessel) {
        for (Objet.InvolvedEventRel eventRel : ciseVessel.getInvolvedEventRels()) {
            Event event = eventRel.getEvent();
            if (event instanceof Movement && ((Movement) event).getMovementType() == MovementType.VOYAGE) {
                return (Movement) event;
            }
        }
        return null;
    }

    private void setCommonPropertiesOnVoyage(Double draught, Integer totalPersonsOnBoard, CargoType cargoTypeCISEValue, VesselData vesselData, List<Metadata> metadataList) {
        if (vesselData.getVoyage() != null && !vesselData.getVoyage().isEmpty()) {
            Voyage voyage = vesselData.getVoyage().get(0);
            if (draught != null) {
                voyage.setDraught(toBigDecimal(draught));
            }
            if (totalPersonsOnBoard != null) {
                voyage.setPersonsOnBoard(toBigDecimal(totalPersonsOnBoard));
            }
            if (cargoTypeCISEValue != null) {
                BigInteger ivefCargoType = cargoTypeMapping.getIVEFValue(cargoTypeCISEValue);
                if (cargoTypeMapping.isDefaultCiseType(cargoTypeCISEValue)) {
                    Optional<String> specificIVEFValue = getValueOfMetadataWithComment(metadataList, cargoTypeMapping.getCISEMetadataCommentsKey());
                    if (specificIVEFValue.isPresent()) {
                        ivefCargoType = new BigInteger(specificIVEFValue.get());
                    }
                } else {
                    //we add a taggedItem to store the specific CISE value in IVEF translation
                    addIvefMetadataOrThrowErrorOnExistingKV(vesselData.getTaggedItem(), cargoTypeMapping.getIVEFTaggedItemKey(), cargoTypeCISEValue.value());
                }
                voyage.setCargoType(ivefCargoType);
            }
        }
    }

    private CargoType getCargoType(Vessel ciseVessel) {
        Vehicle.CargoRel cargoRel = ciseVessel.getCargoRel();
        if (cargoRel != null) {
            Cargo cargo = cargoRel.getCargo();
            if (cargo != null) {
                return cargo.getCargoType();
            }
        }
        return null;
    }

    private List<Metadata> getCargoMetadataList(Vessel ciseVessel) {
        Vehicle.CargoRel cargoRel = ciseVessel.getCargoRel();
        if (cargoRel != null) {
            Cargo cargo = cargoRel.getCargo();
            if (cargo != null) {
                return cargo.getMetadatas();
            }
        }
        return null;
    }

    private Stream<Event> getStreamOfMovementOfTypeVoyage(Vessel ciseVessel) {
        return ciseVessel.getInvolvedEventRels().stream().map(Objet.InvolvedEventRel::getEvent).filter(event -> event instanceof Movement && ((Movement) event).getMovementType() == MovementType.VOYAGE);
    }

    private void populateIVEFVoyageDataDestination(Voyage voyage, Movement ciseMovement) {
        // We should use PortName always PortName
        ciseMovement.getLocationRels().stream().findAny().stream().filter(locationRel -> locationRel.getLocationRole() == LocationRoleInEventType.END_PLACE).map(Event.LocationRel::getLocation).filter(location -> location instanceof PortLocation).findAny().ifPresent(location -> voyage.setDestination(((PortLocation) location).getPortName()));
    }

    private void convertCreationDateIVEFToCISE(VesselData ivefVessel, Objet ciseVesselAsObjet) {
        applyToVessel(ivefVessel, ciseVesselAsObjet, IVEF_CREATION_DATE_PROPERTY_NAME, creationDate -> {
            XMLGregorianCalendar xmlGregorianCalendar = convertToXMLGregorianCalendar(creationDate);
            ciseVesselAsObjet.getMetadatas().get(0).setCreationDate(xmlGregorianCalendar);
        });
    }

    private void convertAISShipTypeIVEFToCISE(VesselData ivefVessel, Objet ciseVesselAsObjet) {
        applyToVessel(ivefVessel, ciseVesselAsObjet, IVEF_AIS_SHIP_TYPE_PROPERTY_NAME, aisShipType -> {
            addCiseMetadataOrThrowErrorOnExistingKV(ciseVesselAsObjet.getMetadatas(), VESSELDATA_TAGGEDITEM_AIS_SHIP_TYPE_KEY, aisShipType);
        });
    }

    private void applyToVessel(VesselData ivefVessel, Objet ciseVesselAsObjet, String key, Consumer<String> metadataAction) {
        ivefVessel.getTaggedItem().stream().filter(t -> t.getKey().equals(key)).map(TaggedItem::getValue).findFirst().ifPresent(value -> {
            List<Metadata> metadataList = ciseVesselAsObjet.getMetadatas();
            if (metadataList.isEmpty()) {
                metadataList.add(new Metadata());
            }
            metadataAction.accept(value);
        });
    }

    private XMLGregorianCalendar convertToXMLGregorianCalendar(String dateStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
        Instant instant = Instant.from(dateTimeFormatter.parse(dateStr));
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(Date.from(instant));
        XMLGregorianCalendar xmlGregorianCalendar = null;
        try {
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            logger.error("Error while converting date", e);
        }
        return xmlGregorianCalendar;
    }

    private void convertPosReportAndLocationIVEFToCISE(Vessel ciseVessel, VesselData ivefVesselData) {
        if (ivefVesselData.getPosReport() != null) {
            PosReport posReport = ivefVesselData.getPosReport();

            BigInteger navStatus = posReport.getNavStatus();
            if (navStatus != null) {
                NavigationalStatusType ciseType = navStatusMapping.getCISEValue(navStatus);
                if (navStatusMapping.isDefaultCiseType(ciseType)) {
                    addCiseMetadataOrThrowErrorOnExistingKV(ciseVessel.getMetadatas(), navStatusMapping.getCISEMetadataCommentsKey(), "" + navStatus);
                }
                ciseVessel.setNavigationalStatus(ciseType);
            }

            Objet.LocationRel locationRel = new Objet.LocationRel();
            if (posReport.getUpdateTime() != null) {
                Period period = new Period();
                period.setStartTime(posReport.getUpdateTime()); // this only stores the time when marshaled
                period.setStartDate(posReport.getUpdateTime()); // this only stores the date when marshaled
                locationRel.setPeriodOfTime(period);
            }
            ciseVessel.getLocationRels().add(locationRel);
            Location location = new Location();

            addCiseMetadataOrThrowErrorOnExistingKV(location.getMetadatas(), VESSELDATA_POSREPORT_ID_KEY, bigIntegerToString(ivefVesselData.getPosReport().getId()));
            addCiseMetadataOrThrowErrorOnExistingKV(location.getMetadatas(), VESSELDATA_POSREPORT_SOURCE_ID_KEY, bigIntegerToString(ivefVesselData.getPosReport().getSourceId()));

            if (posReport.getSOG() != null) {
                locationRel.setSOG(SpeedConverter.convertMetersPerSecondToKnots(posReport.getSOG()));
            }
            if (posReport.getCOG() != null) {
                locationRel.setCOG(bigDecimalToDouble(posReport.getCOG()));
            }

            if (posReport.getOrientation() != null) {
                locationRel.setHeading(bigDecimalToDouble(posReport.getOrientation()));
            }
            if (posReport.getUpdSensorType() != null) {

                BigInteger updSensorType = posReport.getUpdSensorType();
                if (updSensorType != null) {
                    SensorType ciseType = sensorTypeMapping.getCISEValue(updSensorType);
                    if (sensorTypeMapping.isDefaultCiseType(ciseType)) {
                        addCiseMetadataOrThrowErrorOnExistingKV(locationRel.getMetadatas(), sensorTypeMapping.getCISEMetadataCommentsKey(), "" + updSensorType);
                    }
                    locationRel.setSensorType(ciseType);
                }
            }
            if (posReport.getPos() != null) {
                Pos pos = posReport.getPos();
                Geometry geometry = new Geometry();
                location.getGeometries().add(geometry);
                locationRel.setLocation(location);
                geometry.setLongitude(bigDecimalToString(pos.getLong()));
                geometry.setLatitude(bigDecimalToString(pos.getLat()));
            }
            if (posReport.getLost() != null) {
                addCiseMetadataOrThrowErrorOnExistingKV(location.getMetadatas(), VESSELDATA_POSREPORT_LOST_KEY, ivefVesselData.getPosReport().getLost());
            }
            if (posReport.getRateOfTurn() != null) {
                addCiseMetadataOrThrowErrorOnExistingKV(location.getMetadatas(), VESSELDATA_POSREPORT_RATEOFTURN_KEY, ivefVesselData.getPosReport().getRateOfTurn().toString());
            }
        }
    }

    /**
     * Converts static vessel data from the IVEF format to the CISE format. We expect that StaticData is unique in an IVEF VesselData.
     * If we found more than one Static data we just keep the values from the first,
     * and we add a CISE Metatdata to keep track that more than one StaticData was found this way:
     *
     * <p>{@code
     *  Vessel > Metadata > Comments, content: FoundMultipleStaticData
     * }
     *
     * @param ivefVesselData The VesselData object in IVEF format (one or more static data entries).
     * @param ciseVessel     The Vessel object in CISE format to which the data will be converted.
     */
    private void convertStaticDataIVEFToCISE(VesselData ivefVesselData, Vessel ciseVessel) {
        if (ivefVesselData.getStaticData().isEmpty()) {
            return;
        }
        if (ivefVesselData.getStaticData().size() > 1) {
            Metadata metadata = new Metadata();
            metadata.setComments(MULTIPLE_STATIC_DATA_FOUND);
            ciseVessel.getMetadatas().add(metadata);
        }

        StaticData staticData = ivefVesselData.getStaticData().get(0);
        if (StringUtils.isNotBlank(staticData.getCallsign())) {
            ciseVessel.setCallSign(staticData.getCallsign());
        }
        if (StringUtils.isNotBlank(staticData.getShipName())) {
            ciseVessel.getNames().add(staticData.getShipName());
        }
        if (StringUtils.isNotBlank(staticData.getCountryFlag())) {
            ciseVessel.getNationalities().addAll(convertNationalitiesIVEFtoCISE(staticData.getCountryFlag()));
        }
        if (staticData.getMMSI() != null) {
            ciseVessel.setMMSI(staticData.getMMSI().longValue());
        }
        if (staticData.getIMO() != null) {
            ciseVessel.setIMONumber(staticData.getIMO().longValue());
        }
        if (staticData.getLength() != null) {
            ciseVessel.setLength(staticData.getLength().doubleValue());
        }
        if (staticData.getBreadth() != null) {
            ciseVessel.setBreadth((int) Math.ceil(staticData.getBreadth().doubleValue()));
        }
        if (staticData.getShipType() != null) {
            BigInteger ivefShipType = staticData.getShipType();
            // if IVEF is the default value we look for the TaggedItem containing specific values (they can be more than one separated by comma)
            if (shipTypeMapping.isDefaultIVEFType(ivefShipType)) {
                List<VesselType> listOfCISEVesselTypes = (List<VesselType>) getListOfCISEValuesFromCommaSeparatedString(ivefVesselData.getTaggedItem(), shipTypeMapping.getIVEFTaggedItemKey(), shipTypeMapping);
                if (listOfCISEVesselTypes == null || listOfCISEVesselTypes.isEmpty()) {
                    ciseVessel.getShipTypes().add(shipTypeMapping.getDefaultCISEValue());
                } else {
                    ciseVessel.getShipTypes().addAll(listOfCISEVesselTypes);
                }
            } else {
                VesselType ciseVesselType = shipTypeMapping.getCISEValue(ivefShipType);
                // if IVEF value is mapped to default CISE value then we store the specific value in metadata
                if (shipTypeMapping.isDefaultCiseType(ciseVesselType)) {
                    addCiseMetadataOrThrowErrorOnExistingKV(ciseVessel.getMetadatas(), shipTypeMapping.getCISEMetadataCommentsKey(), "" + ivefShipType);
                }
                ciseVessel.getShipTypes().add(ciseVesselType);
            }
        }
        if (staticData.getObjectType() != null) {
            addCiseMetadataOrThrowErrorOnExistingKV(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_OBJECTTYPE_KEY, bigIntegerToString(staticData.getObjectType()));
        }
        addCiseMetadataOrSkipOnExistingKV(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_ID_KEY, staticData.getId());
        addCiseMetadataOrSkipOnExistingKV(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_SOURCENAME_KEY, staticData.getSourceName());
        addCiseMetadataOrSkipOnExistingKV(ciseVessel.getMetadatas(), VESSELDATA_STATICDATA_SOURCE_KEY, bigIntegerToString(staticData.getSource()));
    }

    private List<?> getListOfCISEValuesFromCommaSeparatedString(List<TaggedItem> taggedItem, String taggedItemKey, ImmutableBidiMultiMap<?, ?> mapping) {
        String taggedItemValue = getTaggedItemValueForKey(taggedItem, taggedItemKey);
        if (taggedItemValue != null) {
            List<String> stringValues = Arrays.asList(taggedItemValue.split(","));
            return stringValues.stream().map(mapping::getCISEEnumFromStringValue).collect(Collectors.toList());
        }
        return null;
    }

    private String getTaggedItemValueForKey(List<TaggedItem> taggedItem, String taggedItemKey) {
        for (TaggedItem item : taggedItem) {
            if (item.getKey().equals(taggedItemKey)) {
                return item.getValue();
            }
        }
        return null;
    }

    private Collection<String> convertNationalitiesIVEFtoCISE(String countryFlag) {
        return Arrays.stream(countryFlag.split(METADATA_CUSTOM_ARRAY_DELIMITER)).map(nationality -> iso3CountryCodeToIso2CountryCode(nationality)).collect(Collectors.toList());
    }

    private void convertVoyageDataIVEFToCISE(Vessel ciseVessel, VesselData ivefVesselData) {
        if (ivefVesselData.getVoyage() != null) {
            if (ivefVesselData.getVoyage().isEmpty()) {
                return;
            }
            if (ivefVesselData.getVoyage().size() > 1) {
                Metadata metadata = new Metadata();
                metadata.setComments(MULTIPLE_VOYAGE_FOUND);
                ciseVessel.getMetadatas().add(metadata);
            }

            Voyage voyage = ivefVesselData.getVoyage().get(0);
            if (voyage.getDraught() != null) {
                ciseVessel.setDraught(voyage.getDraught().doubleValue());
            }
            if (voyage.getPersonsOnBoard() != null) {
                ciseVessel.setTotalPersonsOnBoard(voyage.getPersonsOnBoard().intValue());
            }
            BigInteger ivefCargoType = voyage.getCargoType();
            if (ivefCargoType != null) {
                Vehicle.CargoRel cargoRel = new Vehicle.CargoRel();
                ciseVessel.setCargoRel(cargoRel);
                Cargo cargo = new Cargo();

                CargoType ciseCargoType = cargoTypeMapping.getCISEValue(ivefCargoType);

                // Add metadata if cargo type is default (not mappable)
                if (cargoTypeMapping.isDefaultCiseType(ciseCargoType)) {
                    addCiseMetadataOrThrowErrorOnExistingKV(cargo.getMetadatas(), cargoTypeMapping.getCISEMetadataCommentsKey(), "" + ivefCargoType);
                }

                cargoRel.setCargo(cargo);
                cargo.setCargoType(ciseCargoType);
            }

            Movement movement = createMovement(ciseVessel);
            addCiseMetadataOrThrowErrorOnExistingKV(movement.getMetadatas(), VESSELDATA_VOYAGE_ID_KEY, voyage.getId());
            addCiseMetadataOrThrowErrorOnExistingKV(movement.getMetadatas(), VESSELDATA_VOYAGE_SOURCENAME_KEY, voyage.getSourceName());
            addCiseMetadataOrThrowErrorOnExistingKV(movement.getMetadatas(), VESSELDATA_VOYAGE_SOURCE_KEY, bigIntegerToString(voyage.getSource()));
            if (voyage.getDestination() != null && !voyage.getDestination().isBlank()) {
                addPortLocationDestinationToMovement(voyage, movement);
            }

            setVoyageETAIfPresent(voyage, movement);
        }
    }

    private void setVoyageETAIfPresent(Voyage voyage, Movement movement) {
        Optional<XMLGregorianCalendar> optionalETA = Optional.ofNullable(voyage.getETA());
        if (optionalETA.isPresent()) {
            Event.LocationRel locationRel = getOrCreateLocationRelInMovement(movement);
            Period period = new Period();
            period.setStartTime(optionalETA.get());
            period.setStartDate(optionalETA.get());
            locationRel.setDateTime(period);
        }
    }

    private Event.LocationRel getOrCreateLocationRelInMovement(Movement movement) {
        List<Event.LocationRel> locationRels = movement.getLocationRels();
        if (locationRels.isEmpty()) {
            Event.LocationRel locationRel = new Event.LocationRel();
            movement.getLocationRels().add(locationRel);
            return locationRel;
        } else {
            return locationRels.get(0);
        }
    }

    private void addPortLocationDestinationToMovement(Voyage voyage, Movement movement) {
        PortLocation portLocation = new PortLocation();
        portLocation.setPortName(voyage.getDestination());
        Event.LocationRel locationRel = new Event.LocationRel();
        locationRel.setLocationRole(LocationRoleInEventType.END_PLACE);
        locationRel.setLocation(portLocation);

        movement.getLocationRels().add(locationRel);
    }

    private Movement createMovement(Vessel ciseVessel) {
        Movement movement = new Movement();
        movement.setMovementType(MovementType.VOYAGE);
        Objet.InvolvedEventRel involvedEventRel = new Objet.InvolvedEventRel();
        involvedEventRel.setEvent(movement);
        ciseVessel.getInvolvedEventRels().add(involvedEventRel);
        return movement;
    }

    private Voyage createVoyage() {
        Voyage voyage = new Voyage();
        voyage.setId(VESSELDATA_VOYAGE_ID_DEFAULT_VALUE);
        voyage.setSourceName(VESSELDATA_VOYAGE_SOURCENAME_DEFAULT_VALUE);
        voyage.setSource(new BigInteger(VESSELDATA_VOYAGE_SOURCE_DEFAULT_VALUE));
        return voyage;
    }

    private void addCiseMetadataOrThrowErrorOnExistingKV(List<Metadata> metadataList, String key, String value) {

        metadataList.stream().filter(metadata -> metadata.getComments() != null && metadata.getComments().startsWith(key + METADATA_CUSTOM_KV_DELIMITER)).findFirst().ifPresent(metadata -> {
            throw new ConversionException("Trying to add already existing key");
        });

        Metadata metadata = new Metadata();
        metadata.setComments(key + METADATA_CUSTOM_KV_DELIMITER + value);
        metadataList.add(metadata);
    }

    private void addCiseMetadataOrSkipOnExistingKV(List<Metadata> metadataList, String key, String value) {

        Optional<Metadata> optionalMetadata = metadataList.stream().filter(metadata -> metadata.getComments() != null && metadata.getComments().startsWith(key + METADATA_CUSTOM_KV_DELIMITER)).findFirst();
        // only update the data if it was not found
        if (optionalMetadata.isEmpty()) {
            Metadata metadata = new Metadata();
            metadata.setComments(key + METADATA_CUSTOM_KV_DELIMITER + value);
            metadataList.add(metadata);
        }
    }

    //TODO: use this function for storing data on IVEF taggedItem ("metadata")
    private void addIvefMetadataOrThrowErrorOnExistingKV(List<TaggedItem> taggedItems, String key, String value) {
        for (TaggedItem taggedItem : taggedItems) {
            String itemKey = taggedItem.getKey();
            if (itemKey.equals(key)) {
                throw new ConversionException("Trying to add already existing key");
            }
        }

        TaggedItem taggedItem = new TaggedItem();
        taggedItem.setKey(key);
        taggedItem.setValue(value);
        taggedItems.add(taggedItem);
    }

    private void initCountryCodeMapping() {
        // Instantiate static map for countries translation
        try {
            if (localeISO3Map == null) {
                String[] countries = Locale.getISOCountries();
                localeISO3Map = new HashMap<>(countries.length);
                localeISO2Map = new HashMap<>(countries.length);
                for (String country : countries) {
                    Locale locale = new Locale("", country);
                    String localeISO3 = locale.getISO3Country().toUpperCase();
                    localeISO3Map.put(localeISO3, country);
                    localeISO2Map.put(country, localeISO3);
                }
            }
        } catch (MissingResourceException e) {
            throw new ConversionException("Unable to instantiate country maps", e);
        }
    }

}