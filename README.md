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
