package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventRequestKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class ArbeidslokasjonRequestKafkaConsumer extends EventRequestKafkaConsumer<ArbeidslokasjonResource> {
    public ArbeidslokasjonRequestKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, ArbeidslokasjonConfig arbeidslokasjonConfig, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, arbeidslokasjonConfig, eventTopicService);
    }
}
