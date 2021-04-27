package simple.soap.jms;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.BindingType;

import simple.soap.jms.view.HelloRemote;

/**
 * Session Bean implementation class Hello
 */
@WebService
@BindingType("http://www.w3.org/2010/soapjms/")
@Stateless
@Remote(HelloRemote.class)
public class Hello implements HelloRemote {

    /**
     * Default constructor. 
     */
    public Hello() {
        // TODO Auto-generated constructor stub
    }
    public String say(String name) {
    	return "Hello " + name;
    }
    
    @WebMethod
    @Oneway
    public void ping() {
    	System.out.println("pong");
    }
}
