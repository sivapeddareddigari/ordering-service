package com.accenture.openshift.services.ordering.controller;

import com.accenture.openshift.services.ordering.model.Orders;
import com.accenture.openshift.services.ordering.repository.OrderingRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.equalTo;

@QuarkusTest
public class OrdersControllerTests {

    @Inject
    OrderingRepository repository;

    @Test
    public void testFindAll() {
        given().when().get("/orderings").then().statusCode(200).body(notNullValue());
    }

    @Test
    public void testFindById() {
        Orders orders = new Orders(10L, "Spare parts", 33, "Carburetor");
        orders = repository.add(orders);
        given().when().get("/orders/{id}", orders.getId()).then().statusCode(200)
                .body("id", equalTo(orders.getId().intValue()))
                .body("Description", equalTo(orders.getDescription()))
                .body("Items", equalTo(orders.getItems()));
    }



    @Test
    public void testAdd() {
        Orders orders = new Orders(10L, "Winter Tires", 40, "Tires");
        given().contentType("application/json").body(orders)
                .when().post("/orderings").then().statusCode(200)
                    .body("id", notNullValue())
                    .body("Description", equalTo(orders.getDescription()))
                    .body("Items", equalTo(orders.getItems()));
    }

    @Test
    public void testInvalidAdd() {
        Orders orders = new Orders();
        given().contentType("application/json").body(orders).when().post("/Orders").then().statusCode(400);
    }

}
