package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import org.springframework.stereotype.Service;

@Service
public class OrganisasjonselementRequestKafkaConsumer extends EventRequestKafkaConsumer<OrganisasjonselementResource> {
    public OrganisasjonselementRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, OrganisasjonselementConfig organisasjonselementConfig) {
        super(eventConsumerFactoryService, organisasjonselementConfig);
    }
}
