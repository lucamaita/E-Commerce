package com.SCAI.ecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// La classe LoginRequest contiene due campi:
// email: Una stringa che rappresenta l'indirizzo email dell'utente che sta tentando di effettuare il login.
// L'email è spesso utilizzata come identificativo unico per l'utente in molte applicazioni web.

// password: Una stringa che rappresenta la password dell'utente.
// La password, insieme all'email, è utilizzata per autenticare l'utente e garantire l'accesso all'applicazione.

// In sintesi, LoginRequest è una classe di modello utilizzata per trasportare i dati di login
// dall'utente al server in un'applicazione di e-commerce, facilitando il processo di autenticazione.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    private String password;
}
