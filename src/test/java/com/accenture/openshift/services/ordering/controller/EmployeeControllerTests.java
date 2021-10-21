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
public class orderingControllerTests {

    @Inject
    OrderingRepository repository;

    @Test
    public void testFindAll() {
        given().when().get("/orderings").then().statusCode(200).body(notNullValue());
    }

    @Test
    public void testFindById() {
        Orders orders = new Orders(1L, 1L, "Adrien Hamilton", 33, "Developer");
        orders = repository.add(orders);
        given().when().get("/orderings/{id}", orders.getId()).then().statusCode(200)
                .body("id", equalTo(orders.getId().intValue()))
                .body("name", equalTo(orders.getName()))
                .body("Items", equalTo(orders.getItems()));
    }

    @Test
    public void testFindByDepartment() {
        given().when().get("/orderings/department/{departmentId}", 1L).then().statusCode(200).body(notNullValue());
    }

    @Test
    public void testAdd() {
        Orders orders = new Orders(1L, 1L, "Josh Stevens", 23, "Developer");
        given().contentType("application/json").body(orders)
                .when().post("/orderings").then().statusCode(200)
                    .body("id", notNullValue())
                    .body("name", equalTo(orders.getName()))
                    .body("Items", equalTo(orders.getItems()));
    }

    @Test
    public void testInvalidAdd() {
        Orders orders = new Orders();
        given().contentType("application/json").body(orders).when().post("/Orders").then().statusCode(400);
    }

}
