package eu.cise.ivef.translators.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * This is a utility class that converts naval speed from Knot to MeterPerSecond and vice versa.
 */
public class SpeedConverter {

    private static final BigDecimal SPEED_CONVERSION_TOLERANCE = new BigDecimal("1E-17");
    private static final BigDecimal KNOTS_TO_MPS_CONVERSION_FACTOR = new BigDecimal("0.51444444444444445");

    /**
     * Converts a speed from knots to meters per second.
     *
     * @param knots the speed in knots as a Double. If null, the method returns null.
     * @return the speed in meters per second as a Double, rounded to the nearest value within the defined tolerance.
     */
    public static Double convertKnotsToMetersPerSecond(Double knots) {
        if (knots == null) {
            return null;
        }
        BigDecimal bdKnots = BigDecimal.valueOf(knots);
        BigDecimal msPerSecond = bdKnots.multiply(KNOTS_TO_MPS_CONVERSION_FACTOR);
        msPerSecond = msPerSecond.divide(SPEED_CONVERSION_TOLERANCE, 0, RoundingMode.HALF_UP);
        msPerSecond = msPerSecond.multiply(SPEED_CONVERSION_TOLERANCE);
        return msPerSecond.doubleValue();
    }

    /**
     * Converts a speed from meters per second to knots.
     *
     * @param sog the speed in meters per second as a BigDecimal. If null, the method returns null.
     * @return the speed in knots as a Double, rounded to the nearest value within the defined tolerance.
     */
    public static Double convertMetersPerSecondToKnots(BigDecimal sog) {
        if (sog == null) {
            return null;
        }
        BigDecimal knots = sog.divide(KNOTS_TO_MPS_CONVERSION_FACTOR, 17, RoundingMode.HALF_UP);
        knots = knots.divide(SPEED_CONVERSION_TOLERANCE, 0, RoundingMode.HALF_UP);
        knots = knots.multiply(SPEED_CONVERSION_TOLERANCE);
        return knots.doubleValue();
    }
}