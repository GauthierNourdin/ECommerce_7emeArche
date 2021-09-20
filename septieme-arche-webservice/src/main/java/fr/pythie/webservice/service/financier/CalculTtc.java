package fr.pythie.webservice.service.financier;

import org.springframework.stereotype.Component;

import fr.pythie.webservice.model.Article;
import fr.pythie.webservice.model.LivreImprime;
import fr.pythie.webservice.model.LivreNumerique;

/**
 * Classe spécialisée pour calculer le prix TTC à partir de règles fixes pour
 * les taxes
 */
@Component
public class CalculTtc {


	// Taux à appliquer
	private static final double taxeLivreNumerique = 0.055;
	private static final double taxeLivreImprime = 0.055;

	// Fonction permettant d'affecter un prix TTC dans un article à partir de son
	// prix HT
	public static void calculerPrixTTC(Article article) {
		if (article instanceof LivreNumerique) {
			article.setPrixTTC((int) Math.round(Math.ceil(article.getPrixHT() * (1 + taxeLivreNumerique))));
		}

		if (article instanceof LivreImprime) {
			article.setPrixTTC((int) Math.round(Math.ceil(article.getPrixHT() * (1 + taxeLivreImprime))));
		}
	}

}
