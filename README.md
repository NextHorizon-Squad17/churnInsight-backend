# ChurnInsight Backend API
API REST responsável por receber dados de clientes e retornar uma previsão de churn (cancelamento), baseada em um modelo preditivo de Data Science.

Este projeto faz parte do MVP do ChurnInsight, desenvolvido durante um hackathon educacional.

# 📌 Objetivo

Disponibilizar um endpoint HTTP que:
* Receba dados de um cliente via JSON;
* Valide as informações recebidas;
* Retorne uma previsão de churn (Vai cancelar ou Vai continuar);
* Inclua a probabilidade associada à previsão.

# ⚠️ Neste estágio, a previsão é mockada.
A integração com o modelo de Data Science será feita em uma etapa posterior.

# 🛠️ Tecnologias

* Java 17+
* Spring Boot
* Spring Web
* Bean Validation (Jakarta Validation)
* Maven
* Docker
* Docker Compose

# 🚀 Como executar o projeto

## Pré-requisitos

### Execução local

* Java 25 ou superior
* Maven 4.0+

### Execução com Docker

* Docker
* Docker Compose

---

## 🐳 Executando o projeto com Docker (recomendado)

### 1️⃣ Clonar o repositório

```bash
git clone <url-do-repositorio>
cd churninsight-backend
```

---

### 2️⃣ Configurar variáveis de ambiente

O projeto utiliza variáveis de ambiente para configuração de **perfil**, **JWT**, **banco de dados** e **bootstrap do usuário administrador**.

Copie o arquivo de exemplo:

```bash
cp .env-example .env
```

Edite o arquivo `.env` conforme necessário:

```env
SPRING_PROFILE_ACTIVE=dev

SECURITY_JWT_SECRET=dev-secret-123
JWT_EXPIRATION_SECONDS=3600

DB_HOST=db
DB_PORT=5432
DB_NAME=churn_insight_db
DB_USER=user123
DB_PASSWORD=pass123

BOOTSTRAP_ADMIN_EMAIL=admin@local.dev
BOOTSTRAP_ADMIN_PASSWORD=admin123
BOOTSTRAP_ADMIN_NAME=Administrator
```

### 🔐 Bootstrap de usuário administrador

- Quando a aplicação é iniciada **pela primeira vez**
- E **não existem usuários cadastrados no banco**
- Um usuário administrador será criado automaticamente usando as variáveis acima

> ⚠️ Em produção, recomenda-se:
> - Utilizar senhas fortes
> - Armazenar segredos em um cofre (AWS Secrets Manager, Vault, etc)
> - Desabilitar o bootstrap após o primeiro deploy

---

### 3️⃣ Subir a aplicação

```bash
docker compose up --build
```

A aplicação irá:
- Subir o banco PostgreSQL
- Executar as migrations (Flyway)
- Iniciar a API Spring Boot
- Criar o usuário administrador (se necessário)

A API ficará disponível em:

```
http://localhost:8080
```

---

## ▶️ Executando localmente (sem Docker)

```bash
mvn spring-boot:run
```

A aplicação será iniciada em:

```
http://localhost:8080
```

> ⚠️ Para execução local sem Docker, é necessário configurar manualmente as variáveis de ambiente e um banco PostgreSQL acessível.

---

# 📡 Endpoint disponível

## POST /predict

Recebe dados de um cliente e retorna a previsão de churn.

# 📥 Contrato de Entrada (Request)
```json
{
  "tempo_contrato_meses": 12,
  "atrasos_pagamento": 2,
  "uso_mensal": 14.5,
  "plano": "Premium"
}
```
# Campos

| Campo                   | Tipo    | Obrigatório | Descrição |
|-------------------------|---------|-----|-------|
| tempo_de_contrato_meses | integer | Sim | Tempo decontrato do cliente em meses
| atrasos_pagamento       |integer| Sim | Número de atrasos em pagamento|
| uso_mensal              | number | Sim | Média de uso mensal do serviço|
| plano                   | string | Sim | Tipo de plano do cliente

# 📤 Contrato de Saída (Response)
```
{
  "previsao": "Vai cancelar",
  "probabilidade": 0.81
}
```
# Campos

| Campo    | Tipo    | Descrição |
|----------|---------|----|
| previsao | string | Resultado da previsão | 
| probabilidade | number | Probabilidade associada à previsão (0 a 1)|  

# ❌ Tratamento de erros

```json
{
  "error": "Campo 'tempo_contrato_meses' é obrigatório"
}
```
A API retorna erro 400 quando:
* Algum campo obrigatório não é informado;
* O tipo do campo é inválido.

# 🧪 Exemplos de uso
Exemplo 1 – Cliente com risco de churn
```json
{
  "tempo_contrato_meses": 3,
  "atrasos_pagamento": 4,
  "uso_mensal": 2.1,
  "plano": "Basico"
}
```
Resposta:
```json
{
  "previsao": "Vai cancelar",
  "probabilidade": 0.87
}
```

# Exemplo 2 – Cliente sem risco de churn
```json
{
  "tempo_contrato_meses": 24,
  "atrasos_pagamento": 0,
  "uso_mensal": 25.0,
  "plano": "Premium"
}
```
Resposta:
```json
{
  "previsao": "Vai continuar",
  "probabilidade": 0.12
}
```
# 🔌 Integração com Data Science

A API foi projetada para integrar com um microserviço de Data Science responsável por:

* Carregar o modelo preditivo;
* Receber dados do cliente;
* Retornar a previsão e probabilidade.
* Essa integração poderá ocorrer via:
  * HTTP (FastAPI/Flask);
  * Ou carregamento de modelo serializado (ex.: ONNX).

# 📄 Status do projeto

- [x] Projeto Spring Boot criado
- [x] Endpoint /predict
- [x] Validação de entrada
- [x] Contrato fechado e documentado
- [x] Integração com modelo de Data Science
- [x] Persistência de previsões
- [x] Dockerização
