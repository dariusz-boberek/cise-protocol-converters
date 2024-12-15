package eu.cise.ivef.translators;

import eu.cise.converters.exceptions.ConversionException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TranslatorRegistryTest {

    @Test
    public void it_finds_translator_for_vessel() throws ConversionException {
        CiseDataTranslator translator = TranslatorRegistry.getTranslator("Vessel");

        assertTrue(translator instanceof VesselDataTranslator);
    }

    @Test
    public void it_throws_exception_for_unknown_translator() {
        assertThrows(ConversionException.class, () -> TranslatorRegistry.getTranslator("Unknown"));
    }

}