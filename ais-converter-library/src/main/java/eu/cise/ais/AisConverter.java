package eu.cise.ais;

import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;
import eu.cise.ais.config.DefaultAisConverterConfiguration;
import eu.cise.ais.converters.AisMessageToAisMsgConverter;
import eu.cise.ais.converters.AisMsgToVesselConverter;
import eu.cise.ais.converters.NmeaToAISMessageConverter;
import eu.cise.ais.converters.StringToNmeaConverter;
import eu.cise.ais.core.*;
import eu.cise.datamodel.v1.entity.Entity;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * This class is the main entrypoint for the conversions between NMEA Strings to {@link AisMsg} of an aisString into a {@link Vessel}. Ais and NMEA Strings are used
 * interchangeably
 */
public class AisConverter {

    private static final Logger logger = LoggerFactory.getLogger(AisConverter.class);

    /**
     * Helper public method to use as an intermediate step of an AIS / NMEA string translation into a {@link Vessel}. This method is actually called by
     * the {@link #translateToCiseVessel(String)} method as an intermediate step.
     * @param nmeaString the NMEA / AIS String to translate
     * @return
     */
    public AisMsg translateNMEAStringToAISMessage(String nmeaString){
        logger.debug("Translating nmea string: {}", nmeaString);
        StringToNmeaConverter stringToNmea = new StringToNmeaConverter();
        NmeaToAISMessageConverter nmeaToAISMessage = new NmeaToAISMessageConverter("SRC");
        AisMessageToAisMsgConverter aisMessageToAisMsg = new AisMessageToAisMsgConverter();

        // translate
        NMEAMessage nmeaMessage = stringToNmea.translate(nmeaString);
        Optional<AISMessage> aisMessage = nmeaToAISMessage.translate(nmeaMessage);
        Optional<AisMsg> aisMsg = aisMessageToAisMsg.translate(aisMessage.get());
        return aisMsg.get();
    }

    /**
     * This is the main method of the conversion of an AIS String into a CISE Vessel
     * @param aisString the AIS / NMEA String to convert
     * @return
     */
    public Vessel translateToCiseVessel(String aisString){
        AisMsg aisMsg = translateNMEAStringToAISMessage(aisString);
        AisMsgToVesselConverter aisMsgToVessel = new AisMsgToVesselConverter(new DefaultAisConverterConfiguration());
        Optional<Entity> vesselEntity = aisMsgToVessel.translate(aisMsg);
        return (Vessel) vesselEntity.get();
    }



}
