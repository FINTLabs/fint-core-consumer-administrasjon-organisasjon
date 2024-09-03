package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class OrganisasjonselementResponseKafkaConsumer extends EventResponseKafkaConsumer<OrganisasjonselementResource> {

    public OrganisasjonselementResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, OrganisasjonselementConfig organisasjonselementConfig, OrganisasjonselementLinker organisasjonselementLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, organisasjonselementConfig, organisasjonselementLinker, eventTopicService);
    }

}
