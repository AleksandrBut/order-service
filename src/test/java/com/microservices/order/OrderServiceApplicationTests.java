package com.microservices.order;

import com.microservices.order.stub.InventoryClientStub;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Import;

import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@AutoConfigureWireMock(port = 0)
@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class OrderServiceApplicationTests {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreateOrder() {
        String requestBody = """
                {
                    "skuCode": "iph_1",
                    "price" : 123.456,
                    "quantity": 25
                }
                """;

        InventoryClientStub.stubIsInStock("iph_1", 25);

        RestAssured.given()
                .contentType(JSON)
                .body(requestBody)
                .when()
                .post("api/order")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("orderNumber", notNullValue())
                .body("skuCode", equalTo("iph_1"))
                .body("price", equalTo(123.456F))
                .body("quantity", equalTo(25));
    }
}
