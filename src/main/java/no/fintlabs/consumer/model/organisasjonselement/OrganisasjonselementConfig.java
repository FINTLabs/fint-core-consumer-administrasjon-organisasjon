package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class OrganisasjonselementConfig extends ConsumerConfig<OrganisasjonselementResource> {

    public OrganisasjonselementConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "organisasjonselement";
    }
}
