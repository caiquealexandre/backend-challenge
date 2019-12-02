package com.invillia.acme.service;

import com.invillia.acme.entity.Payment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentService {

    public Payment save(Payment payment);

    public Optional<Payment> findById(Long id);

    public List<Payment> findByIdOrderAndDatePayment(Long idOrder);

    public ResponseEntity<String> checkOrder(Long id, HttpHeaders httpHeaders);

}
