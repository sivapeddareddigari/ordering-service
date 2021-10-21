package com.accenture.openshift.services.ordering.controller;

import com.accenture.openshift.services.ordering.model.Orders;
import com.accenture.openshift.services.ordering.repository.OrderingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
public class OrderingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderingController.class);

    @Inject
    OrderingRepository repository;

    @POST
    public Orders add(@Valid Orders orders) {
        LOGGER.info("orders add: {}", orders);
        return repository.add(orders);
    }

    @Path("/{id}")
    @GET
    public Orders findById(@PathParam("id") Long id) {
        LOGGER.info("orders find: id={}", id);
        return repository.findById(id);
    }

    @GET
    public Set<Orders> findAll() {
        LOGGER.info("orders find");
        return repository.findAll();
    }


}
