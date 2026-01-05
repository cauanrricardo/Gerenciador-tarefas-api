# Gerenciador de Tarefas API - SDET Study

Projeto focado em aprender a construir APIs com Spring Boot e automatizar testes com RestAssured. O objetivo foi aplicar o ciclo TDD (fazer o teste falhar e depois fazer passar).

---

##  Tecnologias

- **Java 21 & Spring Boot 3**
- **Spring Data JPA** (Postgres para uso real e H2 para testes)
- **RestAssured & JUnit 5** (Para automação de testes)
- **Bean Validation** (Para não aceitar títulos vazios ou curtos)

---

##  O que o projeto faz

✅ **Criação de Tarefas:** Valida se o título tem pelo menos 3 caracteres.  
✅ **Listagem:** Retorna todas as tarefas do banco.  
✅ **Atualização:** Edita tarefas existentes e valida os novos dados.  
✅ **Deleção:** Apaga tarefas e retorna erro 404 caso o ID não exista.  
✅ **Tratamento de Erros:** Respostas limpas em JSON quando algo dá errado.
