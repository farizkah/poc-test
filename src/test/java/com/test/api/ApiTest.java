package com.test.api;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class ApiTest {

	@Test
	public void checkSchema() {
		File schemaFile = new File("resources/schema.json");
		
		when().
			get("https://jsonplaceholder.cypress.io/posts").
		then().
			statusCode(200).
			body(matchesJsonSchema(schemaFile));
	}
	
	@Test
	public void checkPostResponse() {
		JsonPath requestData = new JsonPath(new File("resources/postRequestData.json"));
		
		given().
			contentType("application/json").
			body(requestData.getJsonObject("")).
		when().
			post("https://jsonplaceholder.cypress.io/posts").
		then().
			statusCode(201).
			body("userId", equalTo(requestData.get("userId"))).
			body("title", equalTo(requestData.get("title"))).
			body("body", equalTo(requestData.get("body")));
	}
	
}
