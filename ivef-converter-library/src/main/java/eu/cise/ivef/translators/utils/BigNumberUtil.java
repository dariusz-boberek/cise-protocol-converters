package eu.cise.ivef.translators.utils;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * This is a utility class that allows the transformation of Object Values of different types to Big Numbers
 * (Decimal or Integer).
 */
public class BigNumberUtil {

    /**
     * Converts a Number-derived object to BigInteger. Returns null if the input is null.
     *
     * @param value The Number object to convert.
     * @param <T>   The type of the Number object.
     * @return BigInteger representation of the input, or null if the input is null.
     */
    public static <T extends Number> BigInteger toBigInteger(T value) {
        return value != null ? new BigInteger(value.toString()) : null;
    }

    /**
     * Converts a Number-derived object to BigDecimal. Returns null if the input is null.
     *
     * @param value The Number object to convert.
     * @param <T>   The type of the Number object.
     * @return BigDecimal representation of the input, or null if the input is null.
     */
    public static <T extends Number> BigDecimal toBigDecimal(T value) {
        return value != null ? new BigDecimal(value.toString()) : null;
    }

    /**
     * Converts a String to BigDecimal. Returns null if the input is null.
     *
     * @param value The String to convert.
     * @return BigDecimal representation of the input, or null if the input is null.
     */
    public static BigDecimal toBigDecimal(String value) {
        return value != null ? new BigDecimal(value) : null;
    }

    /**
     * Converts a BigDecimal to Double. Returns null if the input is null.
     *
     * @param value The BigDecimal to convert.
     * @return Double representation of the input, or null if the input is null.
     */
    public static Double bigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }

    /**
     * Converts a BigDecimal to a String. Returns null if the input is null.
     *
     * @param value The BigDecimal to convert.
     * @return String representation of the BigDecimal, or null if the input is null.
     */
    public static String bigDecimalToString(BigDecimal value) {
        return value != null ? value.toPlainString() : null;
    }

    /**
     * Converts a BigInteger to a String. Returns null if the input is null.
     *
     * @param value The BigInteger to convert.
     * @return String representation of the BigInteger, or null if the input is null.
     */
    public static String bigIntegerToString(BigInteger value) {
        return value != null ? value.toString() : null;
    }

}
