# 📬 Notification Approve System

Este projeto implementa um sistema completo de cadastro e notificação baseado em **arquitetura hexagonal (Ports & Adapters)**, utilizando **Spring Boot** com padrões REST e containerização via Docker. O fluxo central consiste em receber dados de pessoas e enviar uma notificação com atraso de 2 minutos para uma fila, onde um consumidor trata o envio de e-mails via SendGrid.

---

## 🧱 Arquitetura

A arquitetura segue o modelo **Hexagonal (Ports & Adapters)**, promovendo uma separação clara entre a lógica de negócio (core domain) e suas interfaces externas, como banco de dados, fila de mensagens e gateways externos. Essa abordagem facilita testes, manutenção e extensão futura da aplicação.

![functional-draw-arch-hexagonal-and-fluxogram](https://github.com/user-attachments/assets/b839c5c0-26ea-4bf5-87d8-ba57bcafb642)


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
| Dev       | `apenas localhost`                                    | `https://api.datadoghq.com`                                                                                                         |
| Staging   | `https://app-producer-staging.up.railway.app`         | `https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/observability?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a` |
| Prod      | `limitação free tier`                                 | `limitação free tier`                                                                                                                                 |

### Exemplos de Endpoints

- `POST {BASE_URL}/notify`
- `GET {BASE_URL}/notify/all`
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
```

## 📬 Fila de Mensagens com Delay
A fila de mensagens implementa um Delayed Letter Queue, configurada para reter as mensagens por 2 minutos antes de disponibilizá-las ao consumidor. Isso garante que a notificação não seja enviada imediatamente após o cadastro.

Producer: Envia a mensagem para a fila

Consumer: Processa após o delay e envia a notificação via SendGrid (e-mail).

## 🔍 Observabilidade

- Logs de aplicação estão disponíveis na plataforma Railway

- Métricas estão expostas via Spring Boot Actuator

- Links de exemplo (Railway)

  - Métricas Railway:
    - Producer: https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/service/4c5719d1-052e-4618-a6f7-cf612170a37a/metrics?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a
    - Consumer: https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/service/5837e00c-18b2-4197-aafd-e7befa1a6737/metrics?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a 
  - Logs Railway:
    - Producer: https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/service/4c5719d1-052e-4618-a6f7-cf612170a37a?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a&id=d0d3905b-b76d-4d6f-9172-324c82edaf3e#deploy
    - Consumer: https://railway.com/project/ad16e77e-968a-4ad9-9203-2d9955ae117d/service/5837e00c-18b2-4197-aafd-e7befa1a6737?environmentId=8abe6965-ba78-4d2b-ac3b-18ba911eef1a&id=b348914a-5e52-4caa-8296-e8559e68f074#deploy

## 🚀 Manual de Configuração e 🐳 Docker
### Producer
1. Clone o repositório:
```bash
git clone https://github.com/thiagomachadoo/app-producer.git
cd app-producer
```
2. Configure as variáveis de ambiente:
```bash
SPRING_PROFILES_ACTIVE=dev
DD_API_KEY=SUA_API_KEY_DATADOG
```
3. Compile e execute:
```bash
./mvnw clean package
docker build -t app-producer .
```
### Consumer
1. Clone o repositório:
```bash
git clone https://github.com/thiagomachadoo/app-consumer.git
cd app-consumer
```
2. Configure as variáveis de ambiente:
```bash
SPRING_PROFILES_ACTIVE=dev
FROM_EMAIL_ADDRESS=EMAIL_DO_REMETENTE
SENDGRID_API_KEY=SUA_API_KEY_SENDGRID
TO_EMAIL_ADDRESS=EMAIL_DE_DESTINO
```
3. Compile e execute:
```bash
./mvnw clean package
docker build -t app-consumer .
```

# ⚠️ Importante ⚠️
- Retorne para a pasta raiz de app-producer e inicialize as imagens docker
```bash
docker-compose up --build
```

### 🧩 Tecnologias e Ferramentas Utilizadas

| Item                  | Ferramenta/Tech                  |
|-----------------------|----------------------------------|
| Framework REST        | Spring Boot                      |
| Arquitetura           | Hexagonal (Ports & Adapters)     |
| Containerização       | Docker, Docker Hub               |
| Mensageria            | RabbitMQ / CloudAMQP             |
| Persistência          | MongoDB                          |
| API Gateway & Deploy  | Railway                          |
| Observabilidade       | Railway, Datadog                 |
| Logs                  | Logback (default do Spring)      |
| Testes                | JUnit 5 + Mockito                |
| Repositório           | GitHub                           |


## ✉️ Contato
- thiagodasilvamachadoo44@gmail.com
- https://www.linkedin.com/in/thiago-smachadoo/

