package simple.jaxrs20.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import java.lang.String;
import javax.ws.rs.client.Entity;

public class SimpleJAXRSServiceClient {

	final String resourceUrl;
	
	public SimpleJAXRSServiceClient(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	public Response invokeSayHello(){
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(resourceUrl + "/example20");
		Invocation.Builder builder = target.request("text/plain");
		Response response = builder.get();
		return response; 
	}

	public Response invokeSayHellowithName(String s){
		Client client = ClientBuilder.newBuilder().build();
		WebTarget target = client.target(resourceUrl + "/example20");
		Invocation.Builder builder = target.request("text/plain");
		
		Response response = builder.post(Entity.entity(s, "text/plain"));
		return response; 
	}

}
