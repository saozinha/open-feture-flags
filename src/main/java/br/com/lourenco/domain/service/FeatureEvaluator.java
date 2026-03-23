package br.com.lourenco.domain.service;

import br.com.lourenco.domain.feature.FeatureFlag;

public class FeatureEvaluator {

    public static boolean evaluate(FeatureFlag flag, String userId) {

        if (flag == null) {
            return false;
        }

        // regra 1. Usuário específico (whitelist)
        if (flag.targetUsers != null && flag.targetUsers.contains(userId)) {
            return true;
        }

        // regra 2. Se não está habilitada globalmente
        if (!flag.enabled) {
            return false;
        }

        // regra 3. Rollout percentual
        if (flag.rollout != null) {
            int hash = Math.abs(userId.hashCode() % 100);
            return hash < flag.rollout;
        }

        // regra 4. fallback
        return flag.defaultValue != null ? flag.defaultValue : false;
    }
}