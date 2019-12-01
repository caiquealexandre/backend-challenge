package com.invillia.acme.repository;

import com.invillia.acme.entity.Order;
import com.invillia.acme.enums.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("SELECT o FROM Order o WHERE 1=1 "
            + " AND ( o.orderStatus = :orderStatus OR :orderStatus IS NULL ) "
            + " AND ( o.street = :street OR :street IS NULL ) "
            + " AND ( o.zipCode = :zipCode OR :zipCode IS NULL ) "
            + " AND ( o.city = :city OR :city IS NULL ) "
            + " AND ( o.state = :state OR :state IS NULL ) "
            + " AND ( o.idStore = :idStore OR :idStore IS NULL ) ")
    Page<Order> findByParameters(@Param("orderStatus") OrderStatus orderStatus,
                                 @Param("street") String street, @Param("zipCode") String zipCode, @Param("city") String city,
                                 @Param("state") String state, @Param("idStore") Long idStore, Pageable page);

}
