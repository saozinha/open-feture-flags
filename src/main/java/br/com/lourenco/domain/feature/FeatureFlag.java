package br.com.lourenco.domain.feature;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

@MongoEntity(collection = "feature_flags")
public class FeatureFlag {

    public ObjectId id;

    public String name;
    public boolean enabled;
    public Integer rollout;
    public List<String> targetUsers;

    public Map<String, String> attributes;
    public Boolean defaultValue = false;
}
