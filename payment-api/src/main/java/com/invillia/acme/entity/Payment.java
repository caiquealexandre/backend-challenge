package com.invillia.acme.entity;

import com.invillia.acme.dto.PaymentDTO;
import com.invillia.acme.enums.PaymentStatus;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_payment")
@SequenceGenerator(name = "generator_payment_seq", sequenceName = "payment_seq", allocationSize = 1, initialValue = 1)
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_payment_seq")
    @Column(name = "id_payment")
    private Long id;

    @Column(name = "credit_card_number")
    private String creditCardNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "payment_status")
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "order_id")
    private Long idOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public PaymentDTO toPaymentDTO() {
        PaymentDTO paymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(this, paymentDTO);
        return paymentDTO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
