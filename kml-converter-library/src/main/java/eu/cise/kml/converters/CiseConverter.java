package eu.cise.kml.converters;

import de.micromata.opengis.kml.v_2_2_0.Document;
import eu.cise.converters.exceptions.ConversionException;
import eu.cise.servicemodel.v1.message.Message;

/**
 * This interface dicatates that the implementing class must be able to translate through the {@link #translate(Message, Object, Document)} method
 * a generic K type object which is part of the payload of a CISE Message into an appropriate object and add it to the {@link Document} object provided
 *
 * The interface also allows for the definition of the K generic simple name as a result of the {@link #getName()} method so that it can be used by the {@link CiseToKMLConverters}
 * through reflection to identify the class to translate the current payload object of the CISE Message
 *
 * @param <K> the generic type that this converter can translate
 */
public interface CiseConverter<K> {

    /**
     * Main method of the CiseConverter interface. It allows for the translation of the K generic type {@code currentPayloadAny} into a KML type to
     * be added appropriately to the {@code kmlMessage}. Any further information required for the translation can be obtained through the {@code ciseMessage} object
     * @param ciseMessage The CISEMessage being translated
     * @param currentPayloadAny The current payload Any that should be translated by this converter
     * @param kmlMessage the resulting KML Document to append the result of the translation to
     * @throws ConversionException exception thrown when an appripriate converter is not found
     */
    void translate(Message ciseMessage, K currentPayloadAny, Document kmlMessage) throws ConversionException;

    /**
     * Helper method so that the {@link CiseToKMLConverters} class can use reflection to identify based on the type of the current Payload Any being translated
     * which converter it should instantiate
     * @return the conversion class SimpleName
     */
    String getName();


}
