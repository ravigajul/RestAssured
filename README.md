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

### **📌 Understanding Hamcrest Matchers in Rest Assured**
Hamcrest is a **framework for writing flexible and readable assertions**, often used in **Rest Assured** for API testing. It provides a rich set of matchers to validate **API responses** effectively.

---
## **🔹 Why Use Hamcrest Matchers in Rest Assured?**
✅ **Readable & Expressive Assertions** – Code looks more natural and easy to understand.  
✅ **Powerful Validation** – Supports **logical conditions, collections, text, numeric checks, and JSON parsing.**  
✅ **Reduces Boilerplate Code** – No need for manual assertions like `if-else`.  

---
## **🔹 Basic Hamcrest Matchers**
| Matcher | Purpose | Example |
|---------|---------|---------|
| `equalTo(x)` | Exact match | `body("id", equalTo(10))` |
| `not(x)` | Negation | `body("status", not("error"))` |
| `containsString(x)` | String contains | `body("message", containsString("success"))` |
| `startsWith(x)` | String starts with | `body("email", startsWith("test"))` |
| `endsWith(x)` | String ends with | `body("url", endsWith(".com"))` |
| `hasItem(x)` | Collection contains one element | `body("names", hasItem("John"))` |
| `hasItems(x, y, z)` | Collection contains multiple elements | `body("names", hasItems("John", "Doe"))` |
| `everyItem(x)` | Condition on every item | `body("ages", everyItem(greaterThan(18)))` |
| `greaterThan(x)` | Number is greater than | `body("age", greaterThan(18))` |
| `lessThan(x)` | Number is less than | `body("score", lessThan(100))` |
| `greaterThanOrEqualTo(x)` | Number is greater than or equal | `body("score", greaterThanOrEqualTo(50))` |
| `lessThanOrEqualTo(x)` | Number is less than or equal | `body("score", lessThanOrEqualTo(90))` |
| `notNullValue()` | Check if value is not null | `body("id", notNullValue())` |
| `nullValue()` | Check if value is null | `body("deletedAt", nullValue())` |

---
## **🔹 Examples of Hamcrest Matchers in Rest Assured**

### **✅ 1. Basic Assertions**
#### **Validate that the response contains an exact value**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .statusCode(200)
        .body("id", equalTo(1))
        .body("userId", equalTo(1))
        .body("title", notNullValue());
```

---
### **✅ 2. String Validations**
#### **Check if a string contains a specific word**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body("title", containsString("sunt aut facere"));
```

#### **Check if a string starts or ends with a specific value**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body("title", startsWith("sunt"))
        .body("title", endsWith("magnam"));
```

---
### **✅ 3. Collection Validations**
#### **Verify that a response contains an item in an array**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .body("username", hasItem("Bret"));
```

#### **Verify multiple values exist in an array**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .body("username", hasItems("Bret", "Antonette", "Samantha"));
```

#### **Validate every item in a list satisfies a condition**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .body("id", everyItem(greaterThan(0)));
```

---
### **✅ 4. Numeric Validations**
#### **Check if a number is greater or less than a value**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body("id", greaterThan(0))
        .body("id", lessThan(100));
```

#### **Validate if a value is within a range**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body("id", greaterThanOrEqualTo(1))
        .body("id", lessThanOrEqualTo(100));
```

---
### **✅ 5. Logical Matchers**
#### **Combine multiple conditions using `not()`**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body("id", not(equalTo(0)))
        .body("title", not(containsString("error")));
```

---
### **✅ 6. Validate JSON Array Size**
#### **Check if the response has exactly 10 items**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .body("size()", equalTo(10));
```

---
### **✅ 7. Validate JSON Schema Using Hamcrest**
```java
import static io.restassured.module.jsv.JsonSchemaValidator.*;
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body(matchesJsonSchemaInClasspath("post-schema.json"));
```

