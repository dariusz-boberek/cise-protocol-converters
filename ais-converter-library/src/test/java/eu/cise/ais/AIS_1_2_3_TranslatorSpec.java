package eu.cise.ais;

import static com.greghaskins.spectrum.Spectrum.describe;
import static com.greghaskins.spectrum.Spectrum.it;
import static com.greghaskins.spectrum.dsl.specification.Specification.beforeAll;
import static com.greghaskins.spectrum.dsl.specification.Specification.context;
import static eu.cise.converters.utils.XMLDataUtils.xmlDate;
import static eu.cise.converters.utils.XMLDataUtils.xmlTime;
import static eu.cise.datamodel.v1.entity.location.LocationQualitativeAccuracyType.HIGH;
import static eu.cise.datamodel.v1.entity.location.LocationQualitativeAccuracyType.MEDIUM;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.greghaskins.spectrum.Spectrum;
import eu.cise.ais.config.DefaultAisConverterConfiguration;
import eu.cise.ais.core.AisMsg;
import eu.cise.ais.converters.Message123Converter;
import eu.cise.ais.converters.utils.NavigationStatus;
import eu.cise.converters.utils.XMLDataUtils;
import eu.cise.datamodel.v1.entity.object.SensorType;
import eu.cise.datamodel.v1.entity.object.SourceType;
import eu.cise.datamodel.v1.entity.vessel.NavigationalStatusType;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import java.time.Instant;

import org.hamcrest.Matchers;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(Spectrum.class)
public class AIS_1_2_3_TranslatorSpec {

