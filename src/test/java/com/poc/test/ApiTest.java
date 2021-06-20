package com.poc.test;

import org.junit.Test;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class ApiTest {

	@Test
	public void checkSchema() {
		when().
			get("https://jsonplaceholder.cypress.io/posts").
		then().
			statusCode(200).
			body(matchesJsonSchemaInClasspath("com/poc/test/schema.json"));
	}
	
	@Test
	public void checkPostResponse() {
		JsonPath requestData = new JsonPath(new File("src/test/java/com/poc/test/postRequestData.json"));
		
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
