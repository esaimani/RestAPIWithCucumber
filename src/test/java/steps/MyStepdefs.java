package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import junit.framework.Assert;
import utils.RequestResponseBuilder;
import utils.TestDataBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {

    RequestSpecification spec;
    ResponseSpecification resSpec;
    Response response;

    RequestResponseBuilder requestResponseBuilder = new RequestResponseBuilder();

    @Given("Prepare Add Place Payload")
    public void prepareAddPlacePayload() throws IOException {
        RequestSpecification specification = requestResponseBuilder.addplaceRequestSpec();

        spec = given().spec(specification);

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("OK")).build();

    }
    @Given("Prepare Add Place Payload with {string} {string} {string}")
    public void prepareAddPlacePayloadWith(String name, String language, String address) throws IOException {
        RequestSpecification specification = requestResponseBuilder.addplaceRequestSpec();

        spec = given().spec(specification).body(new TestDataBuilder().addPlacePayload(name,language,address));

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("OK")).build();

    }


    @When("user calls the {string} with post call")
    public void userCallsTheWithPostCall(String resource) {
        response = spec
                .when().post(resource)
                .then().spec(resSpec)
                .extract().response();
    }

    @Then("Place added with success code {int}")
    public void placeAddedWithSuccessCode(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String string, String string2) {
        JsonPath path = new JsonPath(response.asString());
        String status = path.get(string);
        Assert.assertEquals(string2, status);
    }

}
