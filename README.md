Here's a **quick yet in-depth** refresher on **Rest Assured**, covering both **basic and advanced** topics to help you ace your interview.  

---

## **📌 1. Introduction to Rest Assured**
- **Rest Assured** is a Java-based library used to test RESTful APIs.
- It simplifies HTTP requests and response validations using a **fluent API**.

📍 **Maven Dependency**  
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>5.3.1</version>
    <scope>test</scope>
</dependency>
```

📍 **Basic GET Request**  
```java
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class RestAssuredDemo {
    public static void main(String[] args) {
        Response response = given()
            .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
            .then()
            .statusCode(200)
            .extract()
            .response();

        System.out.println(response.asString());
    }
}
```
---
## **📌 2. Advanced Validations & Assertions**
- Use **Hamcrest Matchers** for flexible assertions.  
- Common matchers: `equalTo()`, `containsString()`, `hasSize()`, `hasItem()`, `not()`

📍 **Validating JSON Response**  
```java
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ResponseValidation {
    public static void main(String[] args) {
        given()
        .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .statusCode(200)
            .body("id", equalTo(1))
            .body("title", containsString("sunt aut"));
    }
}
```

📍 **Extracting Values from Response**  
```java
Response response = get("https://jsonplaceholder.typicode.com/posts/1");
String title = response.jsonPath().getString("title");
System.out.println("Title: " + title);
```
---
## **📌 3. Authentication & Authorization**
- **Basic Auth, Bearer Token, OAuth2, Digest Auth**
  
📍 **Basic Authentication**  
```java
given()
    .auth()
    .basic("username", "password")
.when()
    .get("https://api.example.com/protected")
.then()
    .statusCode(200);
```

📍 **Bearer Token Authentication**  
```java
given()
    .header("Authorization", "Bearer YOUR_ACCESS_TOKEN")
.when()
    .get("https://api.example.com/secure-data")
.then()
    .statusCode(200);
```

📍 **OAuth2 Authentication**  
```java
given()
    .auth()
    .oauth2("YOUR_ACCESS_TOKEN")
.when()
    .get("https://api.example.com/userinfo")
.then()
    .statusCode(200);
```
---
## **📌 4. Handling Request Headers, Query Params, and Path Params**
📍 **Headers & Query Parameters**  
```java
given()
    .header("Content-Type", "application/json")
    .queryParam("userId", "1")
