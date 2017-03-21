

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;



/**
 * This is a test framework to unit test my RestEasyExample application using Rest Assured
 * Apparently it should be able to connect to the running tomcat server even though it is in 
 * a different project. 
 * The reason I have put it in the jUnique project is because of maven issues caused by Spring/hibernate
 * incompatibilities in the RestEasyExample project. When I've cleaned up those issues I can port this across
 * because it would be interesting to see if debugging the unit test would step through the code on the server.
 * 
 * @author markpower
 *
 */
public class TDDRestServiceTest extends TDDFunctionalTest {
    
    // default implementation of jsond returns following json
    // {"person":{"city":"Perth","country":"Australia","dob":"21\/10\/1961","hobbies":"Tango,Skiing","name":"Mark Power"}}

    // GET tests
    
    @Test
    public void testGetPing() {
        given().contentType("application/json").when().get("/json").peek()
        .then().statusCode(200); //.body("person.country", equalTo("Austria"));
    }
    
    
    @Test
    public void testGetContains() {
        given().contentType("application/json").when().get("/json").peek()
        .then().body(containsString("Georg Kurz")); //.body("person.country", equalTo("Austria"));
    }
    
    
    // {"paginatedListWrapper":{"list":[{"city":"Perth","country":"Australia","name":"Mark Power"},
    // {"city":"Auckland","country":"New Zealand","name":"Clive Levido"},
    // {"city":"Bichling","country":"Austria","name":"Richie Taferner"},{"city":"Westendorf","country":"Austria","name":"Georg Kurz"},
    // {"city":"Copenhagen","country":"Denmark","name":"Tina Sorenson"}]}}
    @Test
    public void verifyNameStructured() {
        // Note the dot notation to walk the json elements
        given().when().get("/json").then().body("paginatedListWrapper.list[0].city",equalTo("Perth"));
        given().when().get("/json").then().body("paginatedListWrapper.list[1].city",equalTo("Auckland"));
        given().when().get("/json").then().body("paginatedListWrapper.list[2].city",equalTo("Bichling"));
    }
    
    // Tests chaining multiple body parts. REST Assured makes possible multiple checks to work in unison.
    // This test will succeed only if both checks on the response body are successful
    @Test
    public void verifyMultipleFields() {
        // Note the dot notation to walk the json elements
        given().when().get("/json").then().body("paginatedListWrapper.list[0].city",equalTo("Perth")).
        body("paginatedListWrapper.list[0].country",equalTo("Australia"));
       
    }
    
    
    @Test
    public void getResponsePojo() {
        PaginatedListWrapper ret = given().when().get("/json").as(PaginatedListWrapper.class);
        assertNotNull("should get result", ret);
        // not null but not initialised
        
        // RestAssured as(class) parser doesnt work so I'm using asString and parsing myself.
        //ret = given().when().get("/json").as(PaginatedListWrapper.class);
        
        String rstr = given().when().get("/json").asString();
        ret = jToP(rstr);
       
        assertNotNull("should get a list", ret.getList());
    }
    
    
    
    // POST tests
    
    // This test was failing until I fixed the POST method signature. Removing @QueryParam fixed issue.
    // see PostRestService
    @Test
    public void testPostWithMap() {
        // Map doesn't seem to get encoded correctly
        Map<String,String> p = new HashMap<String,String>(0);
        p.put("name", "Richie Taferner");
        
        given().contentType("application/json").body(p).when().post("/json")
        .then().statusCode(200); //.body("person.country", equalTo("Austria"));
    }
    
    @Test
    public void testPostWithPojo() {
        Person p = new Person();
        p.setName("Georg Kurz");
        
        given().contentType("application/json").body(p).when().post("/json")
        .then().statusCode(200); //.body("person.country", equalTo("Austria"));
    }
    
    // this version worked with service returning String "{\"Name\": \"" + json + " \"}";
    // It tests the method returning a json string. Service code is now commented out
//    @Test
//    public void testPostResponseBasic() {
//        Map<String,String> p = new HashMap<String,String>(0);
//        p.put("name", "Tina Sorenson");
//        
//        // NB case sensitivity of Name param
//        given().contentType("application/json").body(p).when().post("/json")
//        .then().body("Name", equalTo("Tina Sorenson"));
//     
//    }
    
    // This method tests returning a pojo and verifying its content
    @Test
    public void testPostResponsePojo() {
        Map<String,String> p = new HashMap<String,String>(0);
        p.put("name", "Tina Sorenson");
         
        // Service is returning an uninitialised person 
        // It appears as(Person.class) is not working properly
//        Person per = given().contentType("application/json").body(p).when().post("/json")
//        .peek().as(Person.class);
        
        // this works
        String pstr = given().contentType("application/json").body(p).when().post("/json")
                .peek().asString();
        //System.out.println("pstr = [" + pstr + "]");
        Person per = jsonToPerson(pstr);
        
        assertNotNull("should get person", per);
        assertEquals("name should be Tina", "Tina Sorenson", per.getName());  
    }
    
    // ##### Parsing methods to get around what appears to be a problem with RestAssured
    
    // this is what as(Person.class) should be doing!!
    private Person jsonToPerson(String str) {
        Person p = new Person();
        try {
            JSONObject wrapper = new JSONObject(str);
            JSONObject obj = wrapper.getJSONObject("person");
            //System.out.println("person = " + obj.get("person"));
            p.setCity(obj.getString("city"));
            p.setCountry(obj.getString("country"));
            p.setName(obj.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
    /**
     * Once again Restassured as(PaginatedListWrapper.class) should take care of the parsing??
     * @param str
     * @return
     */
    private PaginatedListWrapper jToP(String str) {
        PaginatedListWrapper p = new PaginatedListWrapper();
        try {
            JSONObject wrapper = new JSONObject(str);
            JSONObject obj = wrapper.getJSONObject("paginatedListWrapper");
            JSONArray arr = obj.getJSONArray("list");
            
            for (int i = 0; i < arr.length(); i++) {
                JSONObject jo = arr.getJSONObject(i);
                Person per = new Person();
                per.setCity(jo.getString("city"));
                per.setCountry(jo.getString("country"));
                per.setName(jo.getString("name"));
                
                if (p.getList() == null) {
                    ArrayList<Person> l = new ArrayList<Person>(0);
                    p.setList(l);
                }
                p.getList().add(per);            
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }
    
   
}
