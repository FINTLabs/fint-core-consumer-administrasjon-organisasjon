package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fintlabs.core.consumer.shared.resource.event.EventResponseKafkaConsumer;
import no.fintlabs.kafka.event.EventConsumerFactoryService;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class ArbeidslokasjonResponseKafkaConsumer extends EventResponseKafkaConsumer<ArbeidslokasjonResource> {

    public ArbeidslokasjonResponseKafkaConsumer(EventConsumerFactoryService eventConsumerFactoryService, ArbeidslokasjonConfig arbeidslokasjonConfig, ArbeidslokasjonLinker arbeidslokasjonLinker, EventTopicService eventTopicService) {
        super(eventConsumerFactoryService, arbeidslokasjonConfig, arbeidslokasjonLinker, eventTopicService);
    }

}
