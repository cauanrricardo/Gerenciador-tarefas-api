package com.sdet.api_tarefas;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTarefasTests {

	@LocalServerPort
	private int port;

	@BeforeEach
	public void setIp(){
		RestAssured.port = port;
	}

	/*
	* given() - configuração
	* when() - ação
	* then() - a validaçãp*/
	@Test
	void deveListarTarefaComSucesso() {
		given().contentType(ContentType.JSON) //o conteudo é JSON
				.when().get("/tarefas")
				.then().statusCode(200); //verifica se a API respondeu OK
	}

	@Test
	void deveCriarTarefaComSucesso(){
		String tarefaJson = "{ \"titulo\": \"Testar com RestAssured\", \"concluida\":false }";
		given().contentType(ContentType.JSON).body(tarefaJson)
				.when().post("/tarefas")
				.then().statusCode(200)
				.body("titulo", equalTo("Testar com RestAssured"))
				.body("id", notNullValue());
	}

	@Test
	void deveDeletarComSucesso(){
		String tarefaJson = "{\"titulo\": \"Tarefa para deletar\", \"concluida\": false}";

		int idDeletar = given().contentType(ContentType.JSON)
				.body(tarefaJson)
				.when().post("/tarefas")
				.then()
				.statusCode(200) //criou a tarefa
				.extract()
				.path("id"); //extrai o id e gaura na variavél

	given().pathParam("id", idDeletar) // esta função substitui o que está entre chaves na URL
			.when().delete("/tarefas/{id}") // o {id} vai ser substituido pelo valor da variavel
			.then().statusCode(200);

	}

	@Test
	void deveAtualizarComSUcesso(){
		String tarefaJson = "{\"titulo\": \"Tarefa para atualizar\", \"concluida\": false}";

		int idAtualizar = given().contentType(ContentType.JSON)
					.body(tarefaJson)
				.when()
					.post("/tarefas")
				.then()
					.statusCode(200)
					.extract()
					.path("id");
		String novaTarefaJson =  "{\"titulo\": \"Tarefa atualizada\", \"concluida\": true}";
		given().contentType(ContentType.JSON)
					.pathParam("id", idAtualizar)
					.body(novaTarefaJson)
				.when()
					.put("/tarefas/{id}")
				.then()
					.statusCode(200)
					.body("titulo", equalTo("Tarefa atualizada"))
					.body("concluida", equalTo(true));

	}

	@Test
	void deveFalharAoTentarCriarUmaTarefaSemTitulo(){
		String tarefaJson = "{\"titulo\": \"\", \"concluida\": false}";
		given()
					.contentType(ContentType.JSON)
					.body(tarefaJson)
				.when()
					.post("/tarefas")
				.then()
					.statusCode(400);
	}

	@Test
	void deveFalharAoTentarCriarTarefaComTituloMuitoCurto() {
		String tarefaInvalida = "{\"titulo\": \"Oii\", \"conclusao\": false}";

		given().contentType(ContentType.JSON)
				.body(tarefaInvalida)
				.when().post("/tarefas")
				.then().statusCode(400);
	}

	@Test
	void deveFalharAoTentarDarUpdateComTituloApagado(){
		String tarefaJson = "{\"titulo\": \"Tarefa para atualizar\", \"concluida\": false}";

		int idAtualizar = given().contentType(ContentType.JSON)
				.body(tarefaJson)
				.when()
				.post("/tarefas")
				.then()
				.statusCode(200)
				.extract()
				.path("id");
		String novaTarefaJson =  "{\"titulo\": \"\", \"concluida\": false}";
		given()
					.contentType(ContentType.JSON)
					.body(novaTarefaJson)
				.when()
					.put("/tarefas/" + idAtualizar)
				.then()
					.statusCode(400);

	}
}
