package eu.cise.converters.utils;


import eu.cise.converters.exceptions.ConversionException;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.location.Location;
import eu.cise.datamodel.v1.entity.object.Objet;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.servicemodel.v1.message.Push;
import eu.cise.servicemodel.v1.message.XmlEntityPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Comparison;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.ComparisonType;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.Difference;
import org.xmlunit.diff.DifferenceEvaluator;

import javax.xml.XMLConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Utility class for XML data processing.
 * This class provides common functionalities for handling and manipulating XML data
 * used across tests mainly to assess if XMLs are the same or if they conform to XSD.
 */
public class XMLDataUtils {

    public static final double EQUALITY_TOLERANCE = 1e-14;
    private static final Logger logger = LoggerFactory.getLogger(XMLDataUtils.class);

    /**
     * Converts an Instant to an XMLGregorianCalendar date representation.
     * This method uses the UTC time zone for the conversion.
     *
     * @param instant The Instant to be converted.
     * @return An XMLGregorianCalendar representing the date component of the provided Instant.
     */
    public static XMLGregorianCalendar xmlDate(Instant instant) {
        ZonedDateTime zdt = instant.atZone(ZoneId.of("UTC"));
        return xmlDate(zdt.getYear(), zdt.getMonthValue(), zdt.getDayOfMonth());
    }