---
## **🔥 Summary**
| Matcher | Use Case | Example |
|---------|---------|---------|
| `equalTo(x)` | Exact match | `"id", equalTo(1)` |
| `not(x)` | Negation | `"status", not("error")` |
| `containsString(x)` | String contains | `"message", containsString("success")` |
| `hasItem(x)` | List contains | `"names", hasItem("John")` |
| `everyItem(x)` | Every item matches | `"ages", everyItem(greaterThan(18))` |
| `greaterThan(x)` | Numeric validation | `"age", greaterThan(18)` |
| `notNullValue()` | Check not null | `"id", notNullValue()` |

---
## **🔥 Advanced Rest Assured Scenarios with Hamcrest Matchers**

Here are some **advanced API testing scenarios** using **Hamcrest matchers** in **Rest Assured** 

---

## **🔥 Scenario 1: Validate Complex JSON Responses with Deep Nesting**
**🔹 Task:** Verify the latitude and longitude in a nested JSON response for a specific user.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users/3")
    .then()
        .statusCode(200)
        .body("address.geo.lat", equalTo("-68.6102"))
        .body("address.geo.lng", equalTo("-47.0653"));
```
✅ **`geo.lat` and `geo.lng` are deeply nested elements in the response JSON.**

---

## **🔥 Scenario 2: Validate JSON Array Elements Using `anyOf()` and `allOf()`**
**🔹 Task:** Verify that the response contains at least **one of** `"Bret"`, `"Antonette"`, or `"RandomUser"` as usernames.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .body("username", anyOf(hasItem("Bret"), hasItem("Antonette"), hasItem("RandomUser")));
```
✅ **`anyOf()` ensures that at least one condition is met.**  
✅ **Use `allOf()` if you need to check for multiple conditions together.**

---

## **🔥 Scenario 3: Extract JSON Response and Perform Advanced Validations**
**🔹 Task:** Extract a user's **email** from the API response and validate its format.

### **✅ Solution:**
```java
import java.util.regex.Pattern;

Response response = given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .statusCode(200)
        .extract()
        .response();

String email = response.jsonPath().getString("[0].email");

// Validate email format using regex
assertThat(email, matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"));
```
✅ **Extracts email dynamically and validates using regex with `matchesPattern()`**

---

## **🔥 Scenario 4: Validate Response Using Logical Matchers**
**🔹 Task:** Verify that the user **ID** is between `1` and `10`.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users/1")
    .then()
        .body("id", allOf(greaterThanOrEqualTo(1), lessThanOrEqualTo(10)));
```
✅ **Ensures `id` is within the range `[1,10]`.**

---

## **🔥 Scenario 5: Validate API Responses with Custom Timeout**
**🔹 Task:** Ensure that the response **time is less than 1 second**.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts")
    .then()
        .time(lessThan(1000L)); // Time in milliseconds
```
✅ **Crucial for performance testing in API validation.**

---

## **🔥 Scenario 6: Extract and Pass Data Between API Calls**
**🔹 Task:** Fetch the **ID of the first user** and use it to get **that user’s posts**.

### **✅ Solution:**
```java
int userId = given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users")
    .then()
        .extract()
        .path("[0].id");

given()
    .queryParam("userId", userId)
    .when()
        .get("https://jsonplaceholder.typicode.com/posts")
    .then()
        .body("userId", everyItem(equalTo(userId)));
```
✅ **Dynamic API chaining for real-world scenarios.**

---

## **🔥 Scenario 7: Validate API Response When a Key is Missing**
**🔹 Task:** Verify that the `deletedAt` field is **not present** in the response.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/users/1")
    .then()
        .body("$", not(hasKey("deletedAt")));
```
✅ **Ensures that `"deletedAt"` is missing in the JSON response.**

---

## **🔥 Scenario 8: Validate API with Multi-Query Parameters**
**🔹 Task:** Fetch **posts for user ID `1` and filter for titles containing "qui"**.

### **✅ Solution:**
```java
given()
    .queryParam("userId", 1)
    .queryParam("title_like", "qui") // Filters posts containing "qui" in the title
    .when()
        .get("https://jsonplaceholder.typicode.com/posts")
    .then()
        .statusCode(200)
        .body("userId", everyItem(equalTo(1)))
        .body("title", everyItem(containsString("qui")));
