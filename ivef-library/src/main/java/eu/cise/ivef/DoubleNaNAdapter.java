package eu.cise.ivef;

/**
 * The {@code DoubleNaNAdapter} class is used for handling special cases in XML data binding,
 * particularly for the 'RateOfTurn' attribute in the 'PosReport' element.
 * It provides static methods to convert between {@code String} and {@code Double} values,
 * with specific handling for 'NaN' (Not a Number) cases.
 *
 * <p>This adapter is specified in the global.xjb bindings for the 'PosReport' element,
 * ensuring the correct treatment of {@code Double} values when converting to and from XML.
 */
public class DoubleNaNAdapter {

    /**
     * Converts a string to a Double.
     * If the string is null or "NaN", it returns {@code Double.NaN}.
     * Otherwise, it parses the string as a double value.
     *
     * @param value the string to be converted to Double
     * @return the corresponding Double value, or {@code Double.NaN} if the input is null or "NaN"
     * @throws NumberFormatException if the string does not contain a parsable double
     */
    public static Double parse(String value) {
        if (value == null || value.equalsIgnoreCase("NaN")) {
            return Double.NaN;
        } else {
            return Double.parseDouble(value);
        }
    }

    /**
     * Converts a Double to its string representation.
     * Returns "NaN" if the double value is {@code Double.NaN}.
     * If the double value is null, it returns null.
     *
     * @param value the Double to be converted to string
     * @return the string representation of the Double value, "NaN" if it's {@code Double.NaN}, or null if the value is null
     */
    public static String print(Double value) {
        if (value == null) {
            return null;
        } else if (Double.isNaN(value)) {
            return "NaN";
        } else {
            return value.toString();
        }
    }
}
