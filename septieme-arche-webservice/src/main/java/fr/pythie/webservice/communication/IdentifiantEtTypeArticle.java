package fr.pythie.webservice.communication;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Classe contenant l'id et le type d'un article. 
 * Permet d'obtenir ultérieurement toutes les informations d'un article en connaissant le contenu
 * exact JSON attendu.
 * 
 * @author Gauthier Nourdin
 * 
 * @version 1.0
 * 
 * @since 1.0
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IdentifiantEtTypeArticle {

	@NonNull
	Long id;
	@NonNull
	String typeArticle;
	
	/**
	 * Constructeur permettant de construire un objet vide.
	 * 
	 * @since 1.0
	 */
	public IdentifiantEtTypeArticle() {
		super();
	}

	/**
	 * Constructeur permettant de construire un objet complet.
	 * 
	 * @param id L'identifiant de l'article.
	 * @param typeArticle Le type de l'article.
	 * 
	 * @since 1.0
	 */
	public IdentifiantEtTypeArticle(@NonNull Long id, @NonNull String typeArticle) {
		super();
		this.id = id;
		this.typeArticle = typeArticle;
	}

	/**
	 * Retourne l'identifiant de l'article.
	 * 
	 * @return id L'identifiant de l'article.
	 * 
	 * @see IdentifiantEtTypeArticle#setId(Long)
	 * 
	 * @since 1.0
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Modifie l'identifiant de l'article.
	 * 
	 * @param id Le nouvel identifiant de l'article.
	 * 
	 * @see IdentifiantEtTypeArticle#getId()
	 * 
	 * @since 1.0
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Retourne le type de l'article.
	 * 
	 * @return typeArticle Le type de l'article.
	 * 
	 * @see IdentifiantEtTypeArticle#setTypeArticle(String)
	 * 
	 * @since 1.0
	 */
	public String getTypeArticle() {
		return typeArticle;
	}

	/**
	 * Modifie le type de l'article.
	 * 
	 * @param typeArticle Le nouveau type de l'article.
	 * 
	 * @see IdentifiantEtTypeArticle#getTypeArticle()
	 * 
	 * @since 1.0
	 */
	public void setTypeArticle(String typeArticle) {
		this.typeArticle = typeArticle;
	}

	/**
	 * Calcule le code hash.
	 * 
	 * @return result Le code hash.
	 * 
	 * @since 1.0
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typeArticle == null) ? 0 : typeArticle.hashCode());
		return result;
	}

	/**
	 * Définit les conditions dans lesquelles un autre objet est égal à celui-ci.
	 *
	 * @param obj L'objet auquel on veut comparer.
	 *
	 * @return true Si les deux objets sont identiques, false sinon.
	 * 
	 * @since 1.0
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IdentifiantEtTypeArticle other = (IdentifiantEtTypeArticle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typeArticle == null) {
			if (other.typeArticle != null)
				return false;
		} else if (!typeArticle.equals(other.typeArticle))
			return false;
		return true;
	}

	/**
     * Produit la chaîne de caractères réprésentant cet objet.
	 * 
	 * @since 1.0
	 */
	@Override
	public String toString() {
		return "IdentifiantEtTypeArticle [id=" + id + ", typeArticle=" + typeArticle + "]";
	}

}
