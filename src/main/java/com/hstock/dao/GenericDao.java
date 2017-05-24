package com.hstock.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<E> {

	Serializable save(E entity);
	
	public void saveOrUpdate(E entity);
	
	public void delete(Serializable id);
	
	public List<E> findAll();
	
	public E findById(Serializable id);
	
	public List<E> findByLike(E entity);

	public void clear();
	
	public void flush();
}
