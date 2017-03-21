

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.containsString;



/**
 * This is a test framework to unit test TDDRestService using Rest Assured. I  originally developed it to test
 * my RestEasyExample application as it can connect to the running tomcat server even though it is in a different project. 
 * That all worked fine, so then I decided to move create the TDDRestService in this project as it has evolved to
 * be quite a useful resource for verifying TDD frameworks and practises.
 * 
 * @author markpower
 *
 */
public class MyRestEasyTest extends FunctionalTest {
    
    // default implementation of jsond returns following json
    // {"person":{"city":"Perth","country":"Australia","dob":"21\/10\/1961","hobbies":"Tango,Skiing","name":"Mark Power"}}

    @Test
    public void basicPingTest() {
        given().when().get("/jsond").then().statusCode(200);
    }
    
    @Test
    public void getValidPerson() {
        given().when().get("/jsond/Mark%20Power").then().statusCode(200);
    }
    
    @Test
    public void getInValidPerson() {
        given().when().get("/jsond/Julia Gillard").then().statusCode(404);
    }
    
    @Test
    public void verifyName() {
        given().when().get("/jsond").then().body(containsString("Mark Power"));
    }
    
    @Test
    public void verifyNameStructured() {
        given().when().get("/jsond").then().body("person.name",equalTo("Mark Power"));
    }
    
    @Test
    public void verifyCityAndCountry() {
        given().when().get("/jsond").then().body("person.city",equalTo("Perth")).body("person.country", equalTo("Australia"));
    }
    
    // TODO - to try to resolve this issue suggest writing a new rest service in TDD based on car park tutorial and providing a 
    // POST method that declare JSONObject in the method signature.
    @Test
    public void testSearch() {
        // Map doesn't seem to get encoded correctly
        Map<String,String> p = new HashMap<String,String>(0);
        p.put("name", "Richie Taferner");
        
        // Nope doesn't work either
//        Person p = new Person();
//        p.setName("Richie Taferner");
        
        
        // this version won't compile error is Method body(Map<String,String>) is undefined for the type EncoderConfig
//        given().config(RestAssured.config().encoderConfig(encoderConfig().encodeContentTypeAs("application/json", ContentType.TEXT).body(p).when().get("/jsond")
//        .then().statusCode(200).body("person.country", equalTo("Austria"))));
        
        // this version returns 204 because p is null i.e. gson doesn't appear to convert the Map
        given().contentType("application/json").body(p).when().post("/jsond")
        .then().statusCode(200); //.body("person.country", equalTo("Austria"));
    }
    
    @Test
    public void hashCodeTest() {
        Employee e1 = new Employee(1, "Mark", "Power");
        Employee e2 = new Employee(1, "Mark", "Power");
        
        System.out.println("employee 1 hashCode " + e1.hashCode());
        System.out.println("employee 2 hashCode " + e2.hashCode());
        
        if (e1.equals(e2)) {
            System.out.println("e1.equals(e2) is true");
        }
        else {
            System.out.println("e1.equals(e2) is false");
        }
        
        if (e1 == e2) {
            System.out.println("e1 == e2 is true");
        }
        else {
            System.out.println("e1 == e2 is false");
        }
    }
    
    @Test
    public void stringEqualsTest() {
        String e1 = "MarkPower";
        String e2 = "MarkPower";
        
        System.out.println("employee 1 hashCode " + e1.hashCode());
        System.out.println("employee 2 hashCode " + e2.hashCode());
        
        if (e1.equals(e2)) {
            System.out.println("e1.equals(e2) is true");
        }
        else {
            System.out.println("e1.equals(e2) is false");
        }
        
        if (e1 == e2) {
            System.out.println("e1 == e2 is true");
        }
        else {
            System.out.println("e1 == e2 is false");
        }
    }
    
    @Test
    public void stringCallTest() {
        int result = 0;

        Boolean b1 = new Boolean("TRUE");
        Boolean b2 = new Boolean("true");
        Boolean b3 = new Boolean("tRuE");
        Boolean b4 = new Boolean("false");

        if (b1 == b2)  /* Line 10 */
            result = 1;
        if (b1.equals(b2) ) /* Line 12 */
            result = result + 10;
        if (b2 == b4)  /* Line 14 */
            result = result + 100;
        if (b2.equals(b4) ) /* Line 16 */
            result = result + 1000;
        if (b2.equals(b3) ) /* Line 18 */
            result = result + 10000;

        System.out.println("result = " + result);
        
        long y = (byte)100;
        System.out.println("y = " + y);
        
        int one = 16 * 4;
        int two = 16>>2;
        int three = 16/2^2;
        int four = 16>>>2;
        
        System.out.println(one + " " + two + " " + three + " " + four);
        
        int I = 1;
        do while ( I < 1 )
        System.out.print("I is " + I);
        while ( I > 1 ) ;
        System.out.println("done");
        
        for(int i = 0; i < 3; i++) 
        {
            System.out.print(i + "..");
        }
    }
    
    
}
