package org.eclipse.dao;

import org.eclipse.model.Adresse;

public interface TemplateClientDao<Client> extends TemplateDao<Client> {

	public Client identifierClient(String email, String motDePasse);
	
	public Client modifierAdresseFacturation(Adresse adresseFacturation, Client client);
	
	public Client modifierAdresseLivraison(Adresse adresseLivraison, Client client);
	
}