```
✅ **Real-world filtering of API responses using query parameters.**

---

## **🔥 Scenario 9: Validate Response Headers**
**🔹 Task:** Ensure the response has `Content-Type` as `application/json`.

### **✅ Solution:**
```java
given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .header("Content-Type", equalTo("application/json; charset=utf-8"));
```
✅ **Validates that the response header matches expectations.**

---

## **🔥 Scenario 10: Validate Response Against Predefined JSON Schema**
**🔹 Task:** Ensure the response matches the expected **JSON schema**.

### **✅ Solution:**
```java
import static io.restassured.module.jsv.JsonSchemaValidator.*;

given()
    .when()
        .get("https://jsonplaceholder.typicode.com/posts/1")
    .then()
        .body(matchesJsonSchemaInClasspath("post-schema.json"));
```
✅ **Ensures API consistency by validating against a predefined schema.**  
✅ **JSON schema files are placed in the `resources` folder.**

---

## **🚀 Summary of Advanced Hamcrest Matchers in Rest Assured**
| Matcher | Purpose | Example |
|---------|---------|---------|
| `hasKey(x)` | Validate key exists | `body("$", hasKey("email"))` |
| `not(hasKey(x))` | Validate key is missing | `body("$", not(hasKey("deletedAt")))` |
| `anyOf(x, y, z)` | Any condition should pass | `body("username", anyOf(hasItem("Bret"), hasItem("Samantha")))` |
| `allOf(x, y, z)` | All conditions should pass | `body("id", allOf(greaterThan(0), lessThan(100)))` |
| `matchesPattern(x)` | Validate regex | `assertThat(email, matchesPattern("^[A-Za-z0-9+_.-]+@(.+)$"));` |
| `time(lessThan(x))` | Validate response time | `.time(lessThan(1000L))` |
| `matchesJsonSchemaInClasspath(x)` | Validate schema | `.body(matchesJsonSchemaInClasspath("schema.json"))` |

---
# **🔥 Real-Time API Security Testing with Rest Assured 🔥**  

API security is **critical** in preventing **unauthorized access, data leaks, and vulnerabilities**. Here are some **real-world API security tests** you can perform using **Rest Assured** and other tools.

---

## **✅ 1. Test Unauthorized Access (No Auth)**
🔹 **Task:** Ensure that endpoints **requiring authentication** return `401 Unauthorized`.  

### **✅ Solution**
```java
given()
    .when()
        .get("https://api.example.com/secure-data")
    .then()
        .statusCode(401); // Unauthorized
```
✅ **Confirms that users without authentication cannot access secure endpoints.**

---

## **✅ 2. Test Incorrect Auth Credentials**
🔹 **Task:** Verify that using incorrect credentials returns `403 Forbidden`.  

### **✅ Solution**
```java
given()
    .auth().basic("invalidUser", "wrongPassword")
.when()
    .get("https://api.example.com/secure-data")
.then()
    .statusCode(403); // Forbidden
```
✅ **Ensures the API correctly rejects invalid login attempts.**

---

## **✅ 3. Test Rate Limiting (Too Many Requests)**
🔹 **Task:** Ensure the API enforces a request limit (e.g., `5 requests/sec`) and returns `429 Too Many Requests`.  

### **✅ Solution**
```java
import java.util.concurrent.*;

