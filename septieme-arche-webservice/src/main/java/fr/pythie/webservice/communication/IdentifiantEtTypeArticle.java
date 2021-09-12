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
@ToString(of = { "id", "typeArticleString" })
@EqualsAndHashCode
public class IdentifiantEtTypeArticle {
	/**
	 * Classe spéciale destinée à la communication, contient l'id et le type d'un
	 * article afin de pouvoir obtenir toutes ses informations ultérieurement sans
	 * problème.
	 */

	@NonNull
	Long id;
	@NonNull
	String typeArticleString;
	
}
