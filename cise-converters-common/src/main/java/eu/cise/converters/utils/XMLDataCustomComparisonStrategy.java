package eu.cise.converters.utils;

import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;

/**
 * Interface for defining custom comparison strategies in XML data comparison.
 * It allows for the implementation of custom logic to be applied during
 * the comparison of XML nodes, attributes, or values in a fine-grained and specific manner.
 *
 * <p>Implementations of this strategy are used in XML comparisons where standard comparison
 * mechanisms may not suffice, such as when ignoring certain differences or applying specific
 * comparison rules for certain XML structures or values.
 *
 * <p>This strategy is particularly used in the {@link XMLDataUtils#getXMLsDifference} method,
 * where it provides a means to apply custom comparison logic as part of the XML comparison process.
 * It allows for the customization of how two XML documents are compared, especially in cases where
 * certain differences should be ignored or handled differently.
 *
 * @see XMLDataUtils#getXMLsDifference for how this strategy is applied in practical XML comparison scenarios.
 */
public interface XMLDataCustomComparisonStrategy {

    /**
     * Applies the custom comparison strategy to a given XMLUnit comparison.
     * This method should determine how the comparison should be evaluated (identical, different, similar)
     * based on the custom logic defined in the implementation.
     *
     * @param comparison The XMLUnit comparison object representing the specific elements, attributes,
     *                   or values being compared in the XML documents.
     * @return A ComparisonResult indicating the outcome of the comparison (identical, different, similar)
     *         based on the custom strategy.
     */
    ComparisonResult apply(Comparison comparison);
}