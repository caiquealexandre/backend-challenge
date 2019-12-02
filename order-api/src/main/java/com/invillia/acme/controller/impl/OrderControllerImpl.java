package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.OrderController;
import com.invillia.acme.dto.CreateOrderDTO;
import com.invillia.acme.dto.OrderDTO;
import com.invillia.acme.entity.Order;
import com.invillia.acme.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import java.util.List;
import java.util.Optional;

@Api(value = "ACME - Order Rest API")
@RestController
@RequestMapping(value = "/api/order")
public class OrderControllerImpl implements OrderController {

    @Autowired
    private OrderService service;

    @Override
    @ApiOperation(value = "Criar Order")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@RequestBody @Valid CreateOrderDTO dto, HttpServletResponse response) {

        Order order = new Order();
        this.copyProperties(dto, order);
        order.setConfirmationDate(LocalDateTime.now());
        service.save(order);

        return ResponseEntity.status(HttpStatus.CREATED).body(order.toOrderDTO());
    }

    @Override
    @ApiOperation(value = "Buscar Order por ID")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOrderById(Long id) {
        return service.findById(id).map(order -> ResponseEntity.ok(order)).orElse(ResponseEntity.notFound().build());
    }

    @Override
    @ApiOperation(value = "Atualizar Order")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> update(@Valid CreateOrderDTO dto, Long id, HttpServletResponse response) {
        Optional<Order> optional = service.findById(id);

        if(!optional.isPresent()) {
            throw new NoResultException("O pedido informado não foi encontrado.");
        }

        Order order = optional.get();
        this.copyProperties(dto, order);
        service.save(order);

        return ResponseEntity.status(HttpStatus.OK).body((order.toOrderDTO()));
    }

    @Override
    @ApiOperation(value = "Buscar Order por parâmetros")
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getOrderByParameters(OrderDTO orderDTO) {
        List<Order> listResult = service.findPaginated(orderDTO);
        return !listResult.isEmpty() ? new ResponseEntity<>(listResult, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    private void copyProperties(CreateOrderDTO dto, Order order) {
        BeanUtils.copyProperties(dto, order);
    }


}
