package simple.ejb3;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import simple.ejb3.view.HelloRemote;

/**
 * Session Bean implementation class Hello
 */
@Stateless
@Remote(HelloRemote.class)
public class Hello implements HelloRemote {

    /**
     * Default constructor. 
     */
    public Hello() {
        // TODO Auto-generated constructor stub
    }
    
    public String say(String name){
        System.out.println("simple.ejb3.Hello.say>");
        System.out.println(name);
        System.out.println("simple.ejb3.Hello.say<");
        return "Helllo " + name;
    }
}
