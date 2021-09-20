package fr.pythie.webservice.communication;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.pythie.webservice.model.Livre;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe permettant de déserialiser un livre présent dans un JSON.
 */
public class LivreDeserializer extends JsonDeserializer<Livre> {

	/**
	 * 
	 */
	@Override
	public Livre deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = (ObjectNode) mapper.readTree(jp);
		Class<? extends Livre> livreClass = null;
		// On vérifie la présence du champ obligatoire "dateFinTirage" des livres imprimés
		if (root.has("dateFinTirage")) {
			livreClass = LivreImprime.class;
		} else {
			livreClass = LivreNumerique.class;
		}
		return mapper.convertValue(root, livreClass);
	}
}
