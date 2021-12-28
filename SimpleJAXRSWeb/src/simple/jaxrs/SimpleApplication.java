package simple.jaxrs;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
//@ApplicationScoped
//@ApplicationPath("/api")
public class SimpleApplication extends Application{
    public Set<Class<?>> getClasses(){
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(SimpleResource.class);
        return classes;
}
}
