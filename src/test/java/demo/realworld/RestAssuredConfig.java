package demo.realworld;

import static io.restassured.RestAssured.baseURI;

public class RestAssuredConfig {

    public static void setup() {
        baseURI = "https://api.realworld.io/api";
    }

}
