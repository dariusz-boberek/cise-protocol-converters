package eu.cise.kml;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import eu.cise.converters.exceptions.ConversionException;
import eu.cise.kml.converters.CiseToKMLConverters;
import eu.cise.servicemodel.v1.message.EncryptedEntityPayload;
import eu.cise.servicemodel.v1.message.Message;
import eu.cise.servicemodel.v1.message.XmlEntityPayload;
import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The KMLConverter is the main entrypoint class of the kml-converter-library.
 * When instantiated, it encapsulates a {@link Kml} document and it enriches it with the
 * information inside the {@link Message} provided through the {@link #transformToKml(Message)}
 */
public class KMLConverter {


    private final Kml kmlMessage;
    private final Document document;
    private static final Logger logger = LoggerFactory.getLogger(KMLConverter.class);

    /**
     * Basic constructor that creates a {@link Kml} message and initializes its {@link Document}
     */
    public KMLConverter() {
        kmlMessage = new Kml();
        document = kmlMessage.createAndSetDocument();
    }

    /**
     * Main method of the KMLConverter. It accepts a CISE message and goes through its payload anies.
     * For each one type of Payload, the apporpriate converter is called (if found) and it translates the CISE payload
     * into an appropriate object for KML.
     *
     * E.g. if provided with a CISE message that contains x number of Vessels, these Vessels will be translated through the
     * {@link eu.cise.kml.converters.VesselConverter} into {@link de.micromata.opengis.kml.v_2_2_0.Placemark} for KML
     *
     * @param ciseMessage the message to convert its payload into KML entities added directly to the Kml document returned
     * @return the KmlDocument encapsulated by this class and enriched through the CISE Message payload
     */
    public Kml transformToKml(Message ciseMessage) {
        if (ciseMessage.getPayload() instanceof EncryptedEntityPayload) {
            this.logger.info("Payload instanceof EncryptedEntityPayload. Nothing to do");
            return kmlMessage;

        } else if (ciseMessage.getPayload() instanceof XmlEntityPayload) {
            this.logger.debug("Payload instanceof XmlEntityPayload");
            XmlEntityPayload xmlEntityPayload = (XmlEntityPayload) ciseMessage.getPayload();
            for (Object obj : xmlEntityPayload.getAnies()) {
                String currentClass = obj.getClass().getSimpleName();
                try {
                    CiseToKMLConverters.getTranslator(currentClass).translate(ciseMessage, obj, document);
                }
                catch(ConversionException ex){
                    logger.error("Unable to eu.cise.ais.translate payload type ",ex);
                }
            }

        } else {
            throw new NotImplementedException("KML Translation for " + ciseMessage.getPayload().getClass().getName() + " is not implemented");
        }
        return kmlMessage;
    }

}



