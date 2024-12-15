package eu.cise.ivef;


import eu.cise.ivef.generated.LoginRequest;
import eu.cise.ivef.generated.LoginResponse;
import eu.cise.ivef.generated.MSGLoginRequest;
import eu.cise.ivef.generated.MSGLoginResponse;
import eu.cise.ivef.generated.MSGLogout;
import eu.cise.ivef.generated.MSGPing;
import eu.cise.ivef.generated.MSGPong;
import eu.cise.ivef.generated.MSGServerStatus;
import eu.cise.ivef.generated.MSGServiceRequest;
import eu.cise.ivef.generated.MSGVesselData;
import eu.cise.ivef.generated.Ping;
import eu.cise.ivef.generated.Pong;
import eu.cise.ivef.generated.Pos;
import eu.cise.ivef.generated.PosReport;
import eu.cise.ivef.generated.ServerStatus;
import eu.cise.ivef.generated.ServiceRequest;
import eu.cise.ivef.generated.StaticData;
import eu.cise.ivef.generated.TaggedItem;
import eu.cise.ivef.generated.VesselData;
import eu.cise.ivef.generated.Voyage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * The {@code IVEFHelper} class provides utility methods for converting between
 * IVEF XML data and Java objects using JAXB (Java Architecture for XML Binding).
 */
public class IVEFHelper {

    private static final Class<?>[] DEFAULT_IVEF_ROOT_ELEMENTS = {
            LoginRequest.class,
            LoginResponse.class,
            MSGLogout.class,
            MSGLoginRequest.class,
            MSGLoginResponse.class,
            MSGLogout.class,
            MSGPing.class,
            MSGPong.class,
            MSGServerStatus.class,
            MSGServiceRequest.class,
            MSGVesselData.class,
            Ping.class,
            Pong.class,
            Pos.class,
            PosReport.class,
            ServerStatus.class,
            ServiceRequest.class,
            StaticData.class,
            TaggedItem.class,
            VesselData.class,
            Voyage.class
    };

    /**
     * Converts an XML string to a {@code MSGVesselData} object.
     * This method is useful for unmarshalling XML data received in string format
     * into Java objects.
     *
     * @param inputXMLString the XML string to be converted
     * @return a {@code MSGVesselData} object representing the provided XML data
     * @throws JAXBException if an error occurs during the unmarshalling process
     */
    public static MSGVesselData stringToData(String inputXMLString) throws JAXBException {
        Unmarshaller jaxbUnmarshaller = newJaxbContext(DEFAULT_IVEF_ROOT_ELEMENTS).createUnmarshaller();
        return (MSGVesselData) jaxbUnmarshaller.unmarshal(new StringReader(inputXMLString));
    }

    /**
     * Converts a {@code MSGVesselData} object to its XML string representation.
     * This method is useful for marshalling Java objects back to XML format.
     *
     * @param msgVesselData the {@code MSGVesselData} object to be converted
     * @return a string representation of the {@code MSGVesselData} object in XML format
     * @throws JAXBException if an error occurs during the marshalling process
     */
    public static String dataToString(MSGVesselData msgVesselData) throws JAXBException {
        Marshaller jaxMarshaller = newJaxbContext(DEFAULT_IVEF_ROOT_ELEMENTS).createMarshaller();
        jaxMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter stringWriter = new StringWriter();
        jaxMarshaller.marshal(msgVesselData, stringWriter);
        return stringWriter.toString();
    }

    private static JAXBContext newJaxbContext(Class<?>[] classesToBeBound) throws JAXBException {
        return JAXBContext.newInstance(classesToBeBound);
    }
}
