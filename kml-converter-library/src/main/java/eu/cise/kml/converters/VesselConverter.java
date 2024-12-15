package eu.cise.kml.converters;

import de.micromata.opengis.kml.v_2_2_0.Data;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.ExtendedData;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.object.Objet;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.servicemodel.v1.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * Implemetnation of a {@link CiseConverter} that converts {@link Vessel} into a {@link Placemark} and adds it to the {@link Document} provided
 */
public class VesselConverter implements CiseConverter<Vessel> {

    private static final Logger logger = LoggerFactory.getLogger("VesselTranslator");

    @Override
    public String getName() {
        return "Vessel";
    }

    @Override
    public void translate(Message ciseMessage, Vessel cisePayload, Document kmlMessage) {
        Placemark vesselPlacemark = kmlMessage.createAndAddPlacemark()
                .withId(generateId(cisePayload)) //TODO this mapping tests need to be revisited - verify what this should be
                .withName("")
                .withDescription("");
        // set time
        vesselPlacemark.createAndSetTimeStamp()
                .withWhen(String.valueOf(ciseMessage.getCreationDateTime()));
        // prepare the extended data
        ExtendedData extendedData = vesselPlacemark.createAndSetExtendedData();

        // get vessel coordinates
        if (!cisePayload.getLocationRels().isEmpty()) {
            Objet.LocationRel vesselLocationRel = cisePayload.getLocationRels().get(0);
            addDataToExtendedData(extendedData, "SPEED", vesselLocationRel.getSpeed());
            addDataToExtendedData(extendedData, "COURSE", vesselLocationRel.getCOG());
            if (!vesselLocationRel.getLocation().getGeometries().isEmpty()) {
                Geometry vesselGeometry = vesselLocationRel
                        .getLocation().getGeometries().get(0);
                // set vessel coordinates
                vesselPlacemark.createAndSetPoint()
                        .addToCoordinates(Double.valueOf(vesselGeometry.getLongitude())
                                , Double.valueOf(vesselGeometry.getLatitude()));
            }
        } else {
            logger.info("Vessel without locationRel found. Skipping translation for location");
        }

        // TODO know default values so that we can fill them in if not present in CISE message
        addDataToExtendedData(extendedData, "NGL-LABEL", StringUtils.join(cisePayload.getNames(), ",")); // TODO is this the name of the vessel?
        addDataToExtendedData(extendedData, "UUID", cisePayload.getUVI()); // TODO is this correct?
        addDataToExtendedData(extendedData, "MMSI", cisePayload.getMMSI());
        addDataToExtendedData(extendedData, "IMO", cisePayload.getIMONumber());
        addDataToExtendedData(extendedData, "NavStatus", cisePayload.getNavigationalStatus());
        addDataToExtendedData(extendedData, "SOURCEID", "N/A"); // TODO What should this be?
        addDataToExtendedData(extendedData, "SOURCESYSTEM", "N/A"); // TODO What should this be?
        if (ciseMessage.getSender() == null) {
            logger.info("Message has not sender. Defaulting REALTIME to false");
            addDataToExtendedData(extendedData, "REALTIME", "false");

        } else {
            addDataToExtendedData(extendedData, "REALTIME", ciseMessage.getSender().getDataFreshness());
        }
    }

    private String generateId(Vessel vessel) {
        String result = nullToDefaultValue(vessel.getIMONumber(), "NO_IMO");
        return "vessel_" + result + "_" + UUID.randomUUID();
    }

    private String nullToDefaultValue(Object testObject, String defaultValue) {
        return testObject == null ? defaultValue : String.valueOf(testObject);
    }

    private void addDataToExtendedData(ExtendedData data, String dataName, Object dataValue) {
        Data currentData = new Data(String.valueOf(dataValue));
        currentData.setName(dataName);
        data.addToData(currentData);
    }
}
