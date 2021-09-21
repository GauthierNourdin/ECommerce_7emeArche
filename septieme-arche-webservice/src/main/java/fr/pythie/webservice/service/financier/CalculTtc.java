package fr.pythie.webservice.service.financier;

import org.springframework.stereotype.Component;

import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe service spécialisée pour calculer le prix TTC des articles. Applique un taux différent selon le type d'article.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@Component
public class CalculTtc {

	// Taux à appliquer.
	private static final double taxeLivreNumerique = 0.055;
	private static final double taxeLivreImprime = 0.055;

	/** 
	 * Calcule le prix TTC d'un article à partir de son
	 * prix HT.
	 * 
	 * @param article Article dont on veut calculer le prix TTC.
	 * 
	 * @since 1.0
	 */
	public static void calculerPrixTTC(Article article) {
		if (article instanceof LivreNumerique) {
			article.setPrixTTC((int) Math.round(Math.ceil(article.getPrixHT() * (1 + taxeLivreNumerique))));
		}

		if (article instanceof LivreImprime) {
			article.setPrixTTC((int) Math.round(Math.ceil(article.getPrixHT() * (1 + taxeLivreImprime))));
		}
	}

}
