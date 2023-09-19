package ua.qa.auto;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceTest {

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "http://localhost:8090";
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    @DisplayName("GET /customers/filter -> queries for all phoneNumbers")
    public void getCustomerByPhoneNumber_test1() {
        String[] phoneNumbers = {
                "+79991736222",
                "+79181938009",
                "+79182520099",
                "+79288771625"
        };
        List<String> lastNames = new ArrayList<>();
        for (String phoneNumber : phoneNumbers) {
            Response response = RestAssured.given()
                    .queryParam("phoneNumber", phoneNumber)
                    .get("/customers/filter");
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String lastName = response.path("lastName");
                lastNames.add(lastName);
            } else {
                System.out.println("Error for " + phoneNumber);
            }
        }
        System.out.println("Customers names: " + lastNames);
    }

    @Test
    @DisplayName("GET /customers/{id} -> Request for every second subscriber. The count starts from the first")
    public void testGetCustomersById_test2() {
        List<String> lastNames = new ArrayList<>();
        for (int i = 1; i < 10; i += 2) {
            Response response = RestAssured.given()
                    .pathParam("id", i)
                    .get("/customers/{id}");
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String lastName = response.path("lastName");
                lastNames.add(lastName);
            } else
                break;
        }
        System.out.println("Customers names: " + lastNames);
    }

    @Test
    @DisplayName("GET /customers/filter -> Request for every second subscriber. The count starts from the first")
    public void testGetCustomersByFilter_test2() {
        String[] phoneNumbers = {
                "+79991736222",
                "+79181938009",
                "+79182520099",
                "+79288771625"
        };
        List<String> lastNames = new ArrayList<>();
        for (int i = 1; i < phoneNumbers.length; i += 2) {
            String phoneNumber = phoneNumbers[i];
            Response response = RestAssured.given()
                    .queryParam("phoneNumber", phoneNumber)
                    .get("/customers/filter");
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String lastName = response.path("lastName");
                lastNames.add(lastName);
            } else
                break;
        }
        System.out.println("Customers names: " + lastNames);
    }

    @Test
    @DisplayName("GET /customers/{id} -> Send GET requests by id from 1 to n until status code 404 is received")
    public void testGetCustomersById_test3() {
        List<String> lastNames = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            Response response = RestAssured.given()
                    .pathParam("id", i)
                    .get("/customers/{id}");
            int statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String lastName = response.path("lastName");
                lastNames.add(lastName);
            } else if (statusCode == 404) {
                break;
            }
        }
        System.out.println("Customers names: " + lastNames);
    }

    @Test
    public void testGetCustomersById_test3_v2() {
        int id = 1;
        int statusCode;
        List<String> lastNames = new ArrayList<>();
        do {
            Response response = RestAssured.given()
                    .pathParam("id", id)
                    .get("/customers/{id}");

            statusCode = response.getStatusCode();
            if (statusCode == 200) {
                String lastName = response.path("lastName");
                lastNames.add(lastName);
            }
            id++;
        } while (statusCode != 404);

        System.out.println("Customers names: " + lastNames);
    }

    @Test
    public void test_test(){
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        System.out.println(a.equals(b));
    }
}





