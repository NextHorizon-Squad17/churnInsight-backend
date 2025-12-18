# ChurnInsight Backend API
API REST responsável por receber dados de clientes e retornar uma previsão de churn (cancelamento), baseada em um modelo preditivo de Data Science.

Este projeto faz parte do MVP do ChurnInsight, desenvolvido durante um hackathon educacional.

# 📌 Objetivo

* Disponibilizar um endpoint HTTP que:
* receba dados de um cliente via JSON;
* valide as informações recebidas;
* retorne uma previsão de churn (Vai cancelar ou Vai continuar);
* inclua a probabilidade associada à previsão.

# ⚠️ Neste estágio, a previsão é mockada.
A integração com o modelo de Data Science será feita em uma etapa posterior.

🛠️ Tecnologias

* Java 17+

* Spring Boot

* Spring Web

* Bean Validation (Jakarta Validation)

* Maven

# 🚀 Como executar o projeto
Pré-requisitos

* Java 25 ou superior

* Maven 4.0+

# Executar localmente
```bash
  mvn spring-boot:run
```

```
A aplicação será iniciada em: localhost:8080
```

# 📡 Endpoint disponível
POST /predict

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
* algum campo obrigatório não é informado;
* o tipo do campo é inválido.

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
# 🔌 Integração com Data Science (Futuro)

A API foi projetada para integrar com um microserviço de Data Science responsável por:

* carregar o modelo preditivo;
* receber dados do cliente;
* retornar a previsão e probabilidade.
* Essa integração poderá ocorrer via:
* HTTP (FastAPI/Flask);
* ou carregamento de modelo serializado (ex.: ONNX).

# 📄 Status do projeto

- [x] Projeto Spring Boot criado
- [x] Endpoint /predict
- [x] Validação de entrada
- [x] Contrato fechado e documentado
- [ ] Integração com modelo de Data Science
- [ ] Persistência de previsões
- [ ] Dockerização