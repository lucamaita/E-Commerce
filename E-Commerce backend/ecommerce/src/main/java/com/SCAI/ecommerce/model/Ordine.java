package com.SCAI.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// Questo blocco di codice definisce l'entità Ordine,
// utilizzando le annotazioni di JPA (Java Persistence API) per mappare la classe a una tabella
// del database e le annotazioni di Lombok per generare automaticamente i metodi getter e setter.
// Include campi per l'ID dell'ordine, il prodotto associato, la quantità ordinata,
// l'utente che ha effettuato l'ordine, lo stato dell'ordine e il prezzo totale,
// stabilendo relazioni uno-a-uno con le entità Product e User.
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
