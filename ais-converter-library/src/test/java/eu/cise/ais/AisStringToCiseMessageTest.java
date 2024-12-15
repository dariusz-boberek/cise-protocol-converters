package eu.cise.ais;

import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;
import eu.cise.ais.config.DefaultAisConverterConfiguration;
import eu.cise.ais.converters.AisMessageToAisMsgConverter;
import eu.cise.ais.converters.AisMsgToVesselConverter;
import eu.cise.ais.converters.NmeaToAISMessageConverter;
import eu.cise.ais.converters.StringToNmeaConverter;
import eu.cise.ais.core.*;
import eu.cise.ais.helpers.TestHelpers;
import eu.cise.datamodel.v1.entity.Entity;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Optional;

public class AisStringToCiseMessageTest {

    @Test
    public void it_reads_ais_string_from_file_transforms_to_vessel() throws IOException, URISyntaxException {
        String aisString = TestHelpers.readResource("ais-single-message.txt");
        StringToNmeaConverter stringToNmea = new StringToNmeaConverter();
        NmeaToAISMessageConverter nmeaToAISMessage = new NmeaToAISMessageConverter("SRC");
        AisMessageToAisMsgConverter aisMessageToAisMsg = new AisMessageToAisMsgConverter();
        AisMsgToVesselConverter aisMsgToVessel = new AisMsgToVesselConverter(new DefaultAisConverterConfiguration());

        // translate
        NMEAMessage nmeaMessage = stringToNmea.translate(aisString);

        Optional<AISMessage> aisMessage = nmeaToAISMessage.translate(nmeaMessage);

        Optional<AisMsg> aisMsg = aisMessageToAisMsg.translate(aisMessage.get());

        Optional<Entity> vesselEntity = aisMsgToVessel.translate(aisMsg.get());
        Vessel actualVesselEntity = (Vessel) vesselEntity.get();

        Geometry generatedGeometry = actualVesselEntity.getLocationRels().get(0).getLocation().getGeometries().get(0);

        Assert.assertEquals(String.valueOf(-1.3485667),generatedGeometry.getLongitude());
        Assert.assertEquals(String.valueOf(50.854515),generatedGeometry.getLatitude());



    }
}
