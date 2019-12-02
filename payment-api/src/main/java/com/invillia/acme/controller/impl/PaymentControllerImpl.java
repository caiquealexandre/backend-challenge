package com.invillia.acme.controller.impl;

import com.invillia.acme.controller.PaymentController;
import com.invillia.acme.dto.CreatePaymentDTO;
import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.enums.PaymentStatus;
import com.invillia.acme.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Api(value = "ACME - Payment Rest API")
@RestController
@RequestMapping(value = "/api/payment")
public class PaymentControllerImpl implements PaymentController {

    @Autowired
    private PaymentService service;

    @Override
    @ApiOperation(value = "Cadastrar pagamento")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> create(@Valid CreatePaymentDTO dto, HttpServletResponse response, @RequestHeader HttpHeaders httpHeaders) {

        ResponseEntity<?> responseOrder = service.checkOrder(dto.getIdOrder(), httpHeaders);
        if(!responseOrder.getStatusCode().equals(HttpStatus.OK)) {
            throw new IllegalArgumentException("Erro ao consultar o pedido para pagamento. Status retornado da API Order: " + responseOrder.getStatusCode().value());
        }

        Payment payment = new Payment();
        this.copyProperties(dto, payment);
        payment.setPaymentStatus(PaymentStatus.PENDING);
        service.save(payment);

        return ResponseEntity.status(HttpStatus.CREATED).body(payment.toPaymentDTO());
    }

    @Override
    public ResponseEntity<?> update(@Valid CreatePaymentDTO dto, Long id, HttpServletResponse response) {
        Optional<Payment> optional = service.findById(id);

        if(!optional.isPresent()) {
            throw new NoResultException("O pagamento informado não foi encontrado.");
        }

        Payment payment = optional.get();
        this.copyProperties(dto, payment);
        service.save(payment);

        return ResponseEntity.status(HttpStatus.OK).body(payment.toPaymentDTO());
    }

    @Override
    @ApiOperation(value = "Buscar pagamento pelo ID do Pedido", response = Payment.class, notes = "Verifica se o pagamento está vencido através do ID do Pedido informado", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @GetMapping(value = "/checkPaymentExpired/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getCheckPaymentExpired(Long idOrder) {
        List<Payment> listResult = service.findByIdOrderAndDatePayment(idOrder);
        return !listResult.isEmpty() ? new ResponseEntity<>(listResult, HttpStatus.OK) : ResponseEntity.notFound().build();
    }

    @Override
    @ApiOperation(value = "Consultar pagamento por ID", notes = "Exemplo para consumo: /payment/22 (/payment/{id})")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> getPaymentById(Long id) {
        return service.findById(id).map(payment -> ResponseEntity.ok(payment.toPaymentDTO())).orElse(ResponseEntity.notFound().build());
    }

    private void copyProperties(CreatePaymentDTO dto, Payment payment) {
        BeanUtils.copyProperties(dto, payment);
    }
}
