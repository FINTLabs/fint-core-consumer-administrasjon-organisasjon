package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fintlabs.core.consumer.shared.config.ConsumerProps;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import org.springframework.stereotype.Component;

@Component
public class ArbeidslokasjonConfig extends ConsumerConfig<ArbeidslokasjonResource> {

    public ArbeidslokasjonConfig(ConsumerProps consumerProps) {
        super(consumerProps);
    }

    @Override
    protected String resourceName() {
        return "arbeidslokasjon";
    }
}
