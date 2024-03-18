package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class ArbeidslokasjonEntityKafkaConsumer extends EntityKafkaConsumer<ArbeidslokasjonResource> {

    public ArbeidslokasjonEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            ArbeidslokasjonConfig arbeidslokasjonConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, arbeidslokasjonConfig);
    }
}
