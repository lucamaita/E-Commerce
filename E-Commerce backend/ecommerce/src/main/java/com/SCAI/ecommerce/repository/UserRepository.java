package com.SCAI.ecommerce.repository;

import com.SCAI.ecommerce.model.User;
import com.SCAI.ecommerce.model.User.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// Questo blocco di codice definisce UserRepository, un'interfaccia per l'accesso ai dati degli utenti.
// Estende JpaRepository, fornendo così una serie di metodi CRUD (Create, Read, Update, Delete) per l'entità User,
// oltre a funzionalità di paginazione e ordinamento.
// L'interfaccia dichiara due metodi aggiuntivi specifici per le esigenze dell'applicazione:
// findByEmail(String email), per trovare un utente basato sulla sua email,
// e existsByRole(Role role), per verificare l'esistenza di utenti con un determinato ruolo.
// Questi metodi sfruttano la convenzione di denominazione di Spring Data JPA
// per generare automaticamente le implementazioni delle query basate sui nomi dei metodi.
public interface UserRepository extends JpaRepository<User, Long> {

    public User findByEmail(String email);
    public boolean existsByRole(Role role);
}
