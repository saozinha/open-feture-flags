package br.com.lourenco.application.service;

import dev.openfeature.sdk.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.Map;

@ApplicationScoped
public class FeatureService {

    @Inject
    OpenFeatureAPI api;

    /**
     * Verifica se a feature de estoque está ativa
     */
    public boolean isInventoryEnabled(String userId) {

        Client client = api.getClient();

        EvaluationContext context = new ImmutableContext(
                userId,
                Map.of(
                        "userId", new Value(userId)
                )
        );

        return client.getBooleanValue(
                "catalog.inventory.enabled",
                false,
                context
        );
    }

    /**
     * Método genérico para qualquer feature
     */
    public boolean isEnabled(String featureName, String userId) {

        Client client = api.getClient();

        EvaluationContext context = new ImmutableContext(
                userId,
                Map.of(
                        "userId", new Value(userId)
                )
        );

        return client.getBooleanValue(
                featureName,
                false,
                context
        );
    }
}
