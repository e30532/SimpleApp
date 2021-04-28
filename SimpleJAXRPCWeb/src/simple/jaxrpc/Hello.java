package simple.jaxrpc;

public class Hello {
	public String say(String name) {
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Hello " + name;
	}
}
