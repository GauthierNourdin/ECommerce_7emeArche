package fr.pythie.webservice.communication;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe permettant de déserialiser un article présent dans un JSON.
 * C'est indispensable pour lire correctement les consultations et les commandes.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
public class ArticleDeserializer extends JsonDeserializer<Article> {

	/**
	 * 
	 * 
	 */ 
	@Override
	public Article deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

		ObjectMapper mapper = (ObjectMapper) jp.getCodec();
		ObjectNode root = (ObjectNode) mapper.readTree(jp);
		Class<? extends Article> articleClass = null;
		// On vérifie la présence du champ obligatoire "dateFinTirage" des livres imprimés
		if (root.has("dateFinTirage")) {
			articleClass = LivreImprime.class;
		} else {
			articleClass = LivreNumerique.class;
		}
		return mapper.convertValue(root, articleClass);
	}
	
}
