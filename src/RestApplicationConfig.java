

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;


public class RestApplicationConfig extends Application {

	private Set<Object> singletons = new HashSet<Object>();

    public RestApplicationConfig() {
    	System.out.println("\n\n*********** RESTEasy is fabulous");
        singletons.add(new TDDRestService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
