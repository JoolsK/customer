package ua.qa.auto;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.qa.auto.config.ConfigurationException;
import ua.qa.auto.model.Customer;
import ua.qa.auto.util.DataLoader;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerServiceTest extends BaseTest {

    @Test
    public void generic_test() {
        List<Customer> cusList = new ArrayList<>();
        cusList.add(new Customer("Sidor", "Sidorov", "79801234548"));
        cusList.add(new Customer("Ivan", "Ivanov", "79801234578"));
        cusList.add(new Customer("Petr", "Petrov", "79801234568"));
        cusList.add(new Customer("Andrey", "Andreev", "79801234518"));

        Collections.sort(cusList);

        for (Customer customer : cusList) {
            System.out.println(customer.getLastName());
        }
    }

    @Test
    @DisplayName("GET /customers/filter -> queries for all phoneNumbers")
    public void getCustomerByPhoneNumber_test1() {

        List<String> phoneNumbers = DataLoader.readFromFile("./src/test/resources/file/phoneNumbers.csv");
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
                throw new ConfigurationException("Ошибка при запросе номера телефона: " + phoneNumber);
            }
        }
        System.out.println("Customers names: " + lastNames);
    }

    @Test
    public void testCreateCustomer() {
        String requestBody = DataLoader.readFromResources("./requests/create-customer.json");
        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .get("/customers/create");
    }

    @Test
    @DisplayName("GET /customers/filter -> queries for all phoneNumbers")
    public void getCustomerByPhoneNumber_test3213213() {
        Path path = Path.of("./src/test/resources/file/phoneNumbers.csv");
        try {
            String data = Files.readString(path);
            System.out.println(data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("GET /customers/filter -> queries for all phoneNumbers")
    public void getCustomerByPhoneNumber_test4444444() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("file/phoneNumbers.csv")) {
            if (input == null) {
                // throw new ConfigurationException("Can't find " + configurationFilePath);
            }
            String data = IOUtils.toString(input, Charset.defaultCharset());
            System.out.println(data);
        } catch (IOException ex) {
            throw new ConfigurationException("Error loading configuration", ex);
        }
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
    public void test_test() {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        System.out.println(a.equals(b));
    }
}





