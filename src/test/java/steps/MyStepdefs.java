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
import utils.Constants;
import utils.RequestResponseBuilder;
import utils.TestDataBuilder;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class MyStepdefs {

    RequestSpecification spec;
    ResponseSpecification resSpec;
    Response response;
    RequestResponseBuilder requestResponseBuilder = new RequestResponseBuilder();
    RequestSpecification specification;

    static String placeID;

    @Given("Prepare Add Place Payload")
    public void prepareAddPlacePayload() throws IOException {
        specification = requestResponseBuilder.placeRequestSpec();
        spec = given().spec(specification);
        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("OK")).build();

    }

    @Given("Prepare Add Place Payload with {string} {string} {string}")
    public void prepareAddPlacePayloadWith(String name, String language, String address) throws IOException {
        specification = requestResponseBuilder.placeRequestSpec();

        spec = given().spec(specification).body(new TestDataBuilder().addPlacePayload(name, language, address));

        resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectBody("status", equalTo("OK")).build();

    }

    @Given("Prepare Delete Place Payload")
    public void prepareDeletePlacePayload() throws IOException {
        specification = requestResponseBuilder.placeRequestSpec();
        spec=given().spec(specification).body("{\n" +
                "    \"place_id\": \""+placeID+"\"\n" +
                "}");
    }



    @When("user calls the {string} with {string} call")
    public void userCallsTheWithCall(String resource, String method) {

        String resource1 = Constants.valueOf(resource).getResource();

        switch (method.toUpperCase()) {
            case "POST" -> response = spec
                    .when().post(resource1);
            case "GET" -> response = spec
                    .when().get(resource1);
            case "PUT" -> response = spec
                    .when().put(resource1);
            case "DELETE" -> response = spec
                    .when().delete(resource1);
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        }

    }

    @Then("API Call with success code {int}")
    public void APICallWithSuccessCode(int statusCode) {


        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("{string} in response body is {string}")
    public void inResponseBodyIs(String string, String string2) {
        JsonPath path = new JsonPath(response.asString());
        String status = path.get(string);
        Assert.assertEquals(string2, status);
        System.out.println(response.asString());
    }

    @Then("verify place_id created maps to {string} using {string}")
    public void verifyPlaceIdCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        specification = requestResponseBuilder.placeRequestSpec();
         placeID = requestResponseBuilder.getJSONValue(response, "place_id");
        spec = given().spec(specification).queryParam("place_id", placeID);
        userCallsTheWithCall(resource, "GET");
        String actualname = requestResponseBuilder.getJSONValue(response, "name");
        Assert.assertEquals(expectedName,actualname);

    }

}
