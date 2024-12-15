package eu.cise.ais.config;

import eu.cise.datamodel.v1.entity.organization.Organization;

/**
 * Default implementation of AisConverterConfiguration with the most common configuration values.
 */
public class DefaultAisConverterConfiguration implements AisConverterConfiguration {
    /**
     * Default implementation that returns a basic Organization with name "Organization Legal Name" and alternative name "Organization Alternative Name"
     * @return Organization
     */
    public  Organization getTestOrganization() {
        Organization organization = new Organization();
        organization.setLegalName("Organization Legal Name");
        organization.setAlternativeName("Organization Alternative Name");
        return organization;
    }

    /**
     * Default implementation that returns false (does not Override any timestamps)
     * @return boolean
     */
    public  boolean getOverrideTimestamps(){
        return false;
    }

    /**
     * Default implentation that return true (deletes Unavailable Locations e.g. locations that equal (longitude == 181 || latitude == 91)
     * @return boolean
     */
    public  boolean getDeleteUnavailableLocation(){
        return true;
    }
}
