package eu.cise.ivef.translators.mappers;

import java.util.HashMap;

/**
 * Represents an abstract bidirectional map between two types of data, facilitating
 * the translation and mapping of data elements between IVEF and CISE standards.
 * Class is designed to be extended for specific data types (like Cargo types or Ship Types)
 *
 * <p>Implementations of this class should define specific mappings and provide methods
 * for retrieving metadata keys and comparing values relevant to the data types being mapped.
 *
 * @param <I> the type representing IVEF side of the mapping
 * @param <C> the type representing CISE side of the mapping
 */
public abstract class ImmutableBidiMultiMap<I, C> {

    private final HashMap<I, C> mapIVEF2CISE = new HashMap<>();
    private final HashMap<C, I> mapCISE2IVEF = new HashMap<>();

    private I defaultIVEFValue;
    private C defaultCISEValue;

    /**
     * Retrieves the default value on the IVEF side of the mapping. This value is used
     * when a corresponding mapping is not found.
     *
     * @return the default IVEF value
     */
    public I getDefaultIVEFValue() {
        return defaultIVEFValue;
    }

    /**
     * Sets the default IVEF value for cases where a direct mapping is not found.
     *
     * @param defaultIVEFValue the default value to be used on the IVEF side
     */
    void setDefaultIVEFValue(I defaultIVEFValue) {
        this.defaultIVEFValue = defaultIVEFValue;
    }

    /**
     * Retrieves the default value on the CISE side of the mapping. This value is used
     * when a corresponding mapping is not found.
     *
     * @return the default CISE value
     */
    public C getDefaultCISEValue() {
        return defaultCISEValue;
    }

    /**
     * Sets the default CISE value for cases where a direct mapping is not found.
     *
     * @param defaultCISEValue the default value to be used on the CISE side
     */
    void setDefaultCISEValue(C defaultCISEValue) {
        this.defaultCISEValue = defaultCISEValue;
    }

    /**
     * This method adds the input pair values to both the internal maps switching the values.
     * You must pay attention to the order in which pairs are added to the maps because HashMaps retain the last value of an already present key.
     * For example: If we have in a map the (a,b) pair, and we add the (a,c) the HashMap retain only the pair (a,c).
     *
     * @param valueForIVEF the IVEF value to be mapped
     * @param valueForCISE the corresponding CISE value to be mapped
     */
    public void put(I valueForIVEF, C valueForCISE) {
        mapIVEF2CISE.put(valueForIVEF, valueForCISE);
        mapCISE2IVEF.put(valueForCISE, valueForIVEF);
    }

    /**
     * Retrieves the corresponding IVEF value for a given CISE value, facilitating the translation
     * from CISE to IVEF format.
     *
     * @param valCISE the CISE value for which to find the corresponding IVEF value
     * @return the corresponding IVEF value
     */
    public I getIVEFValue(C valCISE) {
        return mapCISE2IVEF.get(valCISE);
    }

    /**
     * Retrieves the corresponding CISE value for a given IVEF value, facilitating the translation
     * from IVEF to CISE format.
     *
     * @param valIVEF the IVEF value for which to find the corresponding CISE value
     * @return the corresponding CISE value
     */
    public C getCISEValue(I valIVEF) {
        return mapIVEF2CISE.get(valIVEF);
    }

    /**
     * Checks if the provided CISE value is the default mapping value.
     *
     * @param valueCISE the CISE value to check
     * @return true if it is the default CISE value, false otherwise
     */
    public boolean isDefaultCiseType(C valueCISE) {
        return valueCISE.equals(getDefaultCISEValue());
    }

    /**
     * Checks if the provided IVEF value is the default mapping value.
     *
     * @param valueIVEF the IVEF value to check
     * @return true if it is the default IVEF value, false otherwise
     */
    public boolean isDefaultIVEFType(I valueIVEF) {
        return valueIVEF.equals(getDefaultIVEFValue());
    }

    /**
     * Gets the key for metadata comments specific to CISE data.
     *
     * @return the metadata comments key for CISE
     */
    public abstract String getCISEMetadataCommentsKey();

    /**
     * Gets the key for tagged items specific to IVEF data.
     *
     * @return the tagged item key for IVEF
     */
    public abstract String getIVEFTaggedItemKey();

    /**
     * Converts a string value to its corresponding CISE enum value.
     *
     * @param stringValueCISE the string value to convert to CISE enum
     * @return the corresponding CISE enum value
     */
    public abstract C getCISEEnumFromStringValue(String stringValueCISE);

    /**
     * Compares an IVEF value with a CISE value to check if they are equivalent.
     *
     * @param valueIVEF the IVEF value to compare
     * @param valueCISE the CISE value to compare
     * @return true if equivalent, false otherwise
     */
    public abstract boolean compareValues(I valueIVEF, C valueCISE);
}
