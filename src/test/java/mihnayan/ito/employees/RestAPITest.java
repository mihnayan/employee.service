package mihnayan.ito.employees;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ws.rs.core.Application;

import mihnayan.ito.employees.resources.OrganizationTypesResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class RestAPITest extends JerseyTest {
	
	private final static String OU_TYPE_URL = "/OUTypes";
	private final static String API_PATH = "api/v1";

	@Override
	protected Application configure() {
		port = 9998;
		return new ResourceConfig(OrganizationTypesResource.class);
	}

	@Test
	public void ouTypesGETTest() throws IOException {		
		
		final String ouTypesGetSchema = new String(Files.readAllBytes(Paths.get(API_PATH,
				"schemas/outypes-get.schema.json")));
		
		get(OU_TYPE_URL).then().assertThat().
			statusCode(200).
			contentType(ContentType.JSON).
			body(matchesJsonSchema(ouTypesGetSchema));
	}
	
	@Test
	public void ouTypeCRUDTest() throws IOException {
		
		final int expectedCreatesStatus = 201;
		
		final String createdResource = new String(Files.readAllBytes(Paths.get(API_PATH, 
				"examples/outype-post.example.json")));
		final String updatedResource = new String(Files.readAllBytes(Paths.get(API_PATH, 
				"examples/outype-put.example.json")));
		final String ouTypeSchema = new String(Files.readAllBytes(Paths.get(API_PATH, 
				"schemas/outype.schema.json")));
		final String resourceOperationSchema = new String(Files.readAllBytes(Paths.get(API_PATH,
				"schemas/resource-operation.schema.json")));
		
		Response response;
		
		/*
		 * Create resource
		 */
		response = 
				given().
					body(createdResource).
					contentType(ContentType.JSON).
				when().
					post(OU_TYPE_URL).
				then().assertThat().
					statusCode(expectedCreatesStatus).
					contentType(ContentType.JSON).
					body(matchesJsonSchema(resourceOperationSchema)).
				extract().
					response();
		
		String location = response.header("Location");
		assertNotNull(location);
		assertTrue(response.path("success"));
		assertEquals((Integer) response.path("status"), new Integer(expectedCreatesStatus));
		assertEquals(response.path("href"), location);
		
		/*
		 * Read created resource
		 */
		String newOUType = JsonPath.with(createdResource).getString("typeName");
		response = get(location).
				then().assertThat().
					statusCode(200).
					contentType(ContentType.JSON).
					body(matchesJsonSchema(ouTypeSchema)).
				extract().response();
		assertEquals(newOUType, response.path("typeName"));
		
		/*
		 * Update created resource
		 */
		response = 
				given().
					body(updatedResource).
					contentType(ContentType.JSON).
				when().
					put(location).
				then().assertThat().
					statusCode(200).
					contentType(ContentType.JSON).
					body(matchesJsonSchema(resourceOperationSchema)).
				extract().
					response();
		
		/*
		 * Read updated resource
		 */
		String updatedOUType = JsonPath.with(updatedResource).getString("typeName");
		response = get(location).
				then().assertThat().
					statusCode(200).
					contentType(ContentType.JSON).
					body(matchesJsonSchema(ouTypeSchema)).
				extract().response();
		assertEquals(updatedOUType, response.path("typeName"));
		
		/*
		 * Delete created resource
		 */
		response = delete(location).
				then().assertThat().
					statusCode(200).
					contentType(ContentType.JSON).
					body(matchesJsonSchema(resourceOperationSchema)).
				extract().response();
	}
	
}
