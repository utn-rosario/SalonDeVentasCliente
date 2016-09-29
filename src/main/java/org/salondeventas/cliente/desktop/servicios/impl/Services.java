package org.salondeventas.cliente.desktop.servicios.impl;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Services<T extends Serializable> {
	protected Class<T> persistentClass;
	protected static String USER_URI = "http://localhost:8080/SalonDeVentasServer/";	
	protected WebResource webResource;
	protected ClientResponse response;
	protected Client client;	
	private ObjectMapper mapper = new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	public Services() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	protected List<T> buildJsonToObjectArray(String sRespuesta){
		try {
			TypeFactory typeFactory = mapper.getTypeFactory();
			CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, persistentClass);
			return mapper.readValue(sRespuesta, collectionType);			
		} catch (IOException e) {			
			e.printStackTrace();
			return null;	
		}
	}

	protected T buildJsonToObject(String sRespuesta){		
		try {			
			return (T)mapper.readValue(sRespuesta, persistentClass);			
		} catch (IOException e) {			
			e.printStackTrace();
			return null;	
		}
	}
}
