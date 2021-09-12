package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Commande;
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
@ToString(of = { "commande", "idClient" })
@EqualsAndHashCode
public class CommandeAvecIdClient {
	/**
	 * Classe spéciale destinée à la communication, contient une commande et
	 * l'id du client auquel elle est associée. Permet de transmettre simplement une
	 * commande d'un client identifié.
	 */

	@NonNull
	Commande commande;
	@NonNull
	Long idClient;
}
