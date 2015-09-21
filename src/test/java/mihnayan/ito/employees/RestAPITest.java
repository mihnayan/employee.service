package mihnayan.ito.employees;

import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.module.jsv.JsonSchemaValidator.*;

import javax.ws.rs.core.Application;

import mihnayan.ito.employees.resources.OrganizationTypesResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import com.jayway.restassured.http.ContentType;

public class RestAPITest extends JerseyTest {

	@Override
	protected Application configure() {
		port = 9998;
		return new ResourceConfig(OrganizationTypesResource.class);
	}

	@Test
	public void getOUTypesTest() {		
		get("/OUTypes").then().assertThat()
			.statusCode(200)
			.contentType(ContentType.JSON)
			.body(matchesJsonSchemaInClasspath("schemas/outypes-get.schema.json"));
	}
}
