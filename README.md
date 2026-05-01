# Tech Challenge - Sistema de Restaurantes (Fase 1)

Este repositório contém a implementação do backend da **Fase 1 do Tech Challenge**, desenvolvido para a Pós-Graduação em Arquitetura e Desenvolvimento Java.

## 📖 O Problema
Um grupo de restaurantes locais decidiu se unir para construir um sistema de gestão único e compartilhado, motivados pelo alto custo de soluções individuais. O objetivo é criar um sistema robusto que permita a todos os restaurantes gerenciar eficientemente suas operações, além de permitir que clientes consultem informações e façam pedidos.

Esta Fase 1 foca exclusivamente na criação da base estrutural do sistema e no **Gerenciamento de Usuários**.

## 🚀 Tecnologias e Padrões Utilizados
* **Java 21**
* **Spring Boot 3** (Web, Data JPA, Validation)
* **Spring Security & JWT** (Autenticação baseada em tokens)
* **Arquitetura Hexagonal** (Ports and Adapters / Clean Architecture)
* **PostgreSQL** (Banco de dados relacional)
* **Docker & Docker Compose** (Containerização e Orquestração)
* **Springdoc OpenAPI / Swagger** (Documentação da API)
* **MapStruct** (Mapeamento de Objetos)
* **JUnit 5 & Mockito** (Testes Unitários)
* **ProblemDetail (RFC 7807)** (Padronização de respostas de erro)

## ⚙️ Funcionalidades Implementadas
O sistema atende a todos os requisitos funcionais solicitados:
* Cadastro, atualização e exclusão de usuários.
* Dois tipos de perfis implementados: `DONO_RESTAURANTE` e `CLIENTE`.
* Troca de senha do usuário isolada em um endpoint separado.
* Atualização de dados cadastrais em endpoint distinto do de senha.
* Busca de usuários por nome.
* Garantia de unicidade de e-mail e login cadastrados.
* Validação obrigatória de login utilizando Spring Security e JWT.
* API versionada (`/v1/`).

## 🏗️ Arquitetura
O projeto foi desenvolvido sob os princípios da **Arquitetura Hexagonal**, dividindo a aplicação em camadas bem definidas para garantir baixo acoplamento e alta coesão:
* **Core:** Contém o domínio da aplicação (`Usuario`, `Endereco`) e as regras de negócio puras nos Casos de Uso (`UsuarioUseCase`). Não possui dependências de frameworks externos.
* **Ports:** Interfaces que definem os contratos de entrada (Input Ports - expostos para a web) e de saída (Output Ports - acesso a banco, criptografia).
* **Adapters:** Implementações técnicas dos conectores.
    * *Inbound Adapters:* Controladores REST (`UsuarioController`, `AuthController`).
    * *Outbound Adapters:* Repositórios JPA (`UsuarioRepositoryAdapter`) e adaptadores de segurança (`BCryptSenhaAdapter`).

## 🐳 Como Executar (Docker)
A aplicação está dockerizada e orquestrada com Docker Compose, subindo simultaneamente a API Java e o banco de dados PostgreSQL.

**Pré-requisitos:** Docker e Docker Desktop instalados e rodando em sua máquina.

1. Clone o repositório.
2. Na raiz do projeto (onde está o arquivo `docker-compose.yml`), abra o terminal e execute:
   ```bash
   docker compose up -d --build
   
3. Aguarde alguns segundos para o Spring Boot inicializar. A aplicação estará rodando na porta 8080 e o banco na porta 5432.

## 📚 Documentação da API (Swagger)
A API foi documentada utilizando o padrão OpenAPI. Com a aplicação em execução, acesse pelo navegador:

👉 http://localhost:8080/swagger-ui.html

Pela interface do Swagger é possível visualizar exemplos de requisições, respostas de sucesso e erro (Padrão ProblemDetail), além de testar os endpoints restritos inserindo o token JWT no botão "Authorize".

📍 Principais Endpoints
Autenticação (/v1/auth)
POST /v1/auth/login - Realiza a autenticação e devolve o Token JWT (Público).

Usuários (/v1/usuarios)
POST /v1/usuarios - Cria um novo usuário (Público).

GET /v1/usuarios?nome={nome} - Busca usuários por nome (Requer Token).

PUT /v1/usuarios/{id} - Atualiza dados do usuário (Requer Token).

PATCH /v1/usuarios/{id}/senha - Altera a senha do usuário (Requer Token).

DELETE /v1/usuarios/{id} - Exclui o usuário do banco (Requer Token).

🧪 Testes Unitários e Collection
Testes Automatizados: O projeto inclui testes unitários automatizados com JUnit e Mockito focados na camada de negócio (UsuarioUseCase). Para rodar os testes, execute mvn test.

Insomnia Collection: Uma coleção em formato JSON cobrindo os principais fluxos de sucesso e erro está disponível na raiz deste repositório para importação rápida.
