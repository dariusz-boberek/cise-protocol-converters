package eu.cise.ais.converters;

import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;
import eu.cise.ais.Translator;
import eu.cise.converters.exceptions.ConversionException;


/**
 * This class is used to convert a NMEA string to {@link NMEAMessage}
 */
public class StringToNmeaConverter implements Translator<String, NMEAMessage> {

    @Override
    public NMEAMessage translate(String nmeaString) {
        try {
            return NMEAMessage.fromString(nmeaString);
        } catch (Exception e) {
            throw new ConversionException("|string->NMEA|error|" + nmeaString, e);
        }
    }
}
