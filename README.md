coloque no padrão READM.md :

# Poc OpenFeature

## 📦 Feature Flags Platform (Quarkus + Mongo + OpenFeature)

## 🧠 Propósito

### Este projeto implementa uma plataforma de feature flags dinâmica, permitindo:

```text
- Ativar ou desativar funcionalidades em tempo real sem necessidade de deploy.
- Gerenciar configurações de features de forma centralizada.
- Ativar/desativar funcionalidades sem deploy
```

-
### Controlar comportamento por:

```text
-  usuário
-  rollout percentual
-  Centralizar regras de negócio em configuração dinâmica (MongoDB)
```

## 🏗️ Arquitetura


### Client (Frontend / API)

```text

Quarkus Backend
|
OpenFeature SDK
|
Custom Provider (MongoFeatureProvider)
|
MongoDB
```

## 🚀 Tecnologias utilizadas

```text
Java 21
Quarkus 3
MongoDB
```

## 📂 Estrutura do projeto

```text
src/main/java
├── config            → Configuração OpenFeature
├── feature           → Provider + Engine + Model
├── resource          → APIs REST
├── service           → Camada de uso das features
⚙️ Configuração
📄 application.properties
```

quarkus.mongodb.database = featuredb
### 🐳 Executando o MongoDB com Docker

```text
✔ Subir container
docker run -d -p 27017:27017 --name mongo mongo:6
✔ Acessar o Mongo shell
docker exec -it mongo mongosh
✔ Criar banco e inserir uma feature
use featuredb

```shell script
db.feature_flags.insertOne({
name: "catalog.inventory.enabled",
enabled: true,
rollout: 50,
targetUsers: ["user1"],
defaultValue: false
})

```

### ▶️ Executando a aplicação

```shell script
mvn quarkus:dev
```

### 🧪 Testando a API

```shell script
✔ Endpoint

GET /produtos?id=1

✔ Exemplo com usuário

curl -H "userId: user1" localhost:8080/produtos?id=1

----------------------------------------

✔ Resposta esperada

Feature ON
{
"id": "1",
"nome": "Notebook",
"estoque": 15
}

Feature OFF
{
"id": "1",
"nome": "Notebook"
}
```

