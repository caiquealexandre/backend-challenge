package com.invillia.acme.controller;

import com.invillia.acme.dto.CreatePaymentDTO;
import com.invillia.acme.dto.PaymentDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

public interface PaymentController {

    public ResponseEntity<?> create(@RequestBody @Valid CreatePaymentDTO dto, HttpServletResponse response, @RequestHeader HttpHeaders httpHeaders);

    public ResponseEntity<?> update(@RequestBody @Valid CreatePaymentDTO dto, @PathVariable Long id, HttpServletResponse response);

    public ResponseEntity<?> getCheckPaymentExpired(@PathVariable Long idOrder);

    public ResponseEntity<?> getPaymentById(@PathVariable Long id);

}
