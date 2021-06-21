package org.eclipse.dao;

public interface TemplateAdresseDao<Adresse> extends TemplateDao<Adresse> {

	public int verifierDoublon(Adresse adresse);

}
