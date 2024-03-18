package no.fintlabs.consumer.model.arbeidslokasjon;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
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
public class ArbeidslokasjonService extends CacheService<ArbeidslokasjonResource> {

    private final EntityKafkaConsumer<ArbeidslokasjonResource> entityKafkaConsumer;

    private final ArbeidslokasjonLinker linker;

    private final ArbeidslokasjonResponseKafkaConsumer arbeidslokasjonResponseKafkaConsumer;

    public ArbeidslokasjonService(
            ArbeidslokasjonConfig consumerConfig,
            CacheManager cacheManager,
            ArbeidslokasjonEntityKafkaConsumer entityKafkaConsumer,
            ArbeidslokasjonLinker linker, ArbeidslokasjonResponseKafkaConsumer arbeidslokasjonResponseKafkaConsumer) {
        super(consumerConfig, cacheManager, entityKafkaConsumer);
        this.entityKafkaConsumer = entityKafkaConsumer;
        this.linker = linker;
        this.arbeidslokasjonResponseKafkaConsumer = arbeidslokasjonResponseKafkaConsumer;
    }

    @Override
    protected Cache<ArbeidslokasjonResource> initializeCache(CacheManager cacheManager, ConsumerConfig<ArbeidslokasjonResource> consumerConfig, String s) {
        return cacheManager.create(PackingTypes.POJO, consumerConfig.getOrgId(), consumerConfig.getResourceName());
    }

    @PostConstruct
    private void registerKafkaListener() {
        long retention = entityKafkaConsumer.registerListener(ArbeidslokasjonResource.class, this::addResourceToCache);
        getCache().setRetentionPeriodInMs(retention);
    }

    private void addResourceToCache(ConsumerRecord<String, ArbeidslokasjonResource> consumerRecord) {
        this.eventLogger.logDataRecieved();
        ArbeidslokasjonResource resource = consumerRecord.value();
        if (resource == null) {
            getCache().remove(consumerRecord.key());
        } else {
            linker.mapLinks(resource);
            this.getCache().put(consumerRecord.key(), resource, linker.hashCodes(resource));
            if (consumerRecord.headers().lastHeader("event-corr-id") != null){
                String corrId = new String(consumerRecord.headers().lastHeader("event-corr-id").value(), StandardCharsets.UTF_8);
                log.debug("Adding corrId to EntityResponseCache: {}", corrId);
                arbeidslokasjonResponseKafkaConsumer.getEntityCache().add(corrId, resource);
            }
        }
    }

    @Override
    public Optional<ArbeidslokasjonResource> getBySystemId(String systemId) {
        return getCache().getLastUpdatedByFilter(systemId.hashCode(),
                (resource) -> Optional
                        .ofNullable(resource)
                        .map(ArbeidslokasjonResource::getSystemId)
                        .map(Identifikator::getIdentifikatorverdi)
                        .map(systemId::equals)
                        .orElse(false)
        );
    }
}
