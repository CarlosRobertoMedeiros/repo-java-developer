package br.com.roberto.hub_manager_smoke_test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * Smoke tests for Hub Manager Payment Link API.
 * Tests basic CRUD operations and health checks.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Hub Manager Smoke Tests")
class HubManagerSmokeTestApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl;

	@BeforeEach
	void setUp() {
		baseUrl = "http://localhost:" + port + "/hub-manager-app";
		RestAssured.port = port;
		RestAssured.basePath = "/hub-manager-app";
	}

	@Test
	@DisplayName("Should verify application is running")
	void testApplicationRunning() {
		given()
				.when()
				.get("/api/payment-links")
				.then()
				.statusCode(200);
	}

	@Test
	@DisplayName("Should create a new payment link")
	void testCreatePaymentLink() {
		String requestBody = """
				{
					"description": "Test Payment Link",
					"amount": 100.50
				}
				""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/payment-links")
				.then()
				.statusCode(201)
				.header("Location", notNullValue())
				.body("id", notNullValue())
				.body("description", equalTo("Test Payment Link"))
				.body("amount", equalTo(100.5F))
				.body("status", equalTo("ACTIVE"))
				.body("paymentUrl", notNullValue());
	}

	@Test
	@DisplayName("Should reject invalid payment link request")
	void testCreateInvalidPaymentLink() {
		String requestBody = """
				{
					"description": "",
					"amount": -50.00
				}
				""";

		given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/payment-links")
				.then()
				.statusCode(400)
				.body("code", equalTo("VALIDATION_ERROR"));
	}

	@Test
	@DisplayName("Should get all payment links with pagination")
	void testGetAllPaymentLinks() {
		given()
				.queryParam("page", 0)
				.queryParam("size", 10)
				.when()
				.get("/api/payment-links")
				.then()
				.statusCode(200)
				.body("content", notNullValue());
	}

	@Test
	@DisplayName("Should return 404 for non-existent payment link")
	void testGetNonExistentPaymentLink() {
		given()
				.when()
				.get("/api/payment-links/00000000-0000-0000-0000-000000000000")
				.then()
				.statusCode(404);
	}

	@Test
	@DisplayName("Should access API documentation at Swagger UI")
	void testSwaggerUIAvailable() {
		given()
				.when()
				.get("/swagger-ui.html")
				.then()
				.statusCode(200);
	}

	@Test
	@DisplayName("Should access OpenAPI documentation")
	void testOpenAPIDocumentationAvailable() {
		given()
				.when()
				.get("/v3/api-docs")
				.then()
				.statusCode(200)
				.body("info.title", notNullValue())
				.body("paths", notNullValue());
	}

	@Test
	@DisplayName("Should verify health check endpoint")
	void testHealthCheckAvailable() {
		given()
				.when()
				.get("/manage/health")
				.then()
				.statusCode(200)
				.body("status", equalTo("UP"));
	}
}
