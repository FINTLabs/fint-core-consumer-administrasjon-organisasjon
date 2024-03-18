package no.fintlabs.consumer.model.arbeidslokasjon;

import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResource;
import no.fint.model.resource.administrasjon.organisasjon.ArbeidslokasjonResources;
import no.fint.relations.FintLinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;

@Component
public class ArbeidslokasjonLinker extends FintLinker<ArbeidslokasjonResource> {

    public ArbeidslokasjonLinker() {
        super(ArbeidslokasjonResource.class);
    }

    public void mapLinks(ArbeidslokasjonResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ArbeidslokasjonResources toResources(Collection<ArbeidslokasjonResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ArbeidslokasjonResources toResources(Stream<ArbeidslokasjonResource> stream, int offset, int size, int totalItems) {
        ArbeidslokasjonResources resources = new ArbeidslokasjonResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ArbeidslokasjonResource resource) {
        return getAllSelfHrefs(resource).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ArbeidslokasjonResource resource) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(resource.getSystemId().getIdentifikatorverdi(), "systemid"));
        }

        return builder.build();
    }

    int[] hashCodes(ArbeidslokasjonResource resource) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(resource.getSystemId()) && !StringUtils.isEmpty(resource.getSystemId().getIdentifikatorverdi())) {
            builder.add(resource.getSystemId().getIdentifikatorverdi().hashCode());
        }

        return builder.build().toArray();
    }
}