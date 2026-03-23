# Poc OpenFeature

## 📦 Feature Flags Platform (Quarkus + Mongo + OpenFeature)

---

## 🧠 Propósito

Este projeto implementa uma **plataforma de feature flags dinâmica**, permitindo:

- Ativar ou desativar funcionalidades em tempo real sem necessidade de deploy
- Gerenciar configurações de features de forma centralizada
- Ativar/desativar funcionalidades sem deploy

### 🎯 Controle de comportamento por:

- usuário
- rollout percentual
- centralização de regras de negócio em configuração dinâmica (MongoDB)

---

## 🏗️ Arquitetura

```text
Client (Frontend / API)
        |
Quarkus Backend
        |
OpenFeature SDK
        |
Custom Provider (MongoFeatureProvider)
        |
MongoDB
```

---

## 🚀 Tecnologias utilizadas

- Java 21
- Quarkus 3
- MongoDB
- OpenFeature

---

## 📂 Estrutura do projeto

```text
src/main/java
└── br.com.example
    ├── application.service   → Camada de aplicação (FeatureService)
    ├── config                → Configuração OpenFeature
    ├── domain                → Modelos e regras de negócio
    │   ├── feature
    │   ├── produto
    │   └── service
    ├── entryPoint            → APIs REST (resources)
    └── infrastructure
        ├── featureflag
        │   ├── producer      → OpenFeatureProducer
        │   └── provider      → MongoFeatureProvider
        └── persistence.repository → FeatureRepository
```

---

## ⚙️ Configuração

### 📄 application.properties

```properties
quarkus.mongodb.database=featuredb
```

---

# 🐳 Executando com Docker Compose (RECOMENDADO)


## ⚠️ Pré-requisito

Antes de subir o container:

```bash
mvn clean package -DskipTests
```

## 📦 Subir aplicação + Mongo

```bash
docker-compose up --build
```

---

## 🔧 Serviços iniciados

- MongoDB → localhost:27017
- Backend → localhost:8080

---

Após subir os containers, inserir dados :

```bash
docker exec -it mongo mongosh
```

---

## 🧾 Inserindo dados no Mongo


```js
use featuredb

db.feature_flags.insertOne({
  name: "catalog.inventory.enabled",
  enabled: true,
  rollout: 50,
  targetUsers: ["user1"],
  defaultValue: false
})
```

---

## ▶️ Executando a aplicação (modo local)

```bash
mvn quarkus:dev
```

---

## 🧪 Testando a API

✔ Endpoint

GET /produtos?id=1

✔ Exemplo com usuário

```bash
curl -H "userId: user1" localhost:8080/produtos?id=1
```

---

## ✔ Resposta esperada

### 🟢 Feature Flag ON

```json
{
  "id": "1",
  "nome": "Notebook",
  "estoque": 15
}
```

---

### 🔴 Feature Flag OFF

```json
{
  "id": "1",
  "nome": "Notebook"
}
```

---

## 🔄 Atualização dinâmica (sem deploy)

```js
db.feature_flags.updateOne(
  { name: "catalog.inventory.enabled" },
  { $set: { enabled: false } }
)
```

👉 A mudança é refletida imediatamente na aplicação.

---

## 🔥 Benefícios

- ✔ Sem deploy para novas features  
- ✔ Controle fino por usuário  
- ✔ Escalável para microservices
---

## ⚠️ Limitações atuais (POC)

- Sem cache
- Sem autenticação
- Sem auditoria
- Sem atualização em tempo real

---
