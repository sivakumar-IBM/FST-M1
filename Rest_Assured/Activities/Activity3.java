package activities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Activity3 {
    // Declare request specification
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("https://petstore.swagger.io/v2/pet")
                .build();
        responseSpec = new ResponseSpecBuilder()
                // Check status code in response
                .expectStatusCode(200)
                // Check response content type
                .expectContentType("application/json")
                // Check if response contains name property
                .expectBody("status", equalTo("alive"))
                // Build response specification
                .build();
    }

    @Test(priority = 1)
    public void POSTRequest() {
        String reqBodyPet = "{\"id\": 77232,\"name\": \"Riley\",\"status\": \"alive\"}";

        Response res = given().spec(requestSpec).body(reqBodyPet).when().post();
        System.out.println(res.getBody().asPrettyString());

        reqBodyPet = "{\"id\": 77233,\"name\": \"Hansel\",\"status\": \"alive\"}";

        res = given().spec(requestSpec).body(reqBodyPet).when().post();
        System.out.println(res.getBody().asPrettyString());
        //validate
        res.then().spec(responseSpec);
    }

    @Test(dataProvider = "petData", priority = 2)
    public void GETRequest(int id,String name,String status) {
        Response response =
                given().spec(requestSpec) // Use requestSpec
                        .pathParam("petId", id) // Set path parameter
                        .get("/{petId}"); // Send GET request

        // Print response
        String body = response.getBody().asPrettyString();
        System.out.println(body);

        // Assertion
        response.then().spec(responseSpec).body("name", equalTo(name));
    }

    @Test(dataProvider = "petData", priority = 3)
    public void DELETERequest(int id,String name,String status) {
        Response response = given().spec(requestSpec)
                .contentType(ContentType.JSON) // Set content type
                .pathParam("petId", id) // Set path parameter
                .delete("/{petId}"); // Send GET request

        // Print response
        System.out.println(response.getBody().asPrettyString());

        // Assertion with response specification
        response.then().body("code",equalTo(200));
    }

    @DataProvider(name = "petData")
    public Object[][] petIdProvider() {
        Object[][] testData = new Object[][]{{77232, "Riley", "alive"},
                {77233, "Hansel", "alive"}};
        return testData;
    }
}
