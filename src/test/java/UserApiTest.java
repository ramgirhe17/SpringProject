import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserApiTest {
	
	private String headerRollnumber = "240340820039";
	
    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://bfhldevapigw.healthrx.co.in";
    }

    @Test
    public void testCreateUserSuccess() {
        // Test Case 1: Create a user successfully
        Response response = given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test1\", \"lastName\": \"User\", \"phoneNumber\": 1234567890, \"emailId\": \"test1.user@test.com\" }")
                .when()
                .post("/automation-campus/create/user");

        response.then().statusCode(201); 
    }

    @Test
    public void testCreateUserWithDuplicatePhoneNumber() {
        // Test Case 2: Duplicate phone number should return 400 Bad Request
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test2\", \"lastName\": \"User\", \"phoneNumber\": 1234567890, \"emailId\": \"test2.user@test.com\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Phone number already exists"));
    }

    @Test
    public void testCreateUserWithDuplicateEmailId() {
        // Test Case 3: Duplicate email ID should return 400 Bad Request
        given()
                .header("roll-number",headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test3\", \"lastName\": \"User\", \"phoneNumber\": 9876543210, \"emailId\": \"test1.user@test.com\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Email ID already exists"));
    }

    @Test
    public void testCreateUserWithoutRollNumberHeader() {
        // Test Case 4: Missing roll-number header should return 401 Unauthorized
        given()
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test4\", \"lastName\": \"User\", \"phoneNumber\": 1112223333, \"emailId\": \"test4.user@test.com\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(401)
                .body("message", equalTo("Unauthorized"));
    }

    @Test
    public void testCreateUserWithoutMandatoryFields() {
        // Test Case 5: Missing mandatory fields should return 400 Bad Request
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"\", \"lastName\": \"\", \"phoneNumber\": null, \"emailId\": \"\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Missing or invalid fields"));
    }

    @Test
    public void testCreateUserWithInvalidPhoneNumberFormat() {
        // Test Case 6: Invalid phone number format should return 400 Bad Request
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test5\", \"lastName\": \"User\", \"phoneNumber\": \"invalidNumber\", \"emailId\": \"test5.user@test.com\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid phone number format"));
    }

    @Test
    public void testCreateUserWithInvalidEmailFormat() {
        // Test Case 7: Invalid email format should return 400 Bad Request
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test6\", \"lastName\": \"User\", \"phoneNumber\": 1234567891, \"emailId\": \"invalidEmail\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Invalid email format"));
    }

    @Test
    public void testCreateUserWithEmptyBody() {
        // Test Case 8: Empty body should return 400 Bad Request
        given()
                .header("roll-number",headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{}")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Missing or invalid fields"));
    }

    @Test
    public void testCreateUserWithExtraFields() {
        // Test Case 9: Extra fields should be ignored and return 201 Created
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": \"Test7\", \"lastName\": \"User\", \"phoneNumber\": 1234567892, \"emailId\": \"test7.user@test.com\", \"extraField\": \"extraValue\" }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(201); // Assuming 201 for a successful creation
    }

    @Test
    public void testCreateUserWithNullValues() {
        // Test Case 10: Fields with null values should return 400 Bad Request
        given()
                .header("roll-number", headerRollnumber)
                .contentType(ContentType.JSON)
                .body("{ \"firstName\": null, \"lastName\": null, \"phoneNumber\": null, \"emailId\": null }")
                .when()
                .post("/automation-campus/create/user")
                .then()
                .statusCode(400)
                .body("message", equalTo("Missing or invalid fields"));
    }
}
