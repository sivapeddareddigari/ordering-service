package com.accenture.openshift.services.ordering.repository;

import java.util.*;
import java.util.stream.Collectors;

import com.accenture.openshift.services.ordering.model.Orders;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderingRepository {

    private Set<Orders> orders = new HashSet<>();

    public OrderingRepository() {
        add(new Orders(10L, "Spare Parts", 30, "Carburetor"));
        add(new Orders(20L, "Tires", 40, "Winter Tires"));
    }

    public Orders add(Orders orders) {
        orders.setId((long) (this.orders.size() + 1));
        this.orders.add(orders);
        return orders;
    }

    public Orders findById(Long id) {
        Optional<Orders> ordering = orders.stream().filter(a -> a.getId().equals(id)).findFirst();
        if (ordering.isPresent())
            return ordering.get();
        else
            return null;
    }

    public Set<Orders> findAll() {
        return orders;
    }



    public Set<Orders> findByOrganization(Long organizationId) {
        return orders.stream().filter(a -> a.getOrganizationId().equals(organizationId)).collect(Collectors.toSet());
    }

}
