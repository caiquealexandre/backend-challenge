package com.invillia.acme.repository;

import com.invillia.acme.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p WHERE p.idOrder = :idOrder AND p.paymentDate > :paymentDate ")
    List<Payment> findByIdOrderAndDatePayment(@Param("paymentDate") Long idOrder, @Param("paymentDate") LocalDateTime paymentDate);

}
