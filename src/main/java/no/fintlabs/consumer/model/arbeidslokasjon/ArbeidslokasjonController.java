package no.fintlabs.consumer.model.arbeidslokasjon;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fint.relations.FintRelationsMediaType;
import no.fintlabs.consumer.config.RestEndpoints;
import no.fintlabs.core.consumer.shared.resource.CacheService;
import no.fintlabs.core.consumer.shared.resource.WriteableConsumerRestController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(name = "Arbeidslokasjon", value = RestEndpoints.ARBEIDSLOKASJON, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class ArbeidslokasjonController extends WriteableConsumerRestController<ArbeidslokasjonResource> {

    public ArbeidslokasjonController(
            CacheService<ArbeidslokasjonResource> cacheService,
            ArbeidslokasjonLinker fintLinker,
            ArbeidslokasjonConfig arbeidslokasjonConfig,
            ArbeidslokasjonEventKafkaProducer arbeidslokasjonEventKafkaProducer,
            ArbeidslokasjonResponseKafkaConsumer arbeidslokasjonResponseKafkaConsumer,
            FintFilterService odataFilterService,
            ArbeidslokasjonRequestKafkaConsumer arbeidslokasjonRequestKafkaConsumer) {
        super(cacheService, fintLinker, arbeidslokasjonConfig, arbeidslokasjonEventKafkaProducer, arbeidslokasjonResponseKafkaConsumer, odataFilterService, arbeidslokasjonRequestKafkaConsumer);
    }
}
