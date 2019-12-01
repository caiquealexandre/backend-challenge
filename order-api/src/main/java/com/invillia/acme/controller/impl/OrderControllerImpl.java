package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.OrderController;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.entity.Order;
import com.invillia.acme.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(value = "/api/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService service;

    @Override
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid OrderDTO orderDTO, HttpServletResponse response) {

        Order order = new Order();
        this.copyProperties(orderDTO, order);
        order.setConfirmationDate(LocalDateTime.now());
        service.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order.toOrderDTO());
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOrderById(Long id) {
        return service.findById(id).map(order -> ResponseEntity.ok(order)).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOrderByParameters(OrderDTO orderDTO, Pageable pageable) {
        Page<Order> pageResult = service.findPaginated(orderDTO, pageable);

        if(pageResult == null || pageResult.isEmpty()) {
            throw new NoResultException("There is no Order with the informed data.");
        }
        return new ResponseEntity<>(pageResult, HttpStatus.OK);
    }

    private void copyProperties(OrderDTO dto, Order order) {
        BeanUtils.copyProperties(dto, order);
    }


}
