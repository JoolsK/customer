package ua.qa.auto;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import ua.qa.auto.config.Configuration;

public class BaseTest {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = Configuration.getProperty("api.base.url");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
