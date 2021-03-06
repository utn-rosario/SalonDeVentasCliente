package org.salondeventas.cliente.desktop.servicios;

import java.io.Serializable;
import java.util.List;

public interface IServicio<T, ID extends Serializable> {
	
	public String insert(T entity) throws Exception;
	public String update(T entity) throws Exception;
	public String delete(T entity) throws Exception;	
	public T load(T entity) throws Exception;			
	public List<T> loadAll() throws Exception;
}