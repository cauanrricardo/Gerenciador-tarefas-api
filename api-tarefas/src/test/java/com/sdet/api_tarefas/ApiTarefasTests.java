package com.sdet.api_tarefas;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApiTarefasTests {
	@LocalServerPort
	private int port;

	@BeforeEach
	public void setIp(){
		RestAssured.port = port;
	}

	@Test
	void deveListarTarefaComSucesso() {
		given().contentType(ContentType.JSON)
				.when().get("/tarefas")
				.then().statusCode(200); //verifica se a API respondeu OK
	}

	@Test
	void deveCriarTarefaComSucesso(){
		String tarefaJson = "{ \"titulo\": \"Testar com RestAssured\", \"conclusao\":false }";
		given().contentType(ContentType.JSON)
				.body(tarefaJson)
				.when().post("/tarefas")
				.then().statusCode(200)
				.body("titulo", equalTo
						("Testar com RestAssured"))
				.body("id", notNullValue());
	}

}
