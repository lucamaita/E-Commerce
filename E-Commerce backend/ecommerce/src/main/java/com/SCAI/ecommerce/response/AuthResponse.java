package com.SCAI.ecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// La classe AuthResponse contiene due campi:

// jwt: Una stringa che rappresenta il token JWT (JSON Web Token) utilizzato per l'autenticazione dell'utente.
// Il token JWT è un modo sicuro per rappresentare le informazioni tra due parti.

// message: Una stringa utilizzata per fornire un messaggio di risposta,
// che può essere utilizzato per comunicare lo stato dell'autenticazione o altri messaggi pertinenti all'utente.

//In sintesi, AuthResponse è una classe di modello utilizzata per trasportare i dati di risposta relativi all'autenticazione,
// come il token JWT e i messaggi di stato, tra il server e il client.
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String message;
}
