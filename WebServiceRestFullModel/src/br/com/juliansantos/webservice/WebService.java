package br.com.juliansantos.webservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/webservice")
public class WebService {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/getText")
	public String getText() {
		return "Texto de retorno";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/getXML")
	public String getXML() {
		return "<xml></xml>";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getJSon")
	public String getJSon() {
		return "{texto=\"Texto de retorno\"}";
	}
	
	
}
