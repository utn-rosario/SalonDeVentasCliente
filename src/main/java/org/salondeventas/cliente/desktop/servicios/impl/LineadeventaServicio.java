package org.salondeventas.cliente.desktop.servicios.impl;

import java.util.List;
import javax.ws.rs.core.MediaType;
import org.salondeventas.cliente.desktop.modelo.Lineadeventa;
import org.salondeventas.cliente.desktop.servicios.ILineadeventaServicio;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class LineadeventaServicio extends Services<Lineadeventa> implements ILineadeventaServicio {	
		
	@Override
	public String insert(Lineadeventa lineadeventa) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "lineadeventa/insert");		
		String stringJson = mapper.writeValueAsString(lineadeventa);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public String update(Lineadeventa lineadeventa) throws Exception {	
		client = Client.create();
		webResource = client.resource(USER_URI + "lineadeventa/update");		
		String stringJson = mapper.writeValueAsString(lineadeventa);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public String delete(Lineadeventa lineadeventa) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "lineadeventa/delete");		
		String stringJson = mapper.writeValueAsString(lineadeventa);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}		
		
		return response.getEntity(String.class);
	}
	
	@Override
	public Lineadeventa load(Lineadeventa lineadeventa) throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "lineadeventa/load");		
		String stringJson = mapper.writeValueAsString(lineadeventa);
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class, stringJson);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}				
		
		return this.buildJsonToObject(response.getEntity(String.class));
	}
	
	@Override
	public List<Lineadeventa> loadAll() throws Exception {		
		client = Client.create();
		webResource = client.resource(USER_URI + "lineadeventa/loadall");		
		
		response = webResource
				.queryParam("usuario", "leonel")
				.queryParam("clave", "123")
				.type(MediaType.APPLICATION_JSON_TYPE)					
				.post(ClientResponse.class);
		
		if (response.getStatus() != 200) {
			throw new Exception("Failed : HTTP error code : "
			     + response.getStatus());
		}				
		
		return this.buildJsonToObjectArray(response.getEntity(String.class));
	}
}