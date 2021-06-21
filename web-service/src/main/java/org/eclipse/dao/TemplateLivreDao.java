package org.eclipse.dao;

import java.util.List;

import org.eclipse.model.Auteur;
import org.eclipse.model.Genre;

public interface TemplateLivreDao<T> extends TemplateDao<T> {

	public T ajouterAuteur (T t, Auteur auteur);
	
	public T ajouterGenre (T t, Genre genre);
	
	public T retirerAuteur (T t, Auteur auteur);
	
	public T retirerGenre (T t, Genre genre);
	
	public List<T> trouverParAuteur (T t, Auteur auteur);
	
	public List<T> trouverParGenre (T t, Genre genre);
	
}
