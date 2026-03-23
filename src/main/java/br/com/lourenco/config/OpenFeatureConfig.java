package br.com.lourenco.config;

import br.com.lourenco.infrastructure.featureflag.provider.MongoFeatureProvider;
import dev.openfeature.sdk.OpenFeatureAPI;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
@ApplicationScoped
public class OpenFeatureConfig {

    @Inject
    MongoFeatureProvider provider;

    @PostConstruct
    void init() {
        OpenFeatureAPI.getInstance().setProvider(provider);
    }
}