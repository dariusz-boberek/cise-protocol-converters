package eu.cise.ivef;

import eu.cise.ivef.generated.MSGVesselData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.URISyntaxException;

import static eu.cise.converters.utils.XMLDataUtils.getXMLsDifference;
import static eu.cise.converters.utils.XMLDataUtils.readResource;
import static eu.cise.converters.utils.XMLDataUtils.validateAgainstXsd;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * The XMLDataUtils class provides specific functionalities for XML validation and comparison, primarily used in testing scenarios.
 *
 * <p>Key Methods:
 * 1. validateAgainstXsd: Validates an XML string against a given XML Schema Definition (XSD).
 * It throws an exception if the XML does not conform to the specified schema, aiding in ensuring XML data integrity.
 * 2. getXMLsDifference: Compares two XML strings and identifies differences.
 * This method is essential for verifying the correctness of XML data transformations and format conversions in tests.
 *
 * <p>The class is utilized in test suites such as ivef-converter-library's IVEFConverterTest and ivef-library's IVEFHelperTest,
 * where precise XML data handling is critical. These methods support rigorous testing by enabling validation of XML structures
 * against schemas and detailed comparison of XML outputs, crucial for testing data format conversions and transformations.
 */
class IVEFHelperTest {

    private static final Logger logger = LoggerFactory.getLogger(IVEFHelperTest.class);

    @Test
    public void simpleTest() {
        System.out.println("test");

    }


    @Test
    public void it_unmarshals_a_small_file() throws URISyntaxException, IOException, JAXBException {
        String ivefXML = readResource("IVEF_threeVessels.xml");
        MSGVesselData result = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(3, result.getBody().getVesselData().size());
    }

    @Test
    public void it_unmarshals_a_large_file() throws URISyntaxException, IOException, JAXBException {
        String ivefXML = readResource("IVEF_big.xml");
        MSGVesselData result = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(1416, result.getBody().getVesselData().size());
    }

    /**
     * by using DoubleNaNAdapter we are creating nonconform XML with XSD. So in orter for validateAgainstXsd function to pass XML have to be modified.
     * That's why in this example we are using "ivef_example_small_correctedBasedOnXSD" instead of standard file.
     *
     * @throws URISyntaxException
     * @throws IOException
     * @throws JAXBException
     * @throws SAXException
     */
    @Test
    public void it_marshals_and_validates_against_xsd_a_small_file() throws URISyntaxException, IOException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("IVEF_threeVessels_correctedBasedOnXSD.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefData = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(3, ivefData.getBody().getVesselData().size());

        //when
        String marshalledIVEFXML = IVEFHelper.dataToString(ivefData);

        //then
        validateAgainstXsd(marshalledIVEFXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, marshalledIVEFXML, null, null).isEmpty(), "The returned string should not be empty");
    }


    @Test
    public void it_fails_to_compare_on_xml_level_unmarshalled_then_marshalled_data_for_nonconforming_SourceName_property() throws URISyntaxException, IOException, JAXBException, SAXException {
        //given
        String ivefXML = readResource("IVEF_threeVessels.xml");
        MSGVesselData ivefData = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(3, ivefData.getBody().getVesselData().size());

        //when
        String marshalledIVEFXML = IVEFHelper.dataToString(ivefData);

        //then
        String diffResult = getXMLsDifference(ivefXML, marshalledIVEFXML, null, null);
        assertThat(diffResult, not(isEmptyOrNullString()));
        assertThat(diffResult, containsString("@SourceName"));
    }

    @Test
    public void it_validates_JAXB_transformations_for_oneVessel_mini() throws IOException, URISyntaxException, JAXBException, SAXException {

        //given
        String ivefXML = readResource("IVEF_oneVessel_mini.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(1, ivefMessage.getBody().getVesselData().size());

        //when
        String marshalledIVEFXML = IVEFHelper.dataToString(ivefMessage);
        logger.debug("\ngenerated message:\n{}\n", marshalledIVEFXML);

        //then
        validateAgainstXsd(marshalledIVEFXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, marshalledIVEFXML, null, null).isEmpty(), "The returned string should not be empty");

    }

    @Test
    public void it_validates_JAXB_transformations_for_big_correctedBasedOnXSD() throws IOException, URISyntaxException, JAXBException, SAXException {

        //given
        String ivefXML = readResource("IVEF_big_correctedBasedOnXSD.xml");
        validateAgainstXsd(ivefXML, "xsd/xsd_0_1_5.xsd");
        MSGVesselData ivefMessage = IVEFHelper.stringToData(ivefXML);
        Assertions.assertEquals(1416, ivefMessage.getBody().getVesselData().size());

        //when
        String marshalledIVEFXML = IVEFHelper.dataToString(ivefMessage);

        //then
        validateAgainstXsd(marshalledIVEFXML, "xsd/xsd_0_1_5.xsd");
        Assertions.assertTrue(getXMLsDifference(ivefXML, marshalledIVEFXML, null, null).isEmpty(), "The returned string should not be empty");

    }


}