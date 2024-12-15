package eu.cise.ivef.translators.mappers;

import eu.cise.datamodel.v1.entity.cargo.CargoType;

import java.math.BigInteger;

/**
 * Maps BigInteger values to specific enumeration types for data translation
 * between IVEF and CISE formats. This class implements the bidirectional mapping
 * defined in {@link ImmutableBidiMultiMap} with specific data types relevant
 * to Cargo Type
 */
public class CargoTypeMapping extends ImmutableBidiMultiMap<BigInteger, CargoType> {
    public CargoTypeMapping() {
        this.put(BigInteger.valueOf(0), CargoType.OTHER);
        this.put(BigInteger.valueOf(1), CargoType.OTHER);
        this.put(BigInteger.valueOf(2), CargoType.OTHER);
        this.put(BigInteger.valueOf(3), CargoType.OTHER);
        this.put(BigInteger.valueOf(4), CargoType.OTHER);
        this.put(BigInteger.valueOf(9), CargoType.PALLETIZED);
        this.put(BigInteger.valueOf(9), CargoType.PRE_SLUNG);
        this.put(BigInteger.valueOf(9), CargoType.MOBILE_SELF_PROPELLED_UNITS);
        this.put(BigInteger.valueOf(9), CargoType.OTHER_MOBILE_UNITS);
        this.put(BigInteger.valueOf(9), CargoType.RESERVED);
        this.put(BigInteger.valueOf(9), CargoType.OTHER_CARGO_TYPES);
        this.put(BigInteger.valueOf(9), CargoType.NO_CARGO_UNIT_LIQUID_BULK_GOODS);
        this.put(BigInteger.valueOf(9), CargoType.NO_CARGO_UNIT_SOLID_BULK_GOODS);
        this.put(BigInteger.valueOf(9), CargoType.LARGE_FREIGHT_CONTAINERS);
        this.put(BigInteger.valueOf(9), CargoType.OTHER_FREIGHT_CONTAINERS);
        this.put(BigInteger.valueOf(9), CargoType.NON_SPECIFIED);
        this.put(BigInteger.valueOf(9), CargoType.OTHER); // Default values must be added last

        setDefaultCISEValue(CargoType.OTHER);
        setDefaultIVEFValue(BigInteger.valueOf(9));
    }

    @Override
    public String getCISEMetadataCommentsKey() {
        return "VesselData.Voyage.CargoType";
    }

    @Override
    public String getIVEFTaggedItemKey() {
        return "CISE_CARGO_TYPE";
    }

    @Override
    public CargoType getCISEEnumFromStringValue(String stringValueCISE) {
        return CargoType.fromValue(stringValueCISE);
    }


    @Override
    public boolean compareValues(BigInteger valueIVEF, CargoType valueCISE) {
        return valueIVEF.equals(getIVEFValue(valueCISE));
    }

}
