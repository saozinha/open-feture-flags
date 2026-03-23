package br.com.lourenco.infrastructure.featureflag.producer;

import br.com.lourenco.infrastructure.featureflag.provider.MongoFeatureProvider;
import dev.openfeature.sdk.OpenFeatureAPI;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

@ApplicationScoped
public class OpenFeatureProducer {

    @Inject
    MongoFeatureProvider provider;

    @Produces
    @ApplicationScoped
    public OpenFeatureAPI openFeatureAPI() {

        OpenFeatureAPI api = OpenFeatureAPI.getInstance();

        api.setProvider(provider);

        return api;
    }
}