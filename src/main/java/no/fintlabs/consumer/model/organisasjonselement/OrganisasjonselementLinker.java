package no.fintlabs.consumer.model.organisasjonselement;

import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResource;
import no.fint.model.resource.administrasjon.organisasjon.OrganisasjonselementResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class OrganisasjonselementLinker extends FintLinker<OrganisasjonselementResource> {

    public OrganisasjonselementLinker() {
        super(OrganisasjonselementResource.class);
    }

    public void mapLinks(OrganisasjonselementResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public OrganisasjonselementResources toResources(Collection<OrganisasjonselementResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public OrganisasjonselementResources toResources(Stream<OrganisasjonselementResource> stream, int offset, int size, int totalItems) {
        OrganisasjonselementResources resources = new OrganisasjonselementResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(OrganisasjonselementResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(OrganisasjonselementResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getOrganisasjonsId()) && !StringUtils.isEmpty(resource.getOrganisasjonsId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getOrganisasjonsId().getIdentifikatorverdi(), "organisasjonsid"));
        }

        if (!isNull(resource.getOrganisasjonsKode()) && !StringUtils.isEmpty(resource.getOrganisasjonsKode().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getOrganisasjonsKode().getIdentifikatorverdi(), "organisasjonskode"));
        }

        if (!isNull(resource.getOrganisasjonsnummer()) && !StringUtils.isEmpty(resource.getOrganisasjonsnummer().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getOrganisasjonsnummer().getIdentifikatorverdi(), "organisasjonsnummer"));
        }

        return builder.build();
    }

    int[] hashCodes(OrganisasjonselementResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getOrganisasjonsId()) && !StringUtils.isEmpty(resource.getOrganisasjonsId().getIdentifikatorverdi())) {
            builder.add(resource.getOrganisasjonsId().getIdentifikatorverdi().hashCode());
        }

        if (!isNull(resource.getOrganisasjonsKode()) && !StringUtils.isEmpty(resource.getOrganisasjonsKode().getIdentifikatorverdi())) {
            builder.add(resource.getOrganisasjonsKode().getIdentifikatorverdi().hashCode());
        }

        if (!isNull(resource.getOrganisasjonsnummer()) && !StringUtils.isEmpty(resource.getOrganisasjonsnummer().getIdentifikatorverdi())) {
            builder.add(resource.getOrganisasjonsnummer().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}