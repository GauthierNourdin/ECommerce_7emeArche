package fr.pythie.webservice.communication;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString(of = { "email", "motDePasse" })
@EqualsAndHashCode
public class DemandeAuthentification {
	/**
	 * Classe spéciale destinée à la communication, contient un email et un mot de
	 * passe afin de déposer une demande d'authentification d'un client.
	 */
	
	@NonNull
	String email;
	@NonNull
	String motDePasse;

}
