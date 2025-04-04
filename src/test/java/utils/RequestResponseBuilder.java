package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class RequestResponseBuilder {



    public RequestSpecification addplaceRequestSpec() throws IOException {

        PrintStream stream = new PrintStream(new FileOutputStream("APILogs.txt"));
        return new RequestSpecBuilder().setBaseUri(getGlobalValue("BASEURL")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
                .addFilter(RequestLoggingFilter.logRequestTo(stream))
                .addFilter(ResponseLoggingFilter.logResponseTo(stream))
                .build();

    }

    public String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream stream = new FileInputStream("/Users/esaimaninavamani/Documents/Automation/RestAPIWithCucumber/src/test/resources/apiConfig.properties");
        properties.load(stream);
        return properties.getProperty(key);

    }

}
