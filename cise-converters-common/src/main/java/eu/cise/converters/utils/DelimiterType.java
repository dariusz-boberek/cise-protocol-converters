package eu.cise.converters.utils;

/**
 * Enum representing types of delimiters used in parsing and processing data streams.
 *
 * <p>This enumeration is used in scenarios where precise control over the treatment of delimiters
 * in data streams is necessary, ensuring data integrity and correct parsing behavior.
 */
public enum DelimiterType {
    /**
     * STRIP option should be used when the delimiter should be removed from the resulting String
     */
    STRIP("strip"),
    /**
     * KEEP option should be used when the delimiter should be kept in the resulting String
     */
    KEEP("value");

    private final String value;

    DelimiterType(String value) {
        this.value = value;
    }
}
