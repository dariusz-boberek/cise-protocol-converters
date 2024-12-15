package eu.cise.ais;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static eu.cise.converters.utils.XMLDataUtils.xmlDate;
import static eu.cise.converters.utils.XMLDataUtils.xmlTime;
import static eu.cise.datamodel.v1.entity.event.LocationRoleInEventType.END_PLACE;
import static eu.cise.datamodel.v1.entity.movement.MovementType.VOYAGE;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import com.greghaskins.spectrum.Spectrum;
import eu.cise.ais.core.AisMsg;
import eu.cise.ais.converters.Message5Converter;
import eu.cise.datamodel.v1.entity.event.Event.LocationRel;
import eu.cise.datamodel.v1.entity.location.PortLocation;
import eu.cise.datamodel.v1.entity.movement.Movement;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import eu.cise.datamodel.v1.entity.vessel.VesselType;
import eu.eucise.xml.DefaultXmlMapper;
import java.time.Instant;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(Spectrum.class)
public class AIS_5_TranslatorSpec {

    {
        describe("an AIS to CISE message translator", () -> {

            Message5Converter translator = new Message5Converter();

            final AisMsg m = new AisMsg.Builder(5)
                .withUserId(12345678)
                .withShipName("QUEEN MARY III")
                .withDimensionA(100)
                .withDimensionB(20)
                .withDimensionC(10)
                .withDimensionD(20)
                .withCallSign("myCallSign")
                .withDraught(34.5F)
                .withIMONumber(123456)
                .withShipType(84)
                .withDestination("FRLEH")
                .withEta(Instant.parse("2019-06-19T15:43:00Z"))
                .build();

            describe("when a message type is 5", () -> {
                final Vessel v = translator.translate(m);

                it("returns a Vessel with an ship name", () -> {
                    assertThat(v.getNames(), is(not(empty())));
                    assertThat(v.getNames().get(0), is("QUEEN MARY III"));
                });
                it("returns a Vessel with an ship name", () -> {
                    assertThat(v.getBeam(), is(30));
                });
                it("returns a Vessel with a call sign", () -> {
                    assertThat(v.getCallSign(), is("myCallSign"));
                });
                it("returns a Vessel with a draught", () -> {
                    assertThat(v.getDraught(), is(34.5D));
                });
                it("returns a Vessel with an imo number", () -> {
                    assertThat(v.getIMONumber(), is(123456L));
                });
                it("returns a Vessel with a length", () -> {
                    assertThat(v.getLength(), is(120D)); // A+B
                });
                it("returns a Vessel with an MMSI", () -> {
                    assertThat(v.getMMSI(), is(12345678L));
                });
                it("returns a Vessel with an ship type", () -> {
                    assertThat(v.getShipTypes(), is(not(empty())));
                    assertThat(v.getShipTypes().get(0), is(VesselType.OIL_TANKER));
                });
                it("returns a Vessel with a InvolvedEventRel", () -> {
                    assertThat(v.getInvolvedEventRels(), is(not(empty())));
                });

                context("Involved Event with a location code", () -> {
                    final Movement mo = getMovement(translator.translate(m));
                    it("returns a Vessel with an Movement", () -> {
                        assertThat(mo, instanceOf(Movement.class));
                    });
                    it("returns a Vessel with an MovementType", () -> {
                        assertThat(mo.getMovementType(), is(VOYAGE));
                    });
                    it("returns a Vessel with a LocationRel", () -> {
                        assertThat(mo.getLocationRels(), is(not(empty())));
                    });
                    it("returns a Vessel without datetime when ETA is null", () -> {
                        final AisMsg mon = new AisMsg.Builder(5)
                            .withUserId(12345678)
                            .withShipName("QUEEN MARY III")
                            .withEta(null)
                            .build();

                        LocationRel locationRel =
                            getMovement(translator.translate(mon)).getLocationRels().get(0);

                        assertThat(locationRel.getDateTime(), is(nullValue()));
                    });
                    it("returns a Vessel with an ETA date", () -> {
                        assertThat(mo.getLocationRels().get(0).getDateTime().getStartDate(),
                            is(xmlDate(Instant.parse("2019-06-19T00:00:00Z"))));
                    });
                    it("returns a Vessel with an ETA time", () -> {
                        assertThat(mo.getLocationRels().get(0).getDateTime().getStartTime(),
                            is(xmlTime(Instant.parse("1970-01-01T15:43:00Z"))));
                    });
                    it("returns a Vessel with location role", () -> {
                        assertThat(mo.getLocationRels().get(0).getLocationRole(), is(END_PLACE));
                    });
                    it("returns a Vessel with a Location", () -> {
                        assertThat(getLocation(mo), instanceOf(PortLocation.class));
                    });
                    it("returns a Vessel with a LocationCode", () -> {
                        assertThat(getLocation(mo).getLocationCode(), is("FRLEH"));
                    });
                    it("returns a Vessel with a PortName", () -> {
                        System.out.print(new DefaultXmlMapper.Pretty().toXML(v));
                        assertThat(getLocation(mo).getPortName(), is("FRLEH"));

                    });
                });
                context("Involved Event with a port name", () -> {
                    final AisMsg m1 = new AisMsg.Builder(5)
                        .withUserId(12345678)
                        .withShipName("QUEEN MARY III")
                        .withDimensionA(100)
                        .withDimensionB(20)
                        .withDimensionC(10)
                        .withDimensionD(20)
                        .withCallSign("myCallSign")
                        .withDraught(34.5F)
                        .withIMONumber(123456)
                        .withShipType(84)
                        .withDestination("Le Havre")
                        .build();

                    final Movement mo = getMovement(translator.translate(m1));

                    it("returns a Vessel with a LocationCode", () -> {
                        assertThat(getLocation(mo).getLocationCode(), is(nullValue()));
                    });
                    it("returns a Vessel with a PortName", () -> {
                        assertThat(getLocation(mo).getPortName(), is("Le Havre"));
                    });
                });
            });
        });
    }

    private PortLocation getLocation(Movement m) {
        return (PortLocation) m.getLocationRels().get(0).getLocation();
    }

    private Movement getMovement(Vessel v) {
        return (Movement) v.getInvolvedEventRels().get(0).getEvent();
    }

}

