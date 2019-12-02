package com.invillia.acme.service.impl;

import com.invillia.acme.dto.CreatePaymentDTO;
import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.entity.Payment;
import com.invillia.acme.repository.PaymentRepository;
import com.invillia.acme.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository repository;

    @Value("${order-rest-api.url}")
    private String urlOrderApi;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Override
    public Payment save(Payment payment) {
        return repository.saveAndFlush(payment);
    }

    @Override
    public Optional<Payment> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Payment> findByIdOrderAndDatePayment(Long idOrder) {
        return repository.findByIdOrderAndDatePayment(idOrder, LocalDateTime.now().minusDays(10));
    }

    @Override
    public ResponseEntity<String> checkOrder(Long id, HttpHeaders httpHeaders) {
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);

        try {
            ResponseEntity<String> result = restTemplate().exchange(urlOrderApi + id, HttpMethod.GET, httpEntity, String.class);
            return ResponseEntity.status(HttpStatus.OK).body(result.toString());
        }
        catch(HttpClientErrorException e) {
            return ResponseEntity.status(e.getRawStatusCode()).headers(e.getResponseHeaders()).body(e.getResponseBodyAsString());
        }

    }


}
