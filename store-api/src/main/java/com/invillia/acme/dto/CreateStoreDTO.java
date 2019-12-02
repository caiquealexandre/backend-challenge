package com.invillia.acme.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CreateStoreDTO {

    @ApiModelProperty(required = true)
    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String name;

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
    @Size(max = 25)
    private String state;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
