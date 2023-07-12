package project;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;


public class gitHubProject {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;

    String sshKey = "ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIPTZ2hsxeoUeNco/FlsThtvzIk5VdYXf8FrGHVGoNl5o";
    int sshkeyid = 12;

    @BeforeClass
    public void setUp() {
        // Create request specification
        requestSpec = new RequestSpecBuilder()
                .addHeader("Content-Type","application/json")
                .addHeader("Authorization","token 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")
                .setBaseUri("https://api.github.com")
                .build();
        // Create response specification
        responseSpec = new ResponseSpecBuilder()
                // Check status code in response
                .expectResponseTime(lessThan(5000L))
                .expectBody("key",equalTo(sshKey))
                .expectBody("title",equalTo("TestAPIKey"))
                .build();
    }

    @Test(priority = 1)
    public void POSTRequest() {
        String reqBody = "{\"title\": \"TestAPIKey\",\"key\": \"ssh-ed25519 AAAAC3NzaC1lZDI1NTE5AAAAIPTZ2hsxeoUeNco/FlsThtvzIk5VdYXf8FrGHVGoNl5o\"}";
        Response res = given().spec(requestSpec).body(reqBody).when().post("/user/keys");
        System.out.println("POST Response \n" + res.getBody().asPrettyString());
        Reporter.log(res.getBody().asPrettyString());
        sshkeyid =res.then().extract().body().path("id");
        res.then().spec(responseSpec);
        Assert.assertEquals(res.getStatusCode(),201);
    }
    @Test(priority = 2)
    public void GETRequest() {
        Response res = given().spec(requestSpec).pathParam("keyId",sshkeyid).when().get(" /user/keys/{keyId}");
        System.out.println("GET Response \n" + res.getBody().asPrettyString());
        Reporter.log(res.getBody().asPrettyString());
        res.then().spec(responseSpec);
        Assert.assertEquals(res.getStatusCode(),200);
    }
    @Test(priority = 3)
    public void DELETERequest() {
        Response res = given().spec(requestSpec).pathParam("keyId",sshkeyid).when().delete(" /user/keys/{keyId}");
        System.out.println("DELETE Response \n" + res.getBody().asPrettyString());
        Reporter.log(res.getBody().asPrettyString());
        Assert.assertEquals(res.getStatusCode(),204);
    }



}
