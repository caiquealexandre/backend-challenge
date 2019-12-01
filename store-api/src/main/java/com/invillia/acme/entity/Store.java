package com.invillia.acme.entity;

import com.invillia.acme.dto.StoreDTO;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_store")
@SequenceGenerator(name = "generator_store_seq", sequenceName = "store_seq", allocationSize = 1, initialValue = 1)
public class Store implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_store_seq")
    @Column(name = "id_store")
    private Long id;

    @Column
    private String name;

    @Column
    private String street;

    @Column(name = "zip_code")
    private String zipCode;

    @Column
    private String city;

    @Column
    private String state;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public StoreDTO toStoreDTO () {
        StoreDTO storeDTO = new StoreDTO();
        BeanUtils.copyProperties(this, storeDTO);
        return storeDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}