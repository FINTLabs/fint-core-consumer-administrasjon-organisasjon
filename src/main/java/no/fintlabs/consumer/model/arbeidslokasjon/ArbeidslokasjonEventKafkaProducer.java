package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fintlabs.core.consumer.shared.resource.kafka.EventKafkaProducer;
import no.fintlabs.kafka.event.EventProducerFactory;
import no.fintlabs.kafka.event.topic.EventTopicService;
import org.springframework.stereotype.Service;

@Service
public class ArbeidslokasjonEventKafkaProducer extends EventKafkaProducer {
    public ArbeidslokasjonEventKafkaProducer(EventProducerFactory eventProducerFactory, ArbeidslokasjonConfig arbeidslokasjonConfig, EventTopicService eventTopicService) {
        super(eventProducerFactory, arbeidslokasjonConfig, eventTopicService);
    }
}
