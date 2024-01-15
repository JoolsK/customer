package ua.qa.auto.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.hamcrest.Matchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ua.qa.auto.BaseTest;
import ua.qa.auto.model.Customer;
import ua.qa.auto.model.Loyalty;
import ua.qa.auto.util.DataGenerator;
import ua.qa.auto.matcher.DateMatchers;
import ua.qa.auto.util.DataLoader;

import java.io.IOException;
import java.time.LocalDateTime;

public class CreateCustomerTest extends BaseTest {

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest(name = "Create customer = {0}")
    @CsvSource(value = {
            "Петр, Петров",
            "Василий, Васильев"})
    public void sendRequestWithAllMandatoryParams(String firstName, String lastName) throws IOException {

        String code = "919";
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        String phoneNumber = DataGenerator.generatePhoneNumber(code);
        customer.setPhoneNumber(phoneNumber);
        Loyalty loyalty = new Loyalty();
        loyalty.setDiscountRate(1);
        String body = DataLoader.readFromResources("request/create-customer.json");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body)
                .post("/customers")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("phoneNumber", Matchers.equalTo(phoneNumber))
                .body("email", Matchers.nullValue())
                .body("dateOfBirth", Matchers.equalTo(null))
                .body("loyalty.bonusCardNumber", Matchers.notNullValue())
                .body("loyalty.active", Matchers.equalTo(true))
                .body("loyalty.discountRate", Matchers.notNullValue())
                .body("updatedAt", DateMatchers.isToday())
                .body("createdAt", DateMatchers.isToday());
    }

    @Test
    @DisplayName("POST /customers send request with absent mandatory parameter firstName -> Expected result: HTTP status 400")
    public void sendRequestWithoutMandatoryParameterFirstName() {

        String code = "919";
        Customer customer = new Customer();
        customer.setLastName("Тестов");
        customer.setEmail("john.doe@gmail.com");
        customer.setDateOfBirth("1990-01-12");
        String phoneNumber = DataGenerator.generatePhoneNumber(code);
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

    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest(name = "Create customer = {0}")
    @CsvSource(value = {
            "Петр, Петров",
            "Василий, Васильев"})
    public void requestWithMandatoryFields(String firstName, String lastName) {

        String code = "919";
        Customer customer = new Customer(firstName, lastName, DataGenerator.generatePhoneNumber(code));

        LocalDateTime referenceDateTime = LocalDateTime.now();

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/customers")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("firstName", Matchers.equalTo(firstName))
                .body("lastName", Matchers.equalTo(lastName))
                .body("phoneNumber", Matchers.equalTo(customer.getPhoneNumber()))
                .body("email", Matchers.nullValue())
                .body("dateOfBirth", Matchers.equalTo(null))
                .body("loyalty.bonusCardNumber", Matchers.notNullValue())
                .body("loyalty.active", Matchers.equalTo(true))
                .body("loyalty.discountRate", Matchers.notNullValue())
                .body("updatedAt", DateMatchers.isToday())
                .body("createdAt", DateMatchers.isAfter(referenceDateTime));
    }


    @Execution(ExecutionMode.CONCURRENT)
    @ParameterizedTest(name = "Create customer = {0}")
    @CsvSource(value = {
            "Петр, Петров, petr@petrov.com, 1990-01-12",
            "Василий, Васильев, vasiliy@vasilev.com, 1990-01-12"})
    public void requestWithoutArgs(String firstName, String lastName, String email, String dateOfBirth) {

        String code = "919";
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setDateOfBirth(dateOfBirth);

        String phoneNumber = DataGenerator.generatePhoneNumber(code);
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
                .body("email", Matchers.equalTo(email))
                .body("dateOfBirth", Matchers.equalTo(dateOfBirth))
                .body("loyalty.bonusCardNumber", Matchers.notNullValue())
                .body("loyalty.active", Matchers.equalTo(true))
                .body("loyalty.discountRate", Matchers.notNullValue())
                .body("updatedAt", DateMatchers.isToday())
                .body("createdAt", DateMatchers.isToday());
    }
}
