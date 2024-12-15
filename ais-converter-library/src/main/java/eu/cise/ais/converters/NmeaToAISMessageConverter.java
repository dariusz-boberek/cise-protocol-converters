package eu.cise.ais.converters;



import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.ais.messages.Metadata;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;
import eu.cise.ais.Translator;
import eu.cise.converters.exceptions.ConversionException;


import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

/**
 * This class is used to convert a {@link NMEAMessage} into and Optional of {@link AISMessage}
 */
public class NmeaToAISMessageConverter implements Translator<NMEAMessage, Optional<AISMessage>> {


    private final String source;
    private final ArrayList<NMEAMessage> messageFragments = new ArrayList<>();
    private Clock clock;

    /**
     * This constructor takes as argument the source NMEA string to append it to the metadata of the
     * generated {@link AISMessage}.
     * When this constructor is used the Clock used for the timestamps conversion is the Clock.systemUTC
     * @param source The NMEA String to be used as metadata source
     */
    public NmeaToAISMessageConverter(String source) {
        this(source, Clock.systemUTC());
    }

    /**
     * Base constructor for this converter
     * @param source The NMEA String to be used as metadata source
     * @param clock The clock to use for timestamps conversions
     */
    public NmeaToAISMessageConverter(String source, Clock clock) {
        this.source = source;
        this.clock = clock;
    }

    @Override
    public Optional<AISMessage> translate(NMEAMessage nmeaMessage) {
        try {
            if (!nmeaMessage.isValid()) {
                throw new ConversionException("NMEA to AISMessage transformation error");
            }

            int numberOfFragments = nmeaMessage.getNumberOfFragments();
            if (numberOfFragments <= 0) {
                messageFragments.clear();
                return Optional.empty();
            }

            if (numberOfFragments == 1) {
                messageFragments.clear();
                return Optional
                    .of(AISMessage.create(new Metadata(source, Instant.now(clock)), nmeaMessage));
            }

            int fragmentNumber = nmeaMessage.getFragmentNumber();
            if (fragmentNumber < 0) {
                messageFragments.clear();
                return Optional.empty();
            }

            if (fragmentNumber > numberOfFragments) {
                messageFragments.clear();
                return Optional.empty();
            }

            int expectedFragmentNumber = messageFragments.size() + 1;
            if (expectedFragmentNumber != fragmentNumber) {
                messageFragments.clear();
                return Optional.empty();
            }

            messageFragments.add(nmeaMessage);

            if (nmeaMessage.getNumberOfFragments() == messageFragments.size()) {
                AISMessage aisMessage
                    = AISMessage.create(new Metadata(source),
                    messageFragments.toArray(new NMEAMessage[messageFragments.size()]));

                messageFragments.clear();
                return Optional.of(aisMessage);
            }

            return Optional.empty();

        } catch (Exception e) {
            // It catches possible exceptions like InvalidMessage or similar
            return Optional.empty();
        }
    }
}
