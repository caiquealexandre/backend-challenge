package com.invillia.acme.controller;

import com.invillia.acme.dto.OrderDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface OrderController {

    public ResponseEntity<?> create(@RequestBody @Valid OrderDTO orderDTO, HttpServletResponse response);

    public ResponseEntity<?> getOrderById(@PathVariable Long id);

    public ResponseEntity<?> getOrderByParameters(OrderDTO orderDTO, Pageable pageable);

}
