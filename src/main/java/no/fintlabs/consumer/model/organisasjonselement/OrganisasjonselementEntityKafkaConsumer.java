package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import no.fintlabs.kafka.common.ListenerBeanRegistrationService;
import no.fintlabs.kafka.entity.EntityConsumerFactoryService;
import no.fintlabs.kafka.entity.topic.EntityTopicService;
import org.springframework.stereotype.Service;

@Service
public class OrganisasjonselementEntityKafkaConsumer extends EntityKafkaConsumer<OrganisasjonselementResource> {

    public OrganisasjonselementEntityKafkaConsumer(
            EntityConsumerFactoryService entityConsumerFactoryService,
            ListenerBeanRegistrationService listenerBeanRegistrationService,
            EntityTopicService entityTopicService,
            OrganisasjonselementConfig organisasjonselementConfig) {
        super(entityConsumerFactoryService, listenerBeanRegistrationService, entityTopicService, organisasjonselementConfig);
    }
}
