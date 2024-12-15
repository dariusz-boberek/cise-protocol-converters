package eu.cise.ais.config;

import eu.cise.datamodel.v1.entity.organization.Organization;

/**
 * Interface that holds the required methods to be implemented by a Configuration required for the following classes:
 * {@link eu.cise.ais.converters.AisMsgToVesselConverter}
 * {@link eu.cise.ais.converters.Message123Converter}
 */
public interface AisConverterConfiguration {
    /**
     * Configuration method that dictates the {@link Organization} to use when converting an AisMsg to a CISE Vessel
     * @return
     */
    Organization getTestOrganization();

    /**
     * Boolean configuration on whether the translation should override the existing timestamps from the message to the current time or not
     * @return
     */
    boolean getOverrideTimestamps();

    /**
     * Boolean configuration on whether the translation should not include default locations (longitude == 181 || latitude == 91) or not. If set to true similar
     * locations will not be translated into a CISE Vessel Location
     * @return
     */
    boolean getDeleteUnavailableLocation();
}
