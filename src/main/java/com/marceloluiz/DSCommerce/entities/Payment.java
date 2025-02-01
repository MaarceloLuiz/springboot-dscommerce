package com.marceloluiz.DSCommerce.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor // Required by Hibernate
@AllArgsConstructor // Required by @Builder
@Builder
@Getter
@Setter
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