void testRateLimit() throws InterruptedException {
    var executor = Executors.newFixedThreadPool(10);

    for (int i = 0; i < 10; i++) {
        executor.submit(() -> {
            given()
            .when()
                .get("https://api.example.com/limited-endpoint")
            .then()
                .statusCode(anyOf(equalTo(200), equalTo(429))); // 429 = Too Many Requests
        });
    }

    executor.shutdown();
    executor.awaitTermination(5, TimeUnit.SECONDS);
}
```
✅ **Confirms the API handles excessive requests correctly.**

---

## **✅ 4. Test SQL Injection Protection**
🔹 **Task:** Ensure that SQL Injection attacks **don’t work**.  

### **✅ Solution**
```java
given()
    .queryParam("username", "' OR '1'='1") // SQL Injection attempt
.when()
    .get("https://api.example.com/user")
.then()
    .statusCode(anyOf(equalTo(400), equalTo(403))); // Bad Request or Forbidden
```
✅ **Prevents unauthorized data access via SQL injection.**

---

## **✅ 5. Test Cross-Site Scripting (XSS) Protection**
🔹 **Task:** Ensure the API **sanitizes inputs** and prevents XSS.  

### **✅ Solution**
```java
given()
    .queryParam("input", "<script>alert('Hacked')</script>")
.when()
    .post("https://api.example.com/comments")
.then()
    .statusCode(anyOf(equalTo(400), equalTo(403))) // Expecting rejection
    .body(not(containsString("<script>")));
```
✅ **Ensures the API removes malicious scripts.**

---

## **✅ 6. Test Cross-Origin Resource Sharing (CORS) Policies**
🔹 **Task:** Ensure the API blocks unauthorized **cross-origin** requests.  

### **✅ Solution**
```java
given()
    .header("Origin", "http://malicious-site.com")
.when()
    .options("https://api.example.com/data")
.then()
    .header("Access-Control-Allow-Origin", not("http://malicious-site.com"));
```
✅ **Ensures API only allows trusted domains to access it.**

---

## **✅ 7. Test Session Hijacking & CSRF Protection**
🔹 **Task:** Ensure **cookies or CSRF tokens** are required.  

### **✅ Solution**
```java
given()
    .cookie("session_id", "fake_session")
.when()
    .get("https://api.example.com/user-info")
.then()
    .statusCode(403); // Expect rejection
```
✅ **Prevents attackers from hijacking a session.**

---

## **✅ 8. Test Sensitive Data Exposure**
🔹 **Task:** Ensure API does **not leak sensitive data** like passwords.  

### **✅ Solution**
```java
given()
    .when()
        .get("https://api.example.com/user/1")
    .then()
        .body("$", not(hasKey("password")))  // Ensures password is not exposed
        .body("$", not(hasKey("creditCard")));
```
✅ **Prevents security risks due to data leaks.**

---

## **✅ 9. Test Weak API Keys & JWT Tokens**
🔹 **Task:** Ensure invalid tokens **fail** authentication.  

### **✅ Solution**
```java
given()
    .header("Authorization", "Bearer weak_token_123")
.when()
    .get("https://api.example.com/protected")
.then()
    .statusCode(401); // Unauthorized
```
✅ **Validates token security.**

---

## **✅ 10. Test API with Expired Tokens**
🔹 **Task:** Ensure expired tokens **fail** authentication.  

### **✅ Solution**
```java
given()
    .header("Authorization", "Bearer expired_token")
.when()
    .get("https://api.example.com/protected")
.then()
    .statusCode(401); // Unauthorized
```
✅ **Ensures expired tokens are rejected.**

---

## **🚀 Summary of API Security Tests**
| **Test Scenario** | **Expected Outcome** |
|-------------------|---------------------|
| No Auth | `401 Unauthorized` |
| Invalid Credentials | `403 Forbidden` |
| Rate Limiting | `429 Too Many Requests` |
| SQL Injection | `400 Bad Request` |
| XSS Injection | HTML tags sanitized |
| CORS Protection | Unauthorized origins blocked |
| CSRF Protection | Invalid sessions rejected |
| Sensitive Data Exposure | Passwords, credit card info not exposed |
| Weak JWT Tokens | `401 Unauthorized` |
| Expired Tokens | `401 Unauthorized` |

---



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
