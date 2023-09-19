package ua.qa.auto.tests;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ua.qa.auto.model.Customer;
import ua.qa.auto.util.DataGenerator;

public class CreateCustomerTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8090";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest(name = "Create customer = {0}")
    @CsvSource(value = {
            "Петр, Петров",
            "Василий, Васильев"})
    public void sendRequestWithAllMandatoryParams(String firstName, String lastName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        String phoneNumber = DataGenerator.generatePhoneNumber();
        customer.setPhoneNumber(phoneNumber);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/customers")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("phoneNumber", Matchers.equalTo(phoneNumber))
                .body("email", Matchers.equalTo(null))
                .body("dateOfBirth", Matchers.equalTo(null))
                .body("loyalty.bonusCardNumber", Matchers.notNullValue())
                .body("loyalty.active", Matchers.equalTo(true))
                .body("loyalty.discountRate", Matchers.notNullValue())
                .body("updatedAt", Matchers.notNullValue())
                .body("createdAt", Matchers.notNullValue());
    }

    @Test
    @DisplayName("POST /customers send request with absent mandatory parameter firstName -> Expected result: HTTP status 400")
    public void sendRequestWithoutMandatoryParameterFirstName() {
        Customer customer = new Customer();
        customer.setLastName("Тестов");
        String phoneNumber = DataGenerator.generatePhoneNumber();
        customer.setPhoneNumber(phoneNumber);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/customers")
                .then()
                .statusCode(400)
                .body("errors[0]", Matchers.equalTo("Mandatory field missing: firstName"));
    }

    @Test
    @DisplayName("POST /customers send request with invalid phoneNumber -> Expected result: HTTP status 400")
    public void sendRequestWithPhoneNumberInInvalidPattern() {
        Customer customer = new Customer();
        customer.setFirstName("Тест");
        customer.setLastName("Тестов");
        customer.setPhoneNumber("+4078369854");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/customers")
                .then()
                .statusCode(400)
                .body("errors[0]", Matchers.equalTo("Invalid phoneNumber: expected format +7XXXXXXXXXX"));
    }
}
