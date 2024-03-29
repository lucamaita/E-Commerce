package com.SCAI.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;

    private Long quantita;

    @OneToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    private String stato;

    private Double prezzo;
}
