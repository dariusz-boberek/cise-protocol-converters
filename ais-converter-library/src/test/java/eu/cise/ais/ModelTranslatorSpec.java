package eu.cise.ais;

import com.greghaskins.spectrum.Spectrum;
import eu.cise.ais.config.AisConverterConfiguration;
import eu.cise.ais.config.DefaultAisConverterConfiguration;
import eu.cise.ais.core.AisMsg;
import eu.cise.ais.converters.AisMsgToVesselConverter;
import eu.cise.datamodel.v1.entity.Entity;
import eu.cise.datamodel.v1.entity.organization.Organization;
import eu.cise.datamodel.v1.entity.uniqueidentifier.UniqueIdentifier;
import eu.cise.datamodel.v1.entity.vessel.Vessel;
import org.junit.runner.RunWith;

import java.util.Optional;

import static com.greghaskins.spectrum.Spectrum.*;
import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(Spectrum.class)
public class ModelTranslatorSpec {
    {
        describe("an AIS to CISE model translator", () -> {

            AisConverterConfiguration config = new DefaultAisConverterConfiguration();
            AisMsgToVesselConverter translator = new AisMsgToVesselConverter(config);

            describe("when a message type is not supported", () -> {
                asList(4, 6, 7, 8, 9, 10, 11)
                        .forEach((n) ->
                                         it("returns an empty optional / " + n, () -> {
                                             assertThat(translator.translate(new AisMsg.Builder(8).build()), is(Optional.empty()));
                                         })
                                );
            });

            describe("when a message type is 1,2,3 or 5", () -> {

                asList(1, 2, 3, 5).forEach((n) -> {
                    final AisMsg m = new AisMsg.Builder(n)
                            .withUserId(538005989)
                            .build();

                    it("returns an optional with an entity / " + n, () ->
                            assertThat(translator.translate(m), is(not(Optional.empty()))));

                    it("returns an Optional<Vessel> / " + n, () -> {
                        Optional<Entity> entity = translator.translate(m);

                        assertThat("The element in the payload must be a Vessel",
                                   entity.get(), instanceOf(Vessel.class));
                    });

                    it("returns a vessel with a uuid / " + n, () -> {
                        Vessel vessel = ((Vessel) translator.translate(m).get());

                        assertThat("the uuid is not null",
                                   vessel.getIdentifier(), notNullValue());
                    });

                    it("returns a uuid with an organization / " + n, () -> {
                        UniqueIdentifier id
                                = ((Vessel) translator.translate(m).get()).getIdentifier();

                        assertThat("the org is not null", id.getGeneratedBy(), notNullValue());
                    });

                    it("returns a legalName in the org object / " + n, () -> {
                        Organization org = ((Vessel) translator.translate(m).get())
                                .getIdentifier().getGeneratedBy();

                        assertThat("the legalName is not null",
                                   org.getLegalName(), notNullValue());

                        //organization.legal-name=A.C.M.E.
                        assertThat("the legalName is the one specified in the config",
                                   org.getLegalName(), is(config.getTestOrganization().getLegalName()));
                    });

                    it("returns a alternativeName in the org object / " + n, () -> {
                        Organization org = ((Vessel) translator.translate(m).get())
                                .getIdentifier().getGeneratedBy();

                        assertThat("the legalName is not null",
                                   org.getAlternativeName(), notNullValue());

                        assertThat("the legalName is the one specified in the config",
                                   org.getAlternativeName(), is(config.getTestOrganization().getAlternativeName()));
                    });
                    it("returns an uuid string not null / " + n, () -> {
                        UniqueIdentifier uuid= ((Vessel) translator.translate(m).get()).getIdentifier();

                        System.out.println(uuid.getUUID());
                        assertThat("the uuid is not null", uuid.getUUID(), notNullValue());
                    });
                });
            });
        });
    }
}

