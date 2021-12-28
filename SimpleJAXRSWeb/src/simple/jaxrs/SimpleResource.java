package simple.jaxrs;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/simple")
public class SimpleResource {

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response sayHello() {
    	System.out.println("simple.jaxrs.SimpleResource#sayHello > ");
    	int i = 1/0;
    	return Response.ok("Hello from JAXRS").build();
    }
}
