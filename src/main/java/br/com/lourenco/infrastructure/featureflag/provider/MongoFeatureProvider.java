package br.com.lourenco.infrastructure.featureflag.provider;

import br.com.lourenco.domain.feature.FeatureFlag;
import br.com.lourenco.domain.service.FeatureEvaluator;
import br.com.lourenco.infrastructure.persistence.repository.FeatureRepository;
import dev.openfeature.sdk.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MongoFeatureProvider implements FeatureProvider {

    @Inject
    FeatureRepository repository;

    @Override
    public Metadata getMetadata() {
        return () -> "mongo-provider";
    }

    // =========================
    // BOOLEAN (principal)
    // =========================
    @Override
    public ProviderEvaluation<Boolean> getBooleanEvaluation(
            String key,
            Boolean defaultValue,
            EvaluationContext context) {

        try {
            String userId = getUser(context);

            FeatureFlag flag = repository.findByName(key);

            boolean result = FeatureEvaluator.evaluate(flag, userId);

            return ProviderEvaluation.<Boolean>builder()
                    .value(result)
                    .build();

        } catch (Exception e) {
            return error(defaultValue, e);
        }
    }

    // =========================
    // STRING
    // =========================
    @Override
    public ProviderEvaluation<String> getStringEvaluation(
            String key,
            String defaultValue,
            EvaluationContext context) {

        return ProviderEvaluation.<String>builder()
                .value(defaultValue)
                .build();
    }

    // =========================
    // INTEGER
    // =========================
    @Override
    public ProviderEvaluation<Integer> getIntegerEvaluation(
            String key,
            Integer defaultValue,
            EvaluationContext context) {

        return ProviderEvaluation.<Integer>builder()
                .value(defaultValue)
                .build();
    }

    // =========================
    // DOUBLE
    // =========================
    @Override
    public ProviderEvaluation<Double> getDoubleEvaluation(
            String key,
            Double defaultValue,
            EvaluationContext context) {

        return ProviderEvaluation.<Double>builder()
                .value(defaultValue)
                .build();
    }

    // =========================
    // OBJECT
    // =========================
    @Override
    public ProviderEvaluation<Value> getObjectEvaluation(
            String key,
            Value defaultValue,
            EvaluationContext context) {

        return ProviderEvaluation.<Value>builder()
                .value(defaultValue)
                .build();
    }

    // =========================
    // LIFECYCLE
    // =========================
    @Override
    public void initialize(EvaluationContext context) {
        // opcional
    }

    @Override
    public void shutdown() {
        // opcional
    }

    // =========================
    // HELPERS
    // =========================
    private String getUser(EvaluationContext context) {
        if (context != null && context.getValue("userId") != null) {
            return context.getValue("userId").asString();
        }
        return "anonymous";
    }

    private <T> ProviderEvaluation<T> error(T defaultValue, Exception e) {
        return ProviderEvaluation.<T>builder()
                .value(defaultValue)
                .errorCode(ErrorCode.GENERAL)
                .errorMessage(e.getMessage())
                .build();
    }
}