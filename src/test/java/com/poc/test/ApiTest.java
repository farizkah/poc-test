package com.poc.test;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class ApiTest {

	@Test
	public void checkSchema() {
		when().
			get("https://jsonplaceholder.cypress.io/posts").
		then().
			statusCode(200).
			body(matchesJsonSchemaInClasspath("com/poc/test/schema.json"));
	}
}
