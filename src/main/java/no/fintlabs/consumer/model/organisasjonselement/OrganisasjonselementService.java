package no.fintlabs.consumer.model.organisasjonselement;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fintlabs.cache.Cache;
import no.fintlabs.cache.CacheManager;
import no.fintlabs.cache.packing.PackingTypes;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.ConsumerConfig;
import no.fintlabs.core.consumer.shared.resource.kafka.EntityKafkaConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Slf4j
@Service
public class OrganisasjonselementService extends CacheService<OrganisasjonselementResource> {

    private final EntityKafkaConsumer<OrganisasjonselementResource> entityKafkaConsumer;

    private final OrganisasjonselementLinker linker;

    private final OrganisasjonselementResponseKafkaConsumer organisasjonselementResponseKafkaConsumer;

    public OrganisasjonselementService(
            OrganisasjonselementConfig consumerConfig,
            CacheManager cacheManager,
            OrganisasjonselementEntityKafkaConsumer entityKafkaConsumer,
            OrganisasjonselementLinker linker, OrganisasjonselementResponseKafkaConsumer organisasjonselementResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.organisasjonselementResponseKafkaConsumer = organisasjonselementResponseKafkaConsumer;
    }

    @Override
    protected Cache<OrganisasjonselementResource> initializeCache(CacheManager cacheManager, ConsumerConfig<OrganisasjonselementResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(OrganisasjonselementResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, OrganisasjonselementResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        OrganisasjonselementResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null){
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                organisasjonselementResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<OrganisasjonselementResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(OrganisasjonselementResource::getOrganisasjonsId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
