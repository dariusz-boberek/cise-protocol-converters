package eu.cise.ivef.translators;

import eu.cise.converters.exceptions.ConversionException;
import eu.cise.datamodel.v1.entity.Entity;

/**
 * The CiseDataTranslator interface is a contract for translating data between two types:
 * a CISE Entity (denoted as C) and another object type (denoted as I).
 *
 * <p>This interface provides methods for translating data in both directions - from CISE Entity to I and from I back to CISE Entity.
 * It encapsulates the logic required for such translations, ensuring a consistent approach across different implementations.
 *
 * <p>For instance, a class implementing this interface could be a VesselDataTranslator that translates between
 * Vessel (a CISE Entity) and VesselData (the I type),
 * handling fields such as location, navigational status, ship type, and voyage data.
 *
 * @param <C> The type of the CISE Entity being translated.
 * @param <I> The type of the object that the CISE Entity is being translated to/from.
 */
public interface CiseDataTranslator<C extends Entity, I> {

    /**
     * Translates a CISE Entity and a Message into an object of type I.
     *
     * @param ciseObject The CISE Entity to be translated.
     * @return An object of type I resulting from the translation.
     * @throws ConversionException If any problems are encountered during the conversion process.
     */
    I translate(C ciseObject) throws ConversionException;

    /**
     * Translates an object of type I into a CISE Entity.
     *
     * @param ivefObject The object to be translated into a CISE Entity.
     * @return A CISE Entity resulting from the translation.
     */
    C translate(I ivefObject);

    /**
     * Returns the name of the translator.
     * The name is used for identification aiding management of different translator instances.
     *
     * @return A string representing the name of the translator.
     */
    String getName();
}