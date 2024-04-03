package com.SCAI.ecommerce.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// Questo blocco di codice definisce l'entità User, utilizzando le annotazioni di JPA per mappare la classe
// a una tabella del database e le annotazioni di Lombok per generare automaticamente i metodi getter e setter.
// La classe User include campi per l'ID (chiave primaria generata automaticamente), password, email (unica per ogni utente),
// nome completo e ruolo. Il ruolo dell'utente è definito tramite un'enumerazione Role, che può essere ADMIN o GUEST,
// e viene mappato come stringa nel database.
@Getter
@Setter
@Entity // Mappa la classe a una tabella di un db, richiede @Id
public class User {

    public enum Role {
        ADMIN,
        GUEST
    }

    @Id // specifica l'attributo PK
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    @Column(unique = true)
    private String email;
    private String fullName;
    @Enumerated(EnumType.STRING)
    private Role role;
}
