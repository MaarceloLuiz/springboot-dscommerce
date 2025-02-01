package com.marceloluiz.DSCommerce.dto;

import com.marceloluiz.DSCommerce.entities.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@NoArgsConstructor
@Getter
public class PaymentDTO {
    private Long id;
    private Instant moment;

    public PaymentDTO(Payment payment) {
        id = payment.getId();
        moment = payment.getMoment();
    }
}
