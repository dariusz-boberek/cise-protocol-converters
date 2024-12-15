package eu.cise.ivef.translators.mappers;

import eu.cise.datamodel.v1.entity.object.SensorType;

import java.math.BigInteger;

/**
 * Maps BigInteger values to specific enumeration types for data translation
 * between IVEF and CISE formats. This class implements the bidirectional mapping
 * defined in {@link ImmutableBidiMultiMap} with specific data types relevant
 * to sensor type.
 */
public class SensorTypeMapping extends ImmutableBidiMultiMap<BigInteger, SensorType> {
    public SensorTypeMapping() {
        this.put(BigInteger.valueOf(1), SensorType.MARITIME_RADAR);
        this.put(BigInteger.valueOf(2), SensorType.AUTOMATIC_IDENTIFICATION_SYSTEM);
        this.put(BigInteger.valueOf(3), SensorType.OTHER);
        this.put(BigInteger.valueOf(4), SensorType.OTHER);
        this.put(BigInteger.valueOf(5), SensorType.SIGNAL_INTERCEPTION_SYSTEMS_COMINT);
        this.put(BigInteger.valueOf(5), SensorType.SIGNAL_INTERCEPTION_SYSTEMS_ELINT);
        this.put(BigInteger.valueOf(5), SensorType.ENVIRONMENTAL_SENSING_SYSTEMS);
        this.put(BigInteger.valueOf(5), SensorType.UNDERWATER_SENSOR);
        this.put(BigInteger.valueOf(5), SensorType.VESSEL_MONITORING_SYSTEM);
        this.put(BigInteger.valueOf(5), SensorType.LONG_RANGE_IDENTIFICATION_TRACKING);
        this.put(BigInteger.valueOf(5), SensorType.AUTOMATIC_VEHICLE_LOCATION);
        this.put(BigInteger.valueOf(5), SensorType.ACOUSTIC_SYSTEMS);
        this.put(BigInteger.valueOf(5), SensorType.MARITIME_MOVING_TARGET_IDENTIFICATION);
        this.put(BigInteger.valueOf(5), SensorType.SIGHTING);
        this.put(BigInteger.valueOf(5), SensorType.SYNTHETIC_APERTURE_RADAR);
        this.put(BigInteger.valueOf(5), SensorType.EOIR_OPTRONIC_SYSTEM);
        this.put(BigInteger.valueOf(5), SensorType.NON_TRADITIONAL_SOURCES);
        this.put(BigInteger.valueOf(5), SensorType.NON_SPECIFIED);
        this.put(BigInteger.valueOf(5), SensorType.OTHER); // Default values must be added last

        setDefaultCISEValue(SensorType.OTHER);
        setDefaultIVEFValue(BigInteger.valueOf(5));
    }

    @Override
    public String getCISEMetadataCommentsKey() {
        return "VesselData.PosReport.UpdSensorType";
    }

    @Override
    public String getIVEFTaggedItemKey() { return "CISE_SENSOR_TYPE"; }

    @Override
    public SensorType getCISEEnumFromStringValue(String stringValueCISE) {
        return SensorType.fromValue(stringValueCISE);
    }


    @Override
    public boolean compareValues(BigInteger valueIVEF, SensorType valueCISE) {
        return valueIVEF.equals(getIVEFValue(valueCISE));
    }

}
