/**
 * using collections : src/main/resources/Library+API.postman_collection.json
 * API contract doc: src/main/resources/Library+API.docx
 */
package dynamicJsonHandling;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import payLoads.Payload;
import utils.Utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DynamicJsonPayload extends Payload{

    Utilities util = new Utilities();
    @Test(dataProvider = "booksToAdd", description = "Feeding the data through String")
    public void addBook(String bookName, String isbn, String asile, String authorName){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addBookResponse = given().header("Content-Type","application/json")
                .body(Payload.addNewBookDetails(bookName,isbn,asile,authorName))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).log().all().extract().response().asString();
        String newBookId = util.getObjectValueFromJson(util.stringToJson(addBookResponse), "ID");
        System.out.println("newBookId: "+newBookId);
    }

    @Test(description = "Feeding the data through *.json file")
    public void addBookViaJsonFile() throws IOException {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String addBookResponse = given().header("Content-Type","application/json")
                .body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/java/basicStart/newBook.json"))))
                .when().post("Library/Addbook.php")
                .then().assertThat().statusCode(200).body("Msg", equalTo("successfully added")).log().all().extract().response().asString();
        String newBookId = util.getObjectValueFromJson(util.stringToJson(addBookResponse), "ID");
        System.out.println("newBookId: "+newBookId);
    }
}
