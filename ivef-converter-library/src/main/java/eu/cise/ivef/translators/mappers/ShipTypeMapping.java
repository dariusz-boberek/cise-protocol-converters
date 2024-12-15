package eu.cise.ivef.translators.mappers;

import eu.cise.datamodel.v1.entity.vessel.VesselType;

import java.math.BigInteger;

/**
 * Maps BigInteger values to specific enumeration types for data translation
 * between IVEF and CISE formats. This class implements the bidirectional mapping
 * defined in {@link ImmutableBidiMultiMap} with specific data types relevant
 * to ship type.
 */
public class ShipTypeMapping extends ImmutableBidiMultiMap<BigInteger, VesselType> {
    public ShipTypeMapping() {
        this.put(BigInteger.valueOf(20), VesselType.OTHER);
        this.put(BigInteger.valueOf(30), VesselType.FISHING_VESSEL);
        this.put(BigInteger.valueOf(31), VesselType.OTHER);
        this.put(BigInteger.valueOf(32), VesselType.OTHER);
        this.put(BigInteger.valueOf(33), VesselType.OTHER);
        this.put(BigInteger.valueOf(34), VesselType.OTHER);
        this.put(BigInteger.valueOf(35), VesselType.OTHER);
        this.put(BigInteger.valueOf(36), VesselType.OTHER);
        this.put(BigInteger.valueOf(37), VesselType.OTHER);
        this.put(BigInteger.valueOf(40), VesselType.HIGH_SPEED_CRAFT);
        this.put(BigInteger.valueOf(50), VesselType.OTHER);
        this.put(BigInteger.valueOf(51), VesselType.OTHER);
        this.put(BigInteger.valueOf(52), VesselType.OTHER);
        this.put(BigInteger.valueOf(53), VesselType.OTHER);
        this.put(BigInteger.valueOf(54), VesselType.OTHER);
        this.put(BigInteger.valueOf(55), VesselType.OTHER);
        this.put(BigInteger.valueOf(58), VesselType.OTHER);
        this.put(BigInteger.valueOf(59), VesselType.OTHER);
        this.put(BigInteger.valueOf(60), VesselType.PASSENGER_SHIP);
        this.put(BigInteger.valueOf(70), VesselType.GENERAL_CARGO_SHIP);
        this.put(BigInteger.valueOf(80), VesselType.OIL_TANKER);
        this.put(BigInteger.valueOf(90), VesselType.NUCLEAR_SHIP);
        this.put(BigInteger.valueOf(90), VesselType.BULK_CARRIER);
        this.put(BigInteger.valueOf(90), VesselType.MOBILE_OFF_SHORE_DRILLING_UNIT);
        this.put(BigInteger.valueOf(90), VesselType.SPECIAL_PURPOSE_SHIP);
        this.put(BigInteger.valueOf(90), VesselType.NON_SPECIFIED);
        this.put(BigInteger.valueOf(90), VesselType.OTHER); // Default values must be added last

        setDefaultCISEValue(VesselType.OTHER);
        setDefaultIVEFValue(BigInteger.valueOf(90));
    }

    @Override
    public String getCISEMetadataCommentsKey() {
        return "VesselData.StaticData.ShipType";
    }

    @Override
    public String getIVEFTaggedItemKey() {
        return "CISE_SHIP_TYPE";
    }

    @Override
    public VesselType getCISEEnumFromStringValue(String stringValueCISE) {
        return VesselType.fromValue(stringValueCISE);
    }


    @Override
    public boolean compareValues(BigInteger valueIVEF, VesselType valueCISE) {
        return valueIVEF.equals(getIVEFValue(valueCISE));
    }

}
