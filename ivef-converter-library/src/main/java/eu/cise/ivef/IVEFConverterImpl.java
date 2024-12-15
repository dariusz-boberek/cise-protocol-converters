package eu.cise.ivef;

import eu.cise.converters.exceptions.ConversionException;
import eu.cise.datamodel.v1.entity.Entity;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.ivef.generated.Header;
import eu.cise.ivef.generated.MSGVesselData;
import eu.cise.ivef.generated.VesselData;
import eu.cise.ivef.translators.CiseDataTranslator;
import eu.cise.ivef.translators.TranslatorRegistry;
import eu.cise.ivef.translators.VesselDataTranslator;
import eu.cise.servicemodel.v1.authority.SeaBasinType;
import eu.cise.servicemodel.v1.message.InformationSecurityLevelType;
import eu.cise.servicemodel.v1.message.InformationSensitivityType;
import eu.cise.servicemodel.v1.message.Message;
import eu.cise.servicemodel.v1.message.PriorityType;
import eu.cise.servicemodel.v1.message.PurposeType;
import eu.cise.servicemodel.v1.message.Push;
import eu.cise.servicemodel.v1.message.XmlEntityPayload;
import eu.cise.servicemodel.v1.service.DataFreshnessType;
import eu.cise.servicemodel.v1.service.ServiceOperationType;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static eu.cise.servicemodel.v1.service.ServiceType.VESSEL_SERVICE;
import static eu.eucise.helpers.ParticipantBuilder.newParticipant;
import static eu.eucise.helpers.PushBuilder.newPush;
import static eu.eucise.helpers.ServiceBuilder.newService;

/**
 * The IVEFConverterImpl class implements the IVEFConverter interface.
 * It provides methods for converting CISE Messages to IVEF messages and vice-versa.
 * This implementation focuses on the translation of vessel-related data.
 */
public class IVEFConverterImpl implements IVEFConverter<Message, MSGVesselData> {

    private static final Logger logger = LoggerFactory.getLogger(IVEFConverterImpl.class);

    /**
     * Implementation-specific conversion of a CISE message (of vessel-related data) to an IVEF message.
     * It also manages conversion exceptions, accumulating them for a comprehensive error report.
     *
     * @param ciseMessage The CISE message to be converted.
     * @return A pair containing the converted IVEF message and a list of conversion exceptions, if any.
     */
    @Override
    public Pair<MSGVesselData, List<ConversionException>> convertToIvef(Message ciseMessage) {
        List<ConversionException> conversionExceptions = new ArrayList<>();
        if (!(ciseMessage.getPayload() instanceof XmlEntityPayload)) {
            //TODO: this mapping need to be revisited - ciseMessage.getPayload() instanceof EncryptedEntityPayload or other cases
            conversionExceptions.add(new ConversionException("Not implemented"));
            return Pair.of(null, conversionExceptions);
        }
        MSGVesselData ivefMessage = new MSGVesselData();
        Header ivefHeader = new Header();
        ivefHeader.setVersion("0.1.5");
        ivefHeader.setMsgRefId("c30f8b9a-9ecc-4470-9ba8-0b0c80a95b19");
        ivefMessage.setHeader(ivefHeader);
        MSGVesselData.Body ivefBody = new MSGVesselData.Body();
        ivefMessage.setBody(ivefBody);
        List<VesselData> vesselDataList = ivefBody.getVesselData();
        XmlEntityPayload xmlEntityPayload = (XmlEntityPayload) ciseMessage.getPayload();
        for (Object ciseElement : xmlEntityPayload.getAnies()) {
            try {
                String simpleName = ciseElement.getClass().getSimpleName();
                CiseDataTranslator translator = TranslatorRegistry.getTranslator(simpleName);
                VesselData vesselData = (VesselData) translator.translate((Entity) ciseElement);
                vesselDataList.add(vesselData);
            } catch (ConversionException exception) {
                conversionExceptions.add(exception);
                logger.error(exception.getMessage(), exception);
            }
        }
        return Pair.of(ivefMessage, conversionExceptions);
    }

    /**
     * Implementation-specific conversion of an IVEF message to a CISE message (of vessel-related data).
     *
     * @param ivefMessage The IVEF message to be converted.
     * @return A pair containing the converted CISE message and a list of conversion exceptions, if any.
     */
    @Override
    public Pair<Message, List<ConversionException>> convertFromIvef(MSGVesselData ivefMessage) {
        List<Vessel> ciseVessels = new ArrayList<>(ivefMessage.getBody().getVesselData().size());
        List<ConversionException> conversionExceptions = new ArrayList<>();

        VesselDataTranslator vesselDataTranslator;
        try {
            vesselDataTranslator = new VesselDataTranslator();
        } catch (ConversionException exception) {
            logger.error(exception.getMessage(), exception);
            conversionExceptions.add(exception);
            return Pair.of(null, conversionExceptions);
        }

        for (VesselData ivefVessel : ivefMessage.getBody().getVesselData()) {
            try {
                Vessel ciseVessel = vesselDataTranslator.translate(ivefVessel);
                ciseVessels.add(ciseVessel);
            } catch (ConversionException exception) {
                logger.error(exception.getMessage(), exception);
                conversionExceptions.add(exception);
            }
        }

        //TODO: we should return only the Payload and not a entire CISE Messasge
        Push convertedMessage = newPush()
                .id(UUID.randomUUID().toString())
                .contextId(UUID.randomUUID().toString())
                .correlationId(UUID.randomUUID().toString())
                .creationDateTime(new Date())
                .sender(newService()
                        .id("TEMP_SERVICEID")
                        .type(VESSEL_SERVICE)
                        .dataFreshness(DataFreshnessType.UNKNOWN)
                        .seaBasin(SeaBasinType.MEDITERRANEAN)
                        .operation(ServiceOperationType.PUSH)
                        .participant(newParticipant().endpointUrl("TEMP_ENDPOINTURL")))
                .priority(PriorityType.HIGH)
                .isRequiresAck(false)
                .informationSecurityLevel(
                        InformationSecurityLevelType.NON_SPECIFIED)
                .informationSensitivity(InformationSensitivityType.NON_SPECIFIED)
                .isPersonalData(false)
                .purpose(PurposeType.NON_SPECIFIED)
                .addEntities(ciseVessels)
                .build();

        return Pair.of(convertedMessage, conversionExceptions);
    }

}