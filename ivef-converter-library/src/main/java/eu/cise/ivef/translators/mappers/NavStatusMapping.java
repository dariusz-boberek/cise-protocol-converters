package eu.cise.ivef.translators.mappers;

import eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType;

import java.math.BigInteger;

/**
 * Maps BigInteger values to specific enumeration types for data translation
 * between IVEF and CISE formats. This class implements the bidirectional mapping
 * defined in {@link ImmutableBidiMultiMap} with specific data types relevant
 * to Navigation status.
 */
public class NavStatusMapping extends ImmutableBidiMultiMap<BigInteger, NavigationalStatusType> {
    public NavStatusMapping() {
        this.put(BigInteger.valueOf(0), NavigationalStatusType.UNDER_WAY_USING_ENGINE);
        this.put(BigInteger.valueOf(1), NavigationalStatusType.AT_ANCHOR);
        this.put(BigInteger.valueOf(2), NavigationalStatusType.NOT_UNDER_COMMAND);
        this.put(BigInteger.valueOf(3), NavigationalStatusType.RESTRICTED_MANOEUVRABILITY);
        this.put(BigInteger.valueOf(4), NavigationalStatusType.CONSTRAINED_BY_HER_DRAUGHT);
        this.put(BigInteger.valueOf(5), NavigationalStatusType.MOORED);
        this.put(BigInteger.valueOf(6), NavigationalStatusType.AGROUND);
        this.put(BigInteger.valueOf(7), NavigationalStatusType.ENGAGED_IN_FISHING);
        this.put(BigInteger.valueOf(8), NavigationalStatusType.UNDER_WAY_SAILING);
        this.put(BigInteger.valueOf(9), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(10), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(11), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(12), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(13), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(14), NavigationalStatusType.OTHER);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.ENGAGED_IN_FISHING_OTHER_THAN_TRAWLING);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.AIR_CUSHION_VESSEL_IN_NON_DISPLAMENET_MODE_OR_WIG_CRAFT_TAKING_OFF_LANDING_OR_IN_FLIGHT);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.POWER_DRIVEN_VESSEL_TOWING_ASTERN);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.POWER_DRIVEN_VESSEL_TOWIG_AHEAD_OR_PUSHING_ALONGSIDE);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.IN_DISTRESS_OR_REQUIRING_ASSISTANCE);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.AISSART_SEEKING_TO_ATTRACT_ATTENTION);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.UNDEFINED_DEFAULT);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.NON_SPECIFIED);
        this.put(BigInteger.valueOf(15), NavigationalStatusType.OTHER); // Default values must be added last

        setDefaultCISEValue(NavigationalStatusType.OTHER);
        setDefaultIVEFValue(BigInteger.valueOf(15));
    }

    @Override
    public String getCISEMetadataCommentsKey() {
        return "VesselData.PosReport.NavStatus";
    }

    @Override
    public String getIVEFTaggedItemKey() { return "CISE_NAV_STATUS"; }

    @Override
    public NavigationalStatusType getCISEEnumFromStringValue(String stringValueCISE) {
        return NavigationalStatusType.fromValue(stringValueCISE);
    }


    @Override
    public boolean compareValues(BigInteger valueIVEF, NavigationalStatusType valueCISE) {
        return valueIVEF.equals(getIVEFValue(valueCISE));
    }

}
