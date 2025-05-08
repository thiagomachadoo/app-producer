# 📬 Notification Approve System

Este projeto implementa um sistema completo de cadastro e notificação baseado em **arquitetura hexagonal (Ports & Adapters)**, utilizando **Spring Boot** com padrões REST e containerização via Docker. O fluxo central consiste em receber dados de pessoas e enviar uma notificação com atraso de 2 minutos para uma fila, onde um consumidor trata o envio de e-mails via SendGrid ou mensagens via Twilio.

---

## 🧱 Arquitetura

A arquitetura segue o modelo **Hexagonal (Ports & Adapters)**, promovendo uma separação clara entre a lógica de negócio (core domain) e suas interfaces externas, como banco de dados, fila de mensagens e gateways externos. Essa abordagem facilita testes, manutenção e extensão futura da aplicação.

![functional-draw-arch-hexagonal-and-fluxogram](https://github.com/user-attachments/assets/2fedef02-fdc9-48c7-aa57-8d076c838104)


## ✅ Requisitos Funcionais

- POST `/notify` → Efetuar um cadastro com os dados:
  - Nome
  - Sobrenome
  - Idade
  - País
- GET `/notify/{id}` → Consultar cadastro por ID
- GET `/notify/all` → Listar todos os cadastros
- PATCH `/notify/{id}` → Atualizar um ou mais dados de um cadastro
- DELETE `/notify/{id}` → Remover um cadastro

🔁 Após o POST, o sistema envia uma notificação **por e-mail** com um **delay de 2 minutos**, utilizando uma **Delayed Letter Queue**.

---

## 📡 Requisitos Não Funcionais

- API exposta via **API Gateway** (Railway/Render)
- Padrão **RESTful**
- **Observabilidade**:
  - Métricas (via Spring Boot Actuator)
  - Logs (via Logback + Railway Dashboard)
- Arquitetura baseada em **Hexagonal Design**
- Deploy em container com **Docker**
- Testes unitários com **cobertura ≥ 90%** (`JUnit 5`, `Mockito`)
- Código hospedado em repositório público no **GitHub**
- Manual de implantação incluído neste arquivo

---

## 🌐 Ambientes e Endpoints

| Ambiente  | Base URL (API Gateway)                                | Observability URL                                                                                                                   |
|-----------|-------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Dev       | `local`                                               | `em progresso`                                                                                                                      |
| Staging   | `https://app-producer-staging.up.railway.app`         | `https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/observability?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a` |
| Prod      | `https://app-producer-production-4558.up.railway.app` | `https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/observability?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a` |

### Exemplos de Endpoints

- `POST {BASE_URL}/notify`
- `GET {BASE_URL}/notify`
- `GET {BASE_URL}/notify/{id}`
- `PATCH {BASE_URL}/notify/{id}`
- `DELETE {BASE_URL}/notify/{id}`

---

## 🧪 Testes

- Testes unitários escritos com **JUnit 5** e **Mockito**
- Cobertura acima de 90% (verificada com Jacoco)
- Testes implementados para:
  - Services
  - Consumers
  - Handlers de exceção
  - Adapters e Ports

```bash
./mvnw clean test
