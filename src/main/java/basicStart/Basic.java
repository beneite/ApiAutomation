package basicStart;

import io.restassured.RestAssured;
import org.testng.Assert;
import payLoads.Payload;
import utils.Utilities;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {
    public static void main(String[] args){

        Utilities util = new Utilities();
        // given , when, then

        //#1. Adding the address
        RestAssured.baseURI = "https://rahulshettyacademy.com/";
        String addApiResponse = given().log().all()
                .queryParam("Key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlacePayload())
                .when()
                .post("/maps/api/place/add/json")
                .then().assertThat().statusCode(200)
                .body("scope", equalTo("APP"))
                .header("Server", "Apache/2.4.52 (Ubuntu)")
                .extract().response().asString();

        System.out.println("Response:"+addApiResponse);

        String placeId = util.getObjectValueFromJson(util.stringToJson(addApiResponse),"place_id");
        System.out.println("Place id:"+placeId);

        //#2. updating the address
        String addApiResponseUpdate = given().log().all()
                .queryParam("Key","qaclick123")
                .header("Content-Type","application/json")
                .body(Payload.updatePlacePayload(placeId, Payload.Address.MUMBAI_ADDRESS.locationAddress))
                .when().put("maps/api/place/update/json").then().assertThat().statusCode(200)
                .body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();

        System.out.println("Update Response:"+addApiResponseUpdate);

        //#3. validating the updated address- get
        String getExistingAddress = given().log().all()
                .queryParam("place_id",placeId)
                .queryParam("key","qaclick123")
                .when()
                .get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200)
                .extract().response().asString();

        System.out.println("get Response:"+getExistingAddress);


        String addressFromJson = util.getObjectValueFromJson(util.stringToJson(getExistingAddress),"address");
        System.out.println("Address :"+addressFromJson);

        Assert.assertEquals(addressFromJson,Payload.Address.MUMBAI_ADDRESS.locationAddress,"The address doe not match");





    }
}