    {
        describe("an AIS to CISE message translator", () -> {

            DefaultAisConverterConfiguration config = mock(DefaultAisConverterConfiguration.class);
            Message123Converter translator = new Message123Converter(config);

            final AisMsg m = new AisMsg.Builder(1)
                .withLatitude(47.443634F)
                .withLongitude(-6.9895167F)
                .withPositionAccuracy(1)
                .withCOG(211.9F)
                .withTrueHeading(210)
                .withTimestamp(Instant.parse("2018-02-19T14:43:16.550Z"))
                .withSOG(13.8F)
                .withUserId(538005989)
                .withNavigationStatus(NavigationStatus.UnderwayUsingEngine)
                .build();

            describe("when a message type is 1,2,3", () -> {
                final Vessel v = translator.translate(m);

                context("when returns a Vessel with a geometry", () -> {

                    it("the geometry elements are not null and well formed", () -> {
                        assertThat(v.getLocationRels(), is(not(empty())));

                        assertThat(XMLDataUtils.extractLocationRel(v).getLocation(), is(notNullValue()));

                        assertThat(XMLDataUtils.extractLocationRel(v).getLocation().getGeometries(),
                            is(not(empty())));

                        assertThat(XMLDataUtils.extractLocationRel(v).getLocation().getGeometries().get(0),
                            is(notNullValue()));
                    });

                    it("the latitude is extracted correctly", () -> {
                        assertThat(XMLDataUtils.extractGeometry(v).getLatitude(), is("47.443634"));
                    });

                    it("the longitude is extracted correctly", () -> {
                        assertThat(XMLDataUtils.extractGeometry(v).getLongitude(), is("-6.9895167"));
                    });

                    context("when the config deleteIncorrectGeoLocation is true", () -> {
                        beforeAll(() -> {
                            when(config.getDeleteUnavailableLocation()).thenReturn(true);
                        });

                        it("the latitude is null when ais defaults to 91", () -> {
                            AisMsg m91 = new AisMsg.Builder(1)
                                .withLatitude(91F).withLongitude(-6.9895167F).build();
                            Vessel v91 = translator.translate(m91);

                            assertThat(XMLDataUtils.extractLocation(v91), nullValue());
                        });

                        it("the longitude is null when ais defaults to 181", () -> {
                            AisMsg m181 = new AisMsg.Builder(1)
                                .withLatitude(47.443634F).withLongitude(181F).build();
                            Vessel v181 = translator.translate(m181);

                            assertThat(XMLDataUtils.extractLocation(v181), nullValue());
                        });
                    });

                    context("when the config deleteLocationIfNotProvided is false", () -> {
                        beforeAll(() -> {
                            when(config.getDeleteUnavailableLocation()).thenReturn(false);
                        });

                        it("the latitude is 91 when ais defaults to 91", () -> {
                            AisMsg m91 = new AisMsg.Builder(1)
                                .withLatitude(91F).withLongitude(-6.9895167F).build();
                            Vessel v91 = translator.translate(m91);

                            assertThat(XMLDataUtils.extractGeometry(v91).getLatitude(), is("91.0"));
                        });

                        it("the longitude is 181 when ais defaults to 181", () -> {
                            AisMsg m181 = new AisMsg.Builder(1)
                                .withLatitude(47.443634F).withLongitude(181F).build();
                            Vessel v181 = translator.translate(m181);

                            assertThat(XMLDataUtils.extractGeometry(v181).getLongitude(), is("181.0"));
                        });
                    });
                });

                context("returns a Vessel with location qualitative accuracy", () -> {

                    it("HIGH for AIS position accuracy 1", () -> {
                        assertThat(XMLDataUtils.extractLocationRel(v).getLocation()
                            .getLocationQualitativeAccuracy(), is(HIGH));
                    });

                    it("MEDIUM for AIS position accuracy 0", () -> {
                        AisMsg mMedium = new AisMsg.Builder(1).withPositionAccuracy(0).build();
                        Vessel vMedium = translator.translate(mMedium);

                        assertThat(XMLDataUtils.extractLocationRel(vMedium).getLocation()
                            .getLocationQualitativeAccuracy(), is(MEDIUM));
                    });
                });

                it("returns a Vessel with cog (in degrees instead of 1/10 od degrees)", () -> {
                    assertThat(XMLDataUtils.extractLocationRel(v).getCOG(), is(211.9D));
                });

                it("returns a Vessel with cog (null for cog=3600)", () -> {
                    AisMsg mc = new AisMsg.Builder(1)
                        .withCOG(360f)
                        .build();

                    Vessel vc = translator.translate(mc);

                    assertThat(XMLDataUtils.extractLocationRel(vc).getCOG(), is(nullValue()));
                });

                it("returns a Vessel with true heading", () -> {
                    assertThat(XMLDataUtils.extractLocationRel(v).getHeading(), is(210D));
                });

                it("returns a Vessel with heading (null for trueHeading=511)", () -> {
                    AisMsg mh = new AisMsg.Builder(1)
                        .withTrueHeading(511)
                        .build();

                    Vessel vh = translator.translate(mh);

                    assertThat(XMLDataUtils.extractLocationRel(vh).getHeading(), is(nullValue()));
                });

                it("returns a Vessel with source type", () -> {
                    assertThat(XMLDataUtils.extractLocationRel(v).getSourceType(), is(SourceType.DECLARATION));
                });

                it("returns a Vessel with sensor type", () -> {
                    assertThat(XMLDataUtils.extractLocationRel(v).getSensorType(),
                        is(SensorType.AUTOMATIC_IDENTIFICATION_SYSTEM));
                });

                it("returns a Vessel with sog (in knots instead of 1/10th of knots)", () -> {
                    assertThat(XMLDataUtils.extractLocationRel(v).getSOG(), is(13.8D));
                });

                it("returns a Vessel with sog (null for SOG=1023)", () -> {
                    AisMsg ms = new AisMsg.Builder(1)
                        .withSOG(102.3F)
                        .build();

                    Vessel vs = translator.translate(ms);
                    assertThat(XMLDataUtils.extractLocationRel(vs).getSOG(), is(nullValue()));
                });

                it("returns a Vessel with periodOfTime.startDate", () -> {
                    //"2018-02-19T14:43:16.550Z"
                    assertThat(XMLDataUtils.extractLocationRel(v).getPeriodOfTime().getStartDate(),
                        Matchers.is(XMLDataUtils.xmlDate(2018, 2, 19)));
                });

                it("returns a Vessel with periodOfTime.startTime", () -> {
                    //"2018-02-19T14:43:16.550Z"
                    assertThat(XMLDataUtils.extractLocationRel(v).getPeriodOfTime().getStartTime(),
                        Matchers.is(XMLDataUtils.xmlTime(14, 43, 16)));
                });

                it("returns a Vessel with MMSI", () -> {
                    assertThat(v.getMMSI(), is(538005989L));
                });

                it("returns a Vessel with navigationStatus", () -> {
                    assertThat(v.getNavigationalStatus(),
                        is(NavigationalStatusType.UNDER_WAY_USING_ENGINE));
                });
            });

        });
    }
}