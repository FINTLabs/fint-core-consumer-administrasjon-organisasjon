package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class OrganisasjonselementResponseKafkaConsumer extends EventResponseKafkaConsumer<OrganisasjonselementResource> {

    public OrganisasjonselementResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, OrganisasjonselementConfig organisasjonselementConfig, OrganisasjonselementLinker organisasjonselementLinker) {
        super(eventConsumerFactoryService, organisasjonselementConfig, organisasjonselementLinker);
    }

}
