package no.fintlabs.consumer.model.organisasjonselement;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class OrganisasjonselementEventKafkaProducer extends EventKafkaProducer {
    public OrganisasjonselementEventKafkaProducer(EventProducerFactory eventProducerFactory, OrganisasjonselementConfig organisasjonselementConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, organisasjonselementConfig, eventTopicService);
    }
}
