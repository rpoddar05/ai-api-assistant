# AI API Assistant

## Overview

AI API Assistant is a backend system built using **Java + Spring Boot** that generates **structured API design suggestions** from high-level requirements using an LLM.

The system focuses on **clean backend architecture**, **strict output parsing**, and **reliable LLM integration**, treating AI as an external dependency rather than a black box.

---

## Problem

Designing backend APIs requires repetitive decisions:

* Endpoint naming
* DTO design
* Validation rules
* Error handling strategies

These are often manually created and inconsistent across teams.

---

## Solution

This service accepts structured API requirements and generates:

* REST endpoint design
* Controller & Service naming
* Request/Response DTOs
* Validation suggestions
* Error handling strategies

The output is returned as **strictly validated JSON mapped to typed DTOs**, ensuring consistency and reliability.

---

## Architecture

```
Client Request
    ↓
Controller (REST)
    ↓
Service Layer (Orchestration)
    ↓
PromptBuilder (Structured Prompt)
    ↓
LlmClient (OpenAI SDK Integration)
    ↓
Raw LLM Response (Untrusted Text)
    ↓
LlmResponseParser (Validation + JSON Mapping)
    ↓
Typed API Response DTO
```

---

## Tech Stack

* **Java 17**
* **Spring Boot**
* **OpenAI Java SDK**
* **Jackson (JSON parsing)**
* **Lombok**
* **JUnit 5**

---

## Key Features

### 1. Clean LLM Abstraction Layer

* OpenAI SDK isolated behind `LlmClient`
* Service layer remains decoupled from LLM provider

---

### 2. Structured Output Parsing

* LLM output treated as **untrusted external input**
* Strict JSON parsing using `ObjectMapper`
* Fail-fast on malformed responses

---

### 3. Prompt Engineering

* Deterministic prompt with strict schema enforcement
* Prevents extra fields, markdown, and invalid structure

---

### 4. Resilient Parsing Layer

* Handles:

    * Markdown-wrapped JSON
    * Extra text around JSON
    * Partial formatting issues

---

### 5. Structured Logging

* Debug-level logging for LLM flow
* File-based logging with rotation
* Clear trace of request → response lifecycle

---

### 6. Unit Testing for LLM Components

* Parser tested against:

    * valid JSON
    * markdown-wrapped responses
    * invalid input
* Prompt builder validated for schema integrity

---

## Why This Project Matters

This project demonstrates how to safely integrate LLMs into backend systems by treating them as unreliable external services and enforcing strict output validation.

It focuses on building production-grade patterns around AI:
- deterministic prompt design
- strict schema validation
- failure handling for non-deterministic outputs

---

## API Usage

### Endpoint

```
POST /api/v1/api-assistant/design
```

### Sample Request

```json
{
  "resourceName": "User",
  "operation": "CREATE",
  "description": "Create a new user",
  "requestFields": [
    {
      "name": "email",
      "type": "String",
      "required": true
    }
  ]
}
```

---

### Sample Response

```json
{
  "resourceName": "User",
  "operation": "CREATE",
  "endpoint": {
    "httpMethod": "POST",
    "path": "/users",
    "purpose": "Create a new user profile"
  },
  "controllerName": "UserController",
  "serviceName": "UserService",
  "requestDtoName": "CreateUserRequest",
  "responseDtoName": "CreateUserResponse"
}
```

---

## Running Tests

```bash
mvn test
```

---

## Running the Application

```bash
./mvnw spring-boot:run
```

---

## Configuration

Set your OpenAI API key as an environment variable:

```bash
export OPENAI_API_KEY=your_key_here
```

---

## Key Engineering Decisions

* **LLM treated as external system**, not trusted source
* **Strict DTO mapping instead of free-form text usage**
* **Separation of concerns across prompt, client, and parser**
* **Fail-fast error handling for invalid responses**
* **Test coverage for non-deterministic components**

---

## Key Contributions

* Built an AI-assisted backend system using **Java + Spring Boot + OpenAI SDK**
* Designed a **structured prompt and parsing pipeline** to convert LLM output into typed DTOs
* Implemented **resilient JSON parsing** for non-deterministic LLM responses
* Added **unit tests for parser and prompt builder**, ensuring robustness against malformed outputs
* Introduced **logging and error handling layers** for production-grade observability

---
