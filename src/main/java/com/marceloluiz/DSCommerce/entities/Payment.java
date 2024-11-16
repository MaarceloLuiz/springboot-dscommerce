package com.marceloluiz.DSCommerce.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
@Entity
@Table(name = "tb_payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    @OneToOne
    @MapsId
    private Order order;
}
