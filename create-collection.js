db = db.getSiblingDB("featuredb");

db.feature_flags.insertOne({
  name: "catalog.inventory.enabled",
  enabled: true,
  rollout: 50,
  targetUsers: ["user1"],
  defaultValue: false
});