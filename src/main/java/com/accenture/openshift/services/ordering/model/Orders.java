package com.accenture.openshift.services.ordering.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Orders {

    private Long id;
    @NotNull
    private Long organizationId;

    @NotBlank
    private String Description;
    @Min(1)
    @Max(100)
    private int Quantity;
    @NotBlank
    private String Items;

    public Orders() {

    }

    public Orders(Long organizationId,  String Description, int quantity, String items) {
        this.organizationId = organizationId;
        this.Description = Description;
        this.Quantity = quantity;
        this.Items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }



    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getItems() {
        return Items;
    }

    public void setItems(String Items) {
        this.Items = Items;
    }

    @Override
    public String toString() {
        return "ordering [id=" + id + ", organizationId=" + organizationId  + ", Description=" + Description + ", Items=" + Items + "]";
    }

}
