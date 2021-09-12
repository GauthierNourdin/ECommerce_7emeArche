package fr.pythie.webservice.communication;

import fr.pythie.webservice.model.Consultation;
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
@ToString(of = { "consultation", "idClient" })
@EqualsAndHashCode
public class ConsultationAvecIdClient {
	/**
	 * Classe spéciale destinée à la communication, contient une consultation et
	 * l'id du client auquel elle est associée. Permet de transmettre simplement une
	 * consultation d'un client identifié.
	 */

	@NonNull
	Consultation consultation;
	@NonNull
	Long idClient;
	
}
