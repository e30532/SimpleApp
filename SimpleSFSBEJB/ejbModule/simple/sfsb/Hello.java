package simple.sfsb;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import simple.sfsb.view.HelloRemote;

/**
 * Session Bean implementation class Hello
 */
@Stateful
@Remote(HelloRemote.class)
public class Hello implements HelloRemote {

    /**
     * Default constructor. 
     */
	int counter = 0;
	
    public Hello() {
    }
    public void say() {
    	try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(counter++);

    }

}
