package eu.cise.kml;


import de.micromata.opengis.kml.v_2_2_0.*;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.object.Objet;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.servicemodel.v1.message.Message;
import eu.cise.servicemodel.v1.message.XmlEntityPayload;
import eu.eucise.xml.DefaultXmlMapper;
import eu.eucise.xml.XmlMapper;
import helpers.TestHelpers;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KMLTranslatorTest {

    private static XmlMapper xmlMapper;

    @BeforeAll
    public static void beforeAll() {
        xmlMapper = new DefaultXmlMapper();
    }


    @Test
    public void it_tests_kml_translation_for_vessel() throws IOException, URISyntaxException {
        Message ciseMessage = xmlMapper.fromXML(TestHelpers.readResource("cisemessages/PUSHmed.xml"));
        Kml resultKml = translateAndTestKML(ciseMessage);

        StringWriter writer = new StringWriter();
        System.out.println(resultKml.marshal(writer));

    }

    @Test
    public void it_tests_kml_translation_withVessels_without_IMONumber() throws IOException, URISyntaxException {
        Message ciseMessage = xmlMapper.fromXML(TestHelpers.readResource("cisemessages/PUSHmed_vessel_without_IMONumber.xml"));
        Kml resultKml = translateAndTestKML(ciseMessage);
    }

    private Kml translateAndTestKML(Message ciseMessage) {
        KMLConverter translator = new KMLConverter();
        Kml resultKML = translator.transformToKml(ciseMessage);
        testEqual(ciseMessage, resultKML);


        // print kml
        assertTrue(resultKML.marshal());

        return resultKML;
    }

    private void testEqual(Message ciseMessage, Kml kmlMessage) {
        // test number of vessels
        //cise message
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();
        payload.getAnies().size();

        //kml message
        Document mainDocument = (Document) kmlMessage.getFeature();
        assertEquals(payload.getAnies().size(), mainDocument.getFeature().size());

        for (int i = 0; i < payload.getAnies().size(); i++) {
            assertVesselAndPlacemarkEqual(getExactVesselFromCiseMessage(ciseMessage, i), getExactPlacemarkFeatureFromKmlMessage(kmlMessage, i), ciseMessage);
        }

    }

    private Vessel getExactVesselFromCiseMessage(Message ciseMessage, int vesselIndex) {
        XmlEntityPayload payload = (XmlEntityPayload) ciseMessage.getPayload();
        return (Vessel) payload.getAnies().get(vesselIndex);

    }

    private Placemark getExactPlacemarkFeatureFromKmlMessage(Kml kmlMessage, int vesselIndex) {
        Document mainDocument = (Document) kmlMessage.getFeature();
        Placemark placemark = (Placemark) mainDocument.getFeature().get(vesselIndex);
        return placemark;

    }

    private void assertPlacemarkIDStartsWith(String ciseValue, String placeMarkValue) {
        if (ciseValue == null) {
            assertTrue(placeMarkValue.startsWith("vessel_NO_IMO"));
        } else {
            assertTrue(placeMarkValue.startsWith("vessel_"+ ciseValue));
        }
    }

    private void assertVesselAndPlacemarkEqual(Vessel vessel, Placemark placemark, Message ciseMessage) {
        // test vessel id <-> imonumber
        assertPlacemarkIDStartsWith(vessel.getIMONumber() != null ? vessel.getIMONumber().toString() : null, placemark.getId());
        // test timestamp
        TimeStamp timeStamp = (TimeStamp) placemark.getTimePrimitive();
        assertEquals(String.valueOf(ciseMessage.getCreationDateTime()), timeStamp.getWhen());
        // test coordinates
        Geometry geometry = vessel.getLocationRels().get(0).getLocation().getGeometries().get(0);
        Point placeMarkPoint = (de.micromata.opengis.kml.v_2_2_0.Point) placemark.getGeometry();
        assertEquals(geometry.getLongitude(), String.valueOf(placeMarkPoint.getCoordinates().get(0).getLongitude()));
        assertEquals(geometry.getLatitude(), String.valueOf(placeMarkPoint.getCoordinates().get(0).getLatitude()));
        // test extended data
        Map<String, Data> extendedDataAsMap = new HashMap<>();
        ExtendedData extendedData = placemark.getExtendedData();
        for (Data data : extendedData.getData()) {
            extendedDataAsMap.put(data.getName(), data);
        }
        // test SPEED
        Objet.LocationRel locationRel = vessel.getLocationRels().get(0);
        assertEquals(String.valueOf(locationRel.getSpeed()), extendedDataAsMap.get("SPEED").getValue());
        // test COURSE
        assertEquals(String.valueOf(locationRel.getCOG()), extendedDataAsMap.get("COURSE").getValue());
        // test NGL-LABEL
        assertEquals(StringUtils.join(vessel.getNames(), ","),extendedDataAsMap.get("NGL-LABEL").getValue());
        // test UUID
        assertEquals(String.valueOf(vessel.getUVI()), extendedDataAsMap.get("UUID").getValue());
        // test MMSI
        assertEquals(String.valueOf(vessel.getMMSI()), extendedDataAsMap.get("MMSI").getValue());
        // test IMO
        assertEquals(String.valueOf(vessel.getIMONumber()), extendedDataAsMap.get("IMO").getValue());
        // test NavStatus
        assertEquals(String.valueOf(vessel.getNavigationalStatus()), extendedDataAsMap.get("NavStatus").getValue());
        // test SOURCEID
        assertEquals("N/A", extendedDataAsMap.get("SOURCEID").getValue());
        // test SOURCESYSTEM
        assertEquals("N/A", extendedDataAsMap.get("SOURCESYSTEM").getValue());
        // test REALTIME
        assertEquals(String.valueOf(ciseMessage.getSender().getDataFreshness()), extendedDataAsMap.get("REALTIME").getValue());
    }

}