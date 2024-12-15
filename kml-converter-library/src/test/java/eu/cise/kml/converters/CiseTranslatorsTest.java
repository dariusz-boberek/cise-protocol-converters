package eu.cise.kml.converters;


import eu.cise.converters.exceptions.ConversionException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.*;

public class CiseTranslatorsTest {

    Logger testLogger= LoggerFactory.getLogger(CiseTranslatorsTest.class);

    @Test
    public void it_finds_translator_for_vessel() throws ConversionException {
        testLogger.info("test");
        CiseConverter translator = CiseToKMLConverters.getTranslator("Vessel");

        assertTrue(translator instanceof VesselConverter);
    }

    @Test
    public void it_throws_exception_for_unknown_translator() {
        assertThrows(ConversionException.class,()-> CiseToKMLConverters.getTranslator("Unknown"));
    }
}