package no.fintlabs.consumer.config;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper() {
        return ImmutableMap.<String,String>builder()
            .put("no.fint.model.administrasjon.organisasjon.Arbeidslokasjon", "/administrasjon/organisasjon/arbeidslokasjon")
            .put("no.fint.model.administrasjon.organisasjon.Organisasjonselement", "/administrasjon/organisasjon/organisasjonselement")
            .build();
    }

}