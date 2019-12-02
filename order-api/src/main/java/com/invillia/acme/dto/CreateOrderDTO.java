package com.invillia.acme.dto;

import com.invillia.acme.entity.OrderItem;
import com.invillia.acme.enums.OrderStatus;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CreateOrderDTO {

    @ApiModelProperty(required = true)
    @NotNull
    private OrderStatus orderStatus;

    @ApiModelProperty(required = true)
    @NotNull
    @NotEmpty
    @Size(max = 25)
    private String street;

    @ApiModelProperty(required = true)
    @NotNull
    @NotEmpty
    @Size(max = 8)
    private String zipCode;

    @ApiModelProperty(required = true)
    @NotNull
    @NotEmpty
    @Size(max = 25)
    private String city;

    @ApiModelProperty(required = true)
    @NotNull
    @NotEmpty
    @Size(max = 20)
    private String state;

    @ApiModelProperty(required = true)
    @NotNull
    private Long idStore;

    @NotEmpty
    private List<OrderItem> itens;

    public OrderStatus getOrderStatus() { return orderStatus; }

    public void setOrderStatus(OrderStatus orderStatus) { this.orderStatus = orderStatus; }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getIdStore() {
        return idStore;
    }

    public void setIdStore(Long idStore) {
        this.idStore = idStore;
    }

    public List<OrderItem> getItens() {
        return itens;
    }

    public void setItens(List<OrderItem> itens) {
        this.itens = itens;
    }
}