.when()
    .get("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(200);
```

📍 **Path Parameters**  
```java
given()
    .pathParam("postId", "1")
.when()
    .get("https://jsonplaceholder.typicode.com/posts/{postId}")
.then()
    .statusCode(200);
```
---
## **📌 5. Handling Different HTTP Methods**
📍 **POST Request**  
```java
String requestBody = "{ \"title\": \"New Post\", \"body\": \"This is a new post.\", \"userId\": 1 }";

given()
    .header("Content-Type", "application/json")
    .body(requestBody)
.when()
    .post("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(201)
    .body("title", equalTo("New Post"));
```

📍 **PUT Request**  
```java
String updateBody = "{ \"id\": 1, \"title\": \"Updated Post\", \"body\": \"Updated content.\", \"userId\": 1 }";

given()
    .header("Content-Type", "application/json")
    .body(updateBody)
.when()
    .put("https://jsonplaceholder.typicode.com/posts/1")
.then()
    .statusCode(200)
    .body("title", equalTo("Updated Post"));
```

📍 **DELETE Request**  
```java
given()
.when()
    .delete("https://jsonplaceholder.typicode.com/posts/1")
.then()
    .statusCode(200);
```
---
## **📌 6. Working with JSON & XML**
📍 **Validating JSON Response Structure**  
```java
given()
.when()
    .get("https://jsonplaceholder.typicode.com/posts/1")
.then()
    .body("$", hasKey("id"))
    .body("$", hasKey("title"));
```

📍 **Parsing XML Response**  
```java
given()
.when()
    .get("https://www.w3schools.com/xml/note.xml")
.then()
    .body("note.to", equalTo("Tove"))
    .body("note.from", equalTo("Jani"));
```
---
## **📌 7. Serialization & Deserialization**
📍 **Using POJOs for Serialization**  
```java
import com.fasterxml.jackson.annotation.JsonProperty;

public class Post {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("body")
    private String body;

    public Post(int userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
}
```

📍 **Sending a Serialized Object in Request**  
```java
Post newPost = new Post(1, "New Post", "Content of the post.");

given()
    .header("Content-Type", "application/json")
    .body(newPost)
.when()
    .post("https://jsonplaceholder.typicode.com/posts")
.then()
    .statusCode(201);
```

📍 **Deserializing Response to POJO**  
```java
Post post = given()
    .when()
    .get("https://jsonplaceholder.typicode.com/posts/1")
    .as(Post.class);

System.out.println("Title: " + post.getTitle());
```
---
## **📌 8. Logging & Debugging**
📍 **Enable Logging for Debugging**  
```java
given()
    .log().all()
.when()
    .get("https://jsonplaceholder.typicode.com/posts/1")
.then()
    .log().body();
```
---
## **📌 9. Integrating with TestNG & JUnit**
📍 **Using TestNG**  
```java
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TestNGExample {
    @Test
    public void testStatusCode() {
        given()
        .when()
            .get("https://jsonplaceholder.typicode.com/posts/1")
        .then()
            .statusCode(200);
    }
}
```
---
## **📌 10. Framework Design with Rest Assured**
- Use **Page Object Model (POM)** for API testing.  
- Maintain a **config.properties** file for URLs & tokens.  
- Use **Rest Assured with Cucumber (BDD approach).**  

 **Data handling between requests** is an important topic in **Rest Assured** testing, especially for real-world scenarios where API calls depend on each other.

---

# **📌 Data Handling Between Requests in Rest Assured**
When testing APIs, you often need to:
- **Extract data from one request** and use it in another request.  
- Handle **dynamic response values** such as IDs, tokens, etc.  
- Use **global variables or test context** for data persistence.  

---

## **🔹 1. Extracting and Passing Data Between Requests**
### **✅ Extracting a Value from Response & Using It in Another Request**
Example: Create a new user, extract the `userId`, and fetch details using that ID.

```java
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class DataPassingExample {
    public static void main(String[] args) {
        // Step 1: Create a user (POST request)
        String requestBody = "{ \"name\": \"John\", \"job\": \"Engineer\" }";

        Response response = given()
            .header("Content-Type", "application/json")
            .body(requestBody)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .extract()
            .response();

        // Extracting user ID from response
        String userId = response.jsonPath().getString("id");
        System.out.println("Extracted User ID: " + userId);

        // Step 2: Use the extracted ID in the next GET request
        given()
            .pathParam("userId", userId)
        .when()
            .get("https://reqres.in/api/users/{userId}")
        .then()
            .statusCode(200)
            .log().body();
    }
}
```
**Key Concept:**  
✅ `extract().response()` – Extracts the entire response.  
✅ `jsonPath().getString("id")` – Extracts a specific field from the response.  

---

## **🔹 2. Using Global Variables for Data Sharing**
For larger frameworks, you might want to **store data globally** for later use.

### **✅ Using Static Variables**
```java
public class TestDataStore {
    public static String userId;  // Global storage
}
```

📍 **Store Data in First Request**
```java
TestDataStore.userId = response.jsonPath().getString("id");
```

📍 **Use the Stored Data in Another Request**
```java
given()
    .pathParam("userId", TestDataStore.userId)
.when()
    .get("https://reqres.in/api/users/{userId}")
.then()
    .statusCode(200);
```

✅ This approach works well in **TestNG & JUnit** where multiple test cases need shared data.

---

## **🔹 3. Using HashMap for Dynamic Data Storage**
Instead of a static variable, you can use **HashMap** to store and retrieve multiple values dynamically.

```java
import java.util.HashMap;
import static io.restassured.RestAssured.*;

public class DataStorageExample {
    static HashMap<String, String> dataStore = new HashMap<>();

    public static void main(String[] args) {
        // Step 1: Create user and store ID in HashMap
        Response response = given()
            .header("Content-Type", "application/json")
            .body("{ \"name\": \"Alice\", \"job\": \"Tester\" }")
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .extract()
            .response();

        dataStore.put("userId", response.jsonPath().getString("id"));

        // Step 2: Use stored ID in GET request
        given()
            .pathParam("userId", dataStore.get("userId"))
        .when()
            .get("https://reqres.in/api/users/{userId}")
        .then()
            .statusCode(200);
    }
}
```

✅ This approach is useful for **storing multiple dynamic values** during test execution.

---

## **🔹 4. Using TestNG `@BeforeMethod` and `@AfterMethod` for Data Setup**
When writing tests in **TestNG**, you might need to **set up test data** before running tests.

```java
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class TestNGDataHandling {
    String userId;

    @BeforeMethod
    public void createUser() {
        Response response = given()
            .header("Content-Type", "application/json")
            .body("{ \"name\": \"Emma\", \"job\": \"Dev\" }")
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201)
            .extract()
            .response();

        userId = response.jsonPath().getString("id");
    }

    @Test
    public void testFetchUser() {
        given()
            .pathParam("userId", userId)
        .when()
            .get("https://reqres.in/api/users/{userId}")
        .then()
            .statusCode(200);
    }
}
```
✅ **TestNG's `@BeforeMethod` ensures** that data is created before each test.

---

## **🔹 5. Data Handling Using External Files (JSON & Excel)**
You might need to **store data in external JSON or Excel files** to use across multiple tests.

### **✅ Reading from a JSON File (External Data Source)**
Use `ObjectMapper` from **Jackson library** to parse JSON files.

📍 **Example: Read data from a JSON file (`user.json`)**
```json
{
    "name": "Mike",
    "job": "Manager"
}
```

📍 **Java Code to Read JSON File**
```java
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.RestAssured.*;

public class ReadJsonData {
    public static void main(String[] args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src/test/resources/user.json");

        // Convert JSON file to Java Object
        User user = objectMapper.readValue(file, User.class);

        // Use data from JSON in API request
        given()
            .header("Content-Type", "application/json")
            .body(user)
        .when()
            .post("https://reqres.in/api/users")
        .then()
            .statusCode(201);
    }
}
```
✅ **Using external JSON files** allows reusability and flexibility in test data handling.

---

# **🔥 Quick Recap: How to Handle Data Between Requests**
| Approach | Use Case |
|----------|----------|
| **Extract & Pass Data** | Extract response values (IDs, tokens) & use them in subsequent requests |
| **Global Variables** | Store values in static variables for later use |
| **HashMap Storage** | Store multiple dynamic values during execution |
| **TestNG `@BeforeMethod`** | Set up test data before test execution |
| **External JSON Handling** | Read/write test data from JSON files |

---

This covers **everything you need** for **data handling between API requests** in **Rest Assured**! 🚀 Let me know if you want **mock interview questions** or **framework design tips**! 🎯



# JKS for SSL certs for two way SSL configured APIs

Below notes for situation where API require certificates for two way SSL connections.
//generate .p12 file
//Copy the cert and pem file to openssl bin location and run below command.ignore the unable to write 'random state' error msg.
			openssl pkcs12 -export -in <<cert.pem>> -inkey <<key.pem>> -certfile <<cert.pem>> -out <<keyAndCertBundle.p12>>
			
note : If openssl command is not working download openssl and set the open.exe path in path variable for open ssl to be recognized globally.

//generate .jks out of .p12 file
//Copy the .p12 file to below location where cert and key are present and run below command
			keytool -importkeystore -srckeystore <<keyAndCertBundle.p12>> -srcstoretype PKCS12 -destkeystore <<destination C:\certs\keyAndCertBundle.jks>>
			

Example
===========
			
//generate .p12 file
openssl pkcs12 -export -in prvfapi.arcot.com.cer.pem -inkey prvfapi.arcot.com.key.pem -certfile prvfapi.arcot.com.cer.pem -out prvf_keyAndCertBundle.p12

//generate .jks out of .p12 file
keytool -importkeystore -srckeystore C:\Users\rg030672\Documents\PaySec-2-KT\CallBackServices\certs\FIS_certs\prvf_keyAndCertBundle.p12 -srcstoretype PKCS12 -destkeystore C:\Users\rg030672\Documents\PaySec-2-KT\CallBackServices\certs\FIS_certs\prvf_keyAndCertBundle.jks
