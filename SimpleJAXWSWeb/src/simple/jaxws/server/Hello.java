package simple.jaxws.server;

import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService
@HandlerChain(file="handler-chain.xml")
public class Hello {
	@Resource
	private WebServiceContext context; 
	
	@WebMethod
	public Person sayHello(Person person) {
		System.out.println("simple.jaxws.server.Hello#sayHello(String name) > ");
		try {
			Thread.sleep(1000*2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		person.setAge("6");
		return person;
	}
	
	@WebMethod
	@Oneway
	public void ping() {
		System.out.println("simple.jaxws.server.Hello#ping() > ");
	}
	
	@WebMethod
	public String sleep(int sleeptime) {
		System.out.println("simple.jaxws.server.Hello#sleep(int sleeptime) > ");
		try {
			System.out.println("I will sleep for " + sleeptime  + " seconds.");
			Thread.sleep(sleeptime*1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "wake up!";
	}
}
