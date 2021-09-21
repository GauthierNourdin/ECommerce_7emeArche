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
	String typeArticleString;
	
	/**
	 * 
	 */
	public IdentifiantEtTypeArticle() {
		super();
	}

	/**
	 * 
	 * @param id
	 * @param typeArticleString
	 */
	public IdentifiantEtTypeArticle(@NonNull Long id, @NonNull String typeArticleString) {
		super();
		this.id = id;
		this.typeArticleString = typeArticleString;
	}

	/**
	 * 
	 * @return
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getTypeArticleString() {
		return typeArticleString;
	}

	/**
	 * 
	 * @param typeArticleString
	 */
	public void setTypeArticleString(String typeArticleString) {
		this.typeArticleString = typeArticleString;
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typeArticleString == null) ? 0 : typeArticleString.hashCode());
		return result;
	}

	/**
	 * 
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
		if (typeArticleString == null) {
			if (other.typeArticleString != null)
				return false;
		} else if (!typeArticleString.equals(other.typeArticleString))
			return false;
		return true;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "IdentifiantEtTypeArticle [id=" + id + ", typeArticleString=" + typeArticleString + "]";
	}

}
