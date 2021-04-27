package org.eclipse.dao;

import java.util.List;

public interface TemplateDao<T> {
	T save(T t);

	void remove(T t);

	T update(T t);

	T findById(int id);

	List<T> findAll();
}
