package simple.jaxws.server;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public class Hello {
	@WebMethod
	public String sayHello(String name) {
		System.out.println("simple.jaxws.server.Hello#sayHello(String name) > ");
		try {
			Thread.sleep(1000*30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello " + name;
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