


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONObject;


/**
 * The purpose of this class is to provide a basic rest service to practise REST Assured unit testing techniques against and in the process to
 * learn more about how REST works.
 * 
 * I've included both POST and GET methods although in the real world you might not include these 2 methods in the same service? Or maybe you would.
 * The code below proves that the same path /json will resolve to distinct methods based on verb used GET/POST.
 * 
 * @author markpower
 *
 */

@Path("/json")
public class TDDRestService {


    // This is the syntax to POST JSON parsed from a pojo or HashMap by Restassured API. 
    // i.e. commented method below caused unit tests to fail. Removing @QueryParam worked!!!
	// public Person personDetails(@QueryParam("name") String name) {
    // use this method to test Restassured post syntax
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Person findPerson(String json) {
	    // TODO find out how to debug Rest service running on tomcat from eclipse so I dont have to use print statements
        System.out.println("****RestService POST...json param = " + json);
        
        try {
            //JSONArray arr = new JSONArray(json);
            JSONObject obj = new JSONObject(json);
            
            for (Person p : getPersons()) {
                System.out.println("p.getName() " + p.getName() + " name: " + obj.get("name"));
                if (p.getName().equals(obj.get("name"))) {
                    System.out.println("returning " + p.getName());
                    return p;
                    //return "{\"Name\": \"" + json + " \"}";
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return null;
        // run this return type with PostServiceTest.testPostResponseBasic() 
        //return "{\"Name\": \"" + json + " \"}";
    }
	
	// use this method to test Restassured get syntax
	@GET
    @Produces({MediaType.APPLICATION_JSON})
    public PaginatedListWrapper listPersons() {
	    System.out.println("****RestService GET");
        PaginatedListWrapper wrapper = new PaginatedListWrapper();
        getPersons(wrapper);
        return wrapper;
    }
	
	private void getPersons(PaginatedListWrapper wrapper) {
        ArrayList<Person> list = new ArrayList<Person>(0);
        Person p = new Person("Mark Power", "Perth", "Australia");
        list.add(p);
        p = new Person("Clive Levido", "Auckland", "New Zealand");
        list.add(p);
        p = new Person("Richie Taferner", "Bichling", "Austria");
        list.add(p);
        p = new Person("Georg Kurz", "Westendorf", "Austria");
        list.add(p);
        p = new Person("Tina Sorenson", "Copenhagen", "Denmark");
        list.add(p);
        
        wrapper.setList(list);
    }
	
	private List<Person> getPersons() throws Exception {
        ArrayList<Person> list = new ArrayList<Person>(0);
        Person p = new Person("Mark Power", "Perth", "Australia");
        p.setDob(DateUtils.getDate("21/10/1961"));
        p.setHobbies("Tango,Skiing");
        list.add(p);
        p = new Person("Clive Levido", "Auckland", "New Zealand");
        list.add(p);
        p = new Person("Richie Taferner", "Bichling", "Austria");
        list.add(p);
        p = new Person("Georg Kurz", "Westendorf", "Austria");
        list.add(p);
        p = new Person("Tina Sorenson", "Copenhagen", "Denmark");
        list.add(p);
        
        return list;
    }
	
}
