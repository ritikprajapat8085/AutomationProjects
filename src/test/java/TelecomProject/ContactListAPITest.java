package TelecomProject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ContactListAPITest {

     String email = "ritikprajapti" + System.currentTimeMillis() + "@gmail.com";
     String token = "";
     String contactId= "";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";
    }

    @Test(priority = 1)
    public void TC01_testAddNewUser()
    {
        String jsonbody = "{  \"firstName\": \"Ritik\",\n" +
                "              \"lastName\": \"Prajapati\",\n" +
                "               \"email\":    \""+email + "\",\n" +
                "                \"password\": \"Ritik@123\"}";
        Response response = given()
                .body(jsonbody).header("Content-Type", "application/json")
                .when().post("/users") ;
         token = response.jsonPath().getString("token");
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println("++++++++++++++++++++++++TC1 passed: added new user++++++++++++++++++++++++++++");
    }



    @Test(priority = 2)
    public void TC02_getUserProfile()
    {
       RestAssured .given().header("Authorization", "Bearer " + token)
                .when().get("/users/me")
                .then().statusCode(200).body("email", equalTo(email));

        System.out.println("++++++++++++++++++++++++TC2 Passed : got user profile+++++++++++++++++++++++++++");
    }

    @Test(priority = 3)
    public void TC03_updateUser()
    {
        String payload = "{\n" +
                "  \"firstName\": \"Ritu\",\n" +
                "  \"lastName\": \"Prajapati\",\n" +
                "  \"email\": \"" +email+ "\",\n" +  // Note: only Email is updating when  using dynamic email other are bot updating because server side problem
                "  \"password\": \"Ritu@123\"\n" +
                "}";
        Response response =given().header("Authorization", "Bearer " + token).body(payload)
                .when().patch("/users/me") ;
        System.out.println(response.asPrettyString());
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("++++++++++++++++++++++++TC3 passed: updated user++++++++++++++++++++++++++++");
    }


    @Test(priority = 4)
    public void TC04_loginUser()
    {
        String payload ="{\n" +
                "  \"email\": \"ritikprajapati@gmail.com\",\n" +
                "  \"password\": \"Ritik@123\"\n" +
                "}";
        Response response = given().header("Content-Type", "application/json")
                .body(payload)
                .when().post("/users/login");
        token = response.jsonPath().getString("token");
       Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.asPrettyString());
        System.out.println("++++++++++++++++++++++++TC4 passed:user logined ++++++++++++++++++++++++++++");
    }



    @Test(priority = 5)
    public void  TC05_AddContact()
    {
        HashMap<String,String>map = new HashMap<>();        // using HashMap : for complex  json data
        map.put("firstName", "John");
        map.put("lastName", "Doe");
        map.put("birthdate", "1970-01-01");
        map.put("email", "jdoe@fake.com");
        map.put("phone", "8005555555");
        map.put("street1", "1 Main St.");
        map.put("street2", "Apartment A");
        map.put("city", "Anytown");
        map.put("stateProvince", "KS");
        map.put("postalCode", "12345");
        map.put("country", "USA");

        Response response = RestAssured
                .given().body(map).header("Authorization", "Bearer " + token).header("Content-Type", "application/json")       // add : contenttype because using map
                .when().post("/contacts") ;

        contactId = response .jsonPath().getString("_id");
        response.then().statusCode(201);

        System.out.println(response.asPrettyString());
        System.out.println("++++++++++++++++++++++++TC5 Passed : User added+++++++++++++++++++++++++++");
    }


    @Test(priority = 6)
    public void TC06_getContactList()
    {
        Response response = RestAssured. given().header("Authorization", "Bearer " + token)
                .when().get("/contacts") ;
        response.then().statusCode(200);

        System.out.println(response.asPrettyString());
        System.out.println("++++++++++++++TC6 Passed : got contact list ++++++++++++++++++");
    }

    @Test(priority = 7)
    public void TC07_getContactById()
    {
        given().header("Authorization", "Bearer " + token)
                .when().get("/contacts/" + contactId)
                .then().statusCode(200).body("_id", equalTo(contactId));
        System.out.println("++++++++++++++TC7 Passed : got contact ++++++++++++++++++");
    }

    @Test(priority = 8)
    public void TC08_updateFullContact() {
        POJOCLASS contact = new  POJOCLASS ();
        contact.setFirstName("Amy");
        contact.setLastName("Miller");
        contact.setBirthdate("1992-02-02");
        contact.setEmail("amiller@fake.com");
        contact.setPhone("8005554242");
        contact.setStreet1("13 School St.");
        contact.setStreet2("Apt. 5");
        contact.setCity("Washington");
        contact.setStateProvince("QC");
        contact.setPostalCode("A1A1A1");
        contact.setCountry("Canada");

        Response response = RestAssured .  given()
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .body(contact)
                .when()
                .put("/contacts/" + contactId) ;

        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println(response.asPrettyString());
        System.out.println("+++++++++ TC8 passed: Contact updated using POJO successfully +++++++++");

    }


    @Test(priority = 9)
    public void TC09_partialUpdateContact()
    {
        String payload = "{\n" +
                "  \"firstName\": \"Anna\"\n" +
                "}";
        Response response = RestAssured.
                 given().header("Authorization", "Bearer " + token) .header("Content-Type", "application/json").body(payload)       // add header then only working
                .when().patch("/contacts/" + contactId) ;

                response .then().statusCode(200).body("firstName", equalTo("Anna"));
               System.out.println(response.asPrettyString());
        System.out.println("++++++++++++++TC9 Passed : firstname updated ++++++++++++++++++");

    }


    @Test(priority = 10)
    public void TC10_logoutUser()
    {
        given().header("Authorization", "Bearer " + token)
                .when().post("/users/logout")
                .then().statusCode(200);
        System.out.println("++++++++++++++TC10 Passed :  user logout ++++++++++++++++++");
    }


}



// NOTE: if validating json body / status code in one then remvome response objectt then only will work>>> but this will not work for printing on console like json body then only use response object
/*
        RestAssured
                .given().body(jsonbody)                   // .header("Content-Type", "application/json")
                .when().post("/users")
                .then().statusCode(400).body("email", equalTo("ritikprajaptii@gmail.com"));
*/