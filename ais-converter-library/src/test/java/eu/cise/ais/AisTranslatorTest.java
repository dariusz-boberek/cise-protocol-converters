package eu.cise.ais;

import eu.cise.ais.helpers.TestHelpers;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.eucise.xml.DefaultXmlMapper;
import eu.eucise.xml.XmlMapper;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AisTranslatorTest {

    @Test
    public void it_tests_ais_translation() throws IOException, URISyntaxException {
        String aisString = TestHelpers.readResource("ais-single-message.txt");
        AisConverter translator = new AisConverter();
        AtomicReference<Vessel> vessel = new AtomicReference<>();
        assertDoesNotThrow(()-> vessel.set(translator.translateToCiseVessel(aisString)));
        XmlMapper xmlMapper = new DefaultXmlMapper.Pretty();
        System.out.println(xmlMapper.toXML(vessel.get()));
    }

}