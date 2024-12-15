package eu.cise.ais.converters;

import eu.cise.ais.Translator;
import eu.cise.ais.config.AisConverterConfiguration;
import eu.cise.ais.core.AisMsg;
import eu.cise.ais.converters.utils.NavigationStatus;
import eu.cise.datamodel.v1.entity.location.Geometry;
import eu.cise.datamodel.v1.entity.location.Location;
import eu.cise.datamodel.v1.entity.location.LocationQualitativeAccuracyType;
import eu.cise.datamodel.v1.entity.object.Objet;
import eu.cise.datamodel.v1.entity.object.SensorType;
import eu.cise.datamodel.v1.entity.object.SourceType;
import eu.cise.datamodel.v1.entity.period.Period;
import eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.converters.exceptions.ConversionException;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType.*;

/**
 * This class converts messages of type 1,2,3 into CISE Vessel objects.
 */
public class Message123Converter implements Translator<AisMsg, Vessel> {

    // internal attributes
    private final AisConverterConfiguration configuration;

    /**
     * The {@link AisConverterConfiguration} configuration is needed to adjust the behavior at runtime.
     *
     * @param configuration the converter config
     */
    public Message123Converter(AisConverterConfiguration configuration) {
        this.configuration = configuration;
    }

    /**
     * Main method to convert an AIS message into a CISE Vessel object. Each and every field is
     * translated in the corresponding vessel field respecting corner cases, special encoding and
     * different base scale of the data.
     *
     * @param message the AIS message
     * @return a translated CISE vessel
     */
    @Override
    public Vessel translate(AisMsg message) {

        // casting float to double
        Long mmsi = Long.valueOf(message.getUserId());

        Vessel vessel = new Vessel();
        vessel.setIMONumber(mmsi);
        vessel.setMMSI(mmsi);
        vessel.getLocationRels().add(
                getLocationRel(
                        toLocation(
                                message.getLatitude(),
                                message.getLongitude(),
                                fromPositionAccuracy(message)),
                        fromCourseOverGround(message.getCOG()),
                        fromTrueHeading(message.getTrueHeading()),
                        message.getTimestamp(),
                        fromSpeedOverGround(message.getSOG())));
        vessel.setNavigationalStatus(fromNavigationStatus(message.getNavigationStatus()));

        return vessel;
    }

    // PRIVATE HELPERS /////////////////////////////////////////////////////////
    private LocationQualitativeAccuracyType fromPositionAccuracy(AisMsg aisMsg) {
        return aisMsg.getPositionAccuracy() == 1 ?
                LocationQualitativeAccuracyType.HIGH :
                LocationQualitativeAccuracyType.MEDIUM;
    }

    private Objet.LocationRel getLocationRel(
            Location location,
            Double cog,
            Double heading,
            Instant timestamp,
            Double sog) {

        Objet.LocationRel locationRel = new Objet.LocationRel();
        locationRel.setLocation(location);
        locationRel.setCOG(cog);
        locationRel.setHeading(heading);

        if (configuration.getOverrideTimestamps()) {
            timestamp = Instant.now(Clock.systemUTC());
        }

        if (!timestamp.equals(Instant.MIN)) {
            Period period = new Period();
            period.setStartDate(toXMLDate(timestamp));
            period.setStartTime(toXMLTime(timestamp));
            locationRel.setPeriodOfTime(period);
        }

        locationRel.setSourceType(SourceType.DECLARATION);
        locationRel.setSensorType(SensorType.AUTOMATIC_IDENTIFICATION_SYSTEM);
        locationRel.setSOG(sog);

        return locationRel;
    }

    private XMLGregorianCalendar toXMLCalendar(int year, int month, int day, int hours, int minutes,
                                               int seconds) {
        try {
            return DatatypeFactory.newInstance()
                    .newXMLGregorianCalendar(year, month, day, hours, minutes, seconds, 0, 0);
        } catch (DatatypeConfigurationException e) {
            throw new ConversionException(
                    "Can't create a correct XMLGregorianCalendar DATE/TIME out of the instant ", e);
        }
    }

    private XMLGregorianCalendar toXMLDate(Instant timestamp) {
        LocalDateTime l = LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC"));
        return toXMLCalendar(l.getYear(), l.getMonthValue(), l.getDayOfMonth(), 0, 0, 0);
    }

    private XMLGregorianCalendar toXMLTime(Instant timestamp) {
        LocalDateTime l = LocalDateTime.ofInstant(timestamp, ZoneId.of("UTC"));
        return toXMLCalendar(1970, 01, 01, l.getHour(), l.getMinute(), l.getSecond());
    }

    private Location toLocation(
            Float latitude, Float longitude, LocationQualitativeAccuracyType lqat) {

        if (configuration.getDeleteUnavailableLocation() && (longitude == 181 || latitude == 91)) {
            return null;
        }

        Location location = new Location();
        Geometry geometry = new Geometry();
        geometry.setLatitude(Float.toString(latitude));
        geometry.setLongitude(Float.toString(longitude));
        location.getGeometries().add(geometry);
        location.setLocationQualitativeAccuracy(lqat);
        return location;
    }

    private Double f2d(Float fValue) {
        return Double.valueOf(fValue.toString());
    }

    private Double fromCourseOverGround(Float cog) {
        return cog == 360.0F ? null : f2d(cog);
    }

    private Double fromSpeedOverGround(Float sog) {
        return sog == 102.3F ? null : f2d(sog);
    }

    private Double fromTrueHeading(int th) {
        return th == 511 ? null : Double.valueOf(th);
    }

    /**
     * It translates the navigation status from AIS to CISE model.
     *
     * @param ns the navigation status to be translated from AIS Message
     * @return the NavigationalStatusType enum coming from the CISE data model
     */
    private NavigationalStatusType fromNavigationStatus(NavigationStatus ns) {
        if (ns == null)
            return UNDEFINED_DEFAULT;

        switch (ns) {
            case UnderwayUsingEngine:
                return UNDER_WAY_USING_ENGINE;
            case AtAnchor:
                return AT_ANCHOR;
            case NotUnderCommand:
                return NOT_UNDER_COMMAND;
            case RestrictedManoeuverability:
                return RESTRICTED_MANOEUVRABILITY;
            case ConstrainedByHerDraught:
                return CONSTRAINED_BY_HER_DRAUGHT;
            case Moored:
                return MOORED;
            case Aground:
                return AGROUND;
            case EngagedInFising:
                return ENGAGED_IN_FISHING;
            case UnderwaySailing:
                return UNDER_WAY_SAILING;
            case ReservedForFutureUse9:
            case ReservedForFutureUse13:
            case ReservedForFutureUse10:
            case SartMobOrEpirb:
                return OTHER;
            case PowerDrivenVesselTowingAstern:
                return POWER_DRIVEN_VESSEL_TOWING_ASTERN;
            case PowerDrivenVesselPushingAheadOrTowingAlongside:
                return POWER_DRIVEN_VESSEL_TOWIG_AHEAD_OR_PUSHING_ALONGSIDE;
            default:
                return UNDEFINED_DEFAULT;
        }

    }

}
