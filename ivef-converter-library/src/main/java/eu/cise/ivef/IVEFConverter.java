package eu.cise.ivef;

import eu.cise.converters.exceptions.ConversionException;
import eu.cise.servicemodel.v1.message.Message;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * The IVEFConverter interface declares methods for converting CISE Messages to IVEF format and vice-versa.
 * It is a generic interface where C represents the type of the CISE message and O is the type of the IVEF message.
 *
 * @param <C> Type of the CISE message to be converted to IVEF.
 * @param <O> Type of the IVEF message to be converted to a CISE message.
 */
public interface IVEFConverter<C extends Message, O> {

    /**
     * Converts a CISE message to an IVEF message.
     *
     * @param source The CISE message to be converted.
     * @return A Pair where the left element is the converted IVEF message and the right element is a list of
     *          ConversionExceptions that were encountered during conversion. Even if ConversionExceptions occur
     *          during the conversion of individual vessels, the method will continue to process the remaining
     *          vessels and collect all the ConversionExceptions. If the payload is not an instance of
     *          XmlEntityPayload, null is returned as the IVEF message and an exception message is added to the list.
     */
    Pair<O, List<ConversionException>> convertToIvef(C source);

    /**
     * Converts an IVEF message to a CISE message.
     *
     * @param target The IVEF message to be converted.
     * @return A Pair where the left element is the converted CISE message and the right element is a list of
     *          ConversionExceptions that were encountered during conversion. Even if ConversionExceptions occur
     *          during the conversion of individual vessels, the method will continue to process the remaining
     *          vessels and collect all the ConversionExceptions.
     */
    Pair<C, List<ConversionException>> convertFromIvef(O target);

}