    /**
     * Creates an XMLGregorianCalendar instance representing a specific date.
     * This method constructs an XMLGregorianCalendar with the specified year, month, and day,
     * setting the time to the start of the day (midnight) in the UTC time zone.
     *
     * @param year  The year component of the date.
     * @param month The month component of the date, with 1 representing January and 12 representing December.
     * @param day   The day component of the date.
     * @return An XMLGregorianCalendar instance representing the specified date.
     * @throws ConversionException if there is an error in creating the XMLGregorianCalendar instance.
     */
    public static XMLGregorianCalendar xmlDate(int year, int month, int day) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(year, month, day, 0, 0, 0, 0, 0);
        } catch (DatatypeConfigurationException e) {
            throw new ConversionException(e);
        }
    }

    /**
     * Converts an Instant to an XMLGregorianCalendar time representation.
     * This method focuses on the time component and uses the UTC time zone for conversion.
     *
     * @param instant The Instant to be converted.
     * @return An XMLGregorianCalendar representing the time component of the provided Instant.
     */
    public static XMLGregorianCalendar xmlTime(Instant instant) {
        ZonedDateTime zdt = instant.atZone(ZoneId.of("UTC"));
        return xmlTime(zdt.getHour(), zdt.getMinute(), zdt.getSecond());
    }

    /**
     * Creates an XMLGregorianCalendar instance representing a specific time.
     * This method constructs an XMLGregorianCalendar with the specified hour, minute, and second,
     * setting the date to a default value (January 1, 1970) and time zone to UTC.
     * Primarily used for time component representation in XML data structures.
     *
     * @param hour   The hour component of the time, from 0 to 23.
     * @param minute The minute component of the time, from 0 to 59.
     * @param second The second component of the time, from 0 to 59.
     * @return An XMLGregorianCalendar instance representing the specified time.
     * @throws ConversionException if there is an error in creating the XMLGregorianCalendar instance.
     */
    public static XMLGregorianCalendar xmlTime(int hour, int minute, int second) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(1970, 1, 1, hour, minute, second, 0, 0);
        } catch (DatatypeConfigurationException e) {
            throw new ConversionException(e);
        }
    }

    /**
     * Extracts the first LocationRel associated with a Vessel.
     * Assumes that the Vessel has at least one LocationRel and returns the first one in the list.
     *
     * @param v The Vessel from which the LocationRel is extracted.
     * @return The first LocationRel object from the Vessel's list of LocationRels.
     */
    public static Objet.LocationRel extractLocationRel(Vessel v) {
        return v.getLocationRels().get(0);
    }

    /**
     * Extracts the Location from the first LocationRel of a Vessel.
     * Assumes that the Vessel has at least one LocationRel and extracts the Location from the first one.
     *
     * @param v The Vessel from which the Location is extracted.
     * @return The Location object from the first LocationRel of the Vessel.
     */
    public static Location extractLocation(Vessel v) {
        return v.getLocationRels().get(0).getLocation();
    }

    /**
     * Extracts the first Geometry from the Location of a Vessel.
     * Assumes that the Location associated with the Vessel has at least one Geometry object.
     *
     * @param v The Vessel from which the Geometry is extracted.
     * @return The first Geometry object from the Location of the Vessel.
     */
    public static Geometry extractGeometry(Vessel v) {
        return extractLocation(v).getGeometries().get(0);
    }

    /**
     * Extracts a Vessel object from a Push message.
     * Assumes that the Push message contains an XmlEntityPayload with a Vessel as the first element.
     *
     * @param translate The Push message from which the Vessel is extracted.
     * @return The Vessel object extracted from the Push message.
     */
    public static Vessel extractVessel(Push translate) {
        return (Vessel) extractPayload(translate).getAnies().get(0);
    }

    /**
     * Extracts the XmlEntityPayload from a Push message.
     *
     * @param m The Push message from which the payload is extracted.
     * @return The XmlEntityPayload extracted from the Push message.
     */
    public static XmlEntityPayload extractPayload(Push m) {
        return (XmlEntityPayload) m.getPayload();
    }


    /**
     * Reads the content of a resource file and returns it as a String.
     * This method is used to read the content of a file located in the resources directory of the project.
     *
     * @param resourcePath The relative path to the resource file within the resource directory.
     * @return A String containing the content of the resource file.
     * @throws IOException if an I/O error occurs while reading the file.
     * @throws URISyntaxException if the URL of the resource is not formatted correctly.
     */
    public static String readResource(String resourcePath) throws IOException, URISyntaxException {
        Path path = Paths.get(XMLDataUtils.class.getClassLoader().getResource(resourcePath).toURI());
        return Files.readString(path);
    }

    /**
     * This method validates an XML string against a provided XML Schema Definition (XSD).
     * The XSD is provided as a path to a resource file.
     * If the XML string is not valid according to the XSD, a SAXException will be thrown.
     *
     * @param xml        The XML string that needs to be validated.
     * @param schemaPath The path to the XSD resource file. This path is relative to the classpath.
     * @throws IOException        If there is an issue accessing the XSD file.
     * @throws URISyntaxException If the given schemaPath is not formatted correctly.
     * @throws SAXException       If the XML string does not conform to the XSD or if there are issues in processing the XML or XSD.
     */
    public static void validateAgainstXsd(String xml, String schemaPath) throws IOException, URISyntaxException, SAXException {
        String resource = readResource(schemaPath);

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new StreamSource(new StringReader(resource)));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(xml)));
    }

    /**
     * This method compares two XML strings and returns a string that represents the differences between them.
     *
     * @param xmlA               the first XML string to compare
     * @param xmlB               the second XML string to compare
     * @param exceptions         a list of XPath expressions representing the parts of the XML documents that should be excluded from the comparison process.
     * @param comparisonStrategy an implementation of XMLDataCustomComparisonStrategy that provides custom logic to handle specific comparisons.
     * @return useful for jUnits string that represents the differences between the two XML strings, ignoring the differences specified by the exception list
     */
    //TODO: Implement an overloaded version of this method which accommodates potential null values for 'exceptions' and 'comparisonStrategy'.
    public static String getXMLsDifference(String xmlA, String xmlB, List<String> exceptions, XMLDataCustomComparisonStrategy comparisonStrategy) {
        Diff diff = DiffBuilder
                .compare(Input.fromString(xmlA))
                .withTest(Input.fromString(xmlB))
                .ignoreWhitespace()
                .ignoreComments()
                .withDifferenceEvaluator(getCustomDifferenceEvaluator(exceptions, comparisonStrategy))
                .checkForIdentical()
                .build();
        StringBuilder differences = new StringBuilder();
        if (diff.hasDifferences()) {
            logger.debug("Differences found:");
            for (Difference difference : diff.getDifferences()) {
                if (difference.getResult() == ComparisonResult.DIFFERENT)
                    differences.append(difference.toString()).append("\n");
            }
            logger.debug(differences.toString());
        }
        return differences.toString();
    }

    /**
     * Custom DifferenceEvaluator that ignores differences in:
     * - the number of attributes
     * - the paths specified in the exception list
     * - the date time strings that represent the same instant but are formatted differently
     *
     * @param exceptionsList
     * @return
     */

    /**
     * Creates a custom DifferenceEvaluator for XML comparison.
     * DifferenceEvaluator is designed to ignore certain differences based on specific criteria,
     * making it useful for comparing XML documents with known allowable variances.
     *
     * <p></p>The evaluator ignores:
     * - Differences in the number of attributes in XML elements are ignored, treating them as similar.
     * - Differences in specific XML paths listed in the exceptions list are ignored. The paths are provided as simplified XPath expressions.
     * - Differences in date-time strings that represent the same instant but are formatted differently are ignored.
     * - If the comparison type is attribute value and the values are numeric, differences within the defined EQUALITY_TOLERANCE are ignored.
     *
     * @param exceptionsList A list of XPath expressions representing the XML paths to be ignored during comparison.
     *                       These paths are simplified, i.e., index numbers in paths are omitted.
     * @param customStrategy An implementation of XMLDataCustomComparisonStrategy, providing custom logic for specific comparisons.
     *                       If a custom strategy is provided, its result is considered before the default evaluator logic.
     * @return A DifferenceEvaluator that applies the specified rules to determine if the differences between XML nodes are significant.
     */
    private static DifferenceEvaluator getCustomDifferenceEvaluator(List<String> exceptionsList, XMLDataCustomComparisonStrategy customStrategy) {
        DifferenceEvaluator diffEvaluator = (comparison, outcome) -> {
            if (customStrategy != null) {
                ComparisonResult result = customStrategy.apply(comparison);
                if (result != null) {
                    return result;
                }
            }
            if (comparison.getType() == ComparisonType.ELEMENT_NUM_ATTRIBUTES) {
                return ComparisonResult.SIMILAR;
            }
            if (comparison.getType() == ComparisonType.CHILD_NODELIST_LENGTH) {
                return ComparisonResult.SIMILAR;
            }
            if (exceptionsList != null && isComparisonInExceptionsList(exceptionsList, comparison)) {
                return ComparisonResult.SIMILAR;
            }
            if (skippDateDifferences(comparison)) {
                return ComparisonResult.SIMILAR;
            }
            if (comparison.getType() == ComparisonType.ATTR_VALUE) {
                try {
                    double controlDouble = Double.parseDouble((String) comparison.getControlDetails().getValue());
                    double testDouble = Double.parseDouble((String) comparison.getTestDetails().getValue());
                    if (Math.abs(controlDouble - testDouble) <= EQUALITY_TOLERANCE) {
                        return ComparisonResult.SIMILAR;
                    }
                } catch (NumberFormatException ex) {
                    ;
                }
            }
            return outcome;
        };
        return diffEvaluator;
    }

    private static boolean isComparisonInExceptionsList(List<String> exceptions, Comparison comparison) {
        boolean notInExceptionsList = false;
        boolean inExceptionsList = true;

        if (exceptions == null || comparison == null) {
            logger.debug("Exceptions list or comparison is null.");
            return notInExceptionsList;
        }
        Comparison.Detail controlDetails = comparison.getControlDetails();
        Comparison.Detail testDetails = comparison.getTestDetails();
        if (controlDetails == null || testDetails == null) {
            logger.debug("Control or test details are null. Control details: " + controlDetails + ", Test details: " + testDetails);
            return notInExceptionsList;
        }
        String controlXPath = controlDetails.getXPath();
        String testXPath = testDetails.getXPath();

        if (controlXPath == null || testXPath == null) {
            return inExceptionsList;
        }
        //checking against exceptions list
        String simplifiedControlXPath = controlXPath.replaceAll("\\[\\d+\\]", "");
        String simplifiedTestXPath = testXPath.replaceAll("\\[\\d+\\]", "");
        if (exceptions.stream().anyMatch(e -> e.equals(simplifiedControlXPath) || e.equals(simplifiedTestXPath))) {
            return inExceptionsList;
        }
        return notInExceptionsList;
    }

    private static boolean skippDateDifferences(Comparison comparison) {
        Object controlValue = comparison.getControlDetails().getValue();
        Object testValue = comparison.getTestDetails().getValue();
        if (controlValue instanceof String && testValue instanceof String) {
            try {
                ZonedDateTime dateTime1 = ZonedDateTime.parse((CharSequence) controlValue);
                ZonedDateTime dateTime2 = ZonedDateTime.parse((CharSequence) testValue);
                return dateTime1.toInstant().equals(dateTime2.toInstant());
            } catch (DateTimeParseException e) {
                // Parsing failed, but no need to do anything here, we'll return false
            }
        }
        return false;
    }
}
