package no.fintlabs.consumer.model.organisasjonselement;

import lombok.extern.slf4j.Slf4j;
import no.fint.antlr.FintFilterService;
import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
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
@RequestMapping(name = "Organisasjonselement", value = RestEndpoints.ORGANISASJONSELEMENT, produces = {FintRelationsMediaType.APPLICATION_HAL_JSON_VALUE, MediaType.APPLICATION_JSON_VALUE})
public class OrganisasjonselementController extends WriteableConsumerRestController<OrganisasjonselementResource> {

    public OrganisasjonselementController(
            CacheService<OrganisasjonselementResource> cacheService,
            OrganisasjonselementLinker fintLinker,
            OrganisasjonselementConfig organisasjonselementConfig,
            OrganisasjonselementEventKafkaProducer organisasjonselementEventKafkaProducer,
            OrganisasjonselementResponseKafkaConsumer organisasjonselementResponseKafkaConsumer,
            FintFilterService odataFilterService,
            OrganisasjonselementRequestKafkaConsumer organisasjonselementRequestKafkaConsumer) {
        super(cacheService, fintLinker, organisasjonselementConfig, organisasjonselementEventKafkaProducer, organisasjonselementResponseKafkaConsumer, odataFilterService, organisasjonselementRequestKafkaConsumer);
    }
}
