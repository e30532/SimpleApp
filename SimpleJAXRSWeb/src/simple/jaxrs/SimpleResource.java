package simple.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/simple")
public class SimpleResource {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
    	System.out.println("simple.jaxrs.SimpleResource#sayHello > ");
    	return Response.ok("Hello from JAXRS").build();
    }
}
