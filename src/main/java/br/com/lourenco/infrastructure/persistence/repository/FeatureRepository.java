package br.com.lourenco.infrastructure.persistence.repository;

import br.com.lourenco.domain.feature.FeatureFlag;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FeatureRepository implements PanacheMongoRepository<FeatureFlag> {

    public FeatureFlag findByName(String name) {
        return find("name", name).firstResult();
    }
}