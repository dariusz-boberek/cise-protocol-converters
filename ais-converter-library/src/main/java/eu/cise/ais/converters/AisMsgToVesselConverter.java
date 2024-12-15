package eu.cise.ais.converters;

import eu.cise.ais.Translator;
import eu.cise.ais.config.AisConverterConfiguration;
import eu.cise.ais.config.DefaultAisConverterConfiguration;
import eu.cise.ais.core.AisMsg;
import eu.cise.datamodel.v1.entity.Entity;
import eu.cise.datamodel.v1.entity.uniqueidentifier.UniqueIdentifier;
import eu.cise.datamodel.v1.entity.vessel.Vessel;

import java.util.Optional;
import java.util.UUID;

/**
 * This converter an converts an {@link AisMsg} object into a optional {@link Entity} model. The entity model is the
 * ancestor class of a Vessel. This class is part of the chain of transformations
 * flow of AIS strings into a corresponding number of HTTP requests containing all the vessels
 * information.
 */
public class AisMsgToVesselConverter implements Translator<AisMsg, Optional<Entity>> {

    private final Message5Converter message5Translator;
    private final Message123Converter message123Translator;
    private final AisConverterConfiguration configuration;

    /**
     * Constructor accepting the config as a collaborator.
     * <p>
     * TODO the constructor is creating objects and it should be done in the main partition. A better
     * design would foresee a factory object using the correct implementation of the given message.
     * Another way to implement it could be through a chain of responsibility.
     *
     * @param configuration the converter configuration
     */
    public AisMsgToVesselConverter(AisConverterConfiguration configuration) {
        this.configuration = configuration;
        message123Translator = new Message123Converter(new DefaultAisConverterConfiguration());
        message5Translator = new Message5Converter();
    }

    /**
     * The {@link #translate(AisMsg)} method is using the message as a selector to choose the right translator.
     *
     * @param message the ais message to be translated
     * @return the translated entity
     */
    @Override
    public Optional<Entity> translate(AisMsg message) {
        try {
            return selectMsgTranslator(message).map(t -> t.translate(message)).map(v -> setNewIdentifier(v, message));

        } catch (Exception e) {
            // if it's not able to translate the message just skip it
            return Optional.empty();
        }
    }

    /**
     * Set a new identifier in the vessel entity with the organization field valued with the values
     * legalName and AlternativeName taken from a config file.
     *
     * @param vessel the vessel that should receive the identifier
     * @return the vessel with the identifier
     */
    private Vessel setNewIdentifier(Vessel vessel, AisMsg message) {
        vessel.setIdentifier(getUniqueIdentifier(message));
        return vessel;
    }

    private UniqueIdentifier getUniqueIdentifier(AisMsg message) {
        UniqueIdentifier uuid = new UniqueIdentifier();
        uuid.setUUID(ensureUUID(message));
        uuid.setGeneratedBy(configuration.getTestOrganization());
        return uuid;
    }

    private String ensureUUID(AisMsg message) {
        return message.getUserId() != 0 ? String.valueOf(message.getUserId()) : UUID.randomUUID().toString();
    }

    private Optional<Translator<AisMsg, Vessel>> selectMsgTranslator(AisMsg message) {
        if (isMessageOfType123(message)) {
            return Optional.of(message123Translator);
        } else if (isMessageOfType5(message)) {
            return Optional.of(message5Translator);
        } else {
            return Optional.empty();
        }
    }

    private boolean isMessageOfType5(AisMsg message) {
        return message.getMessageType() == 5;
    }

    private boolean isMessageOfType123(AisMsg message) {
        return message.getMessageType() == 1 || message.getMessageType() == 2 || message.getMessageType() == 3;
    }

}
