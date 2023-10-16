package ua.qa.auto;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8090";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
