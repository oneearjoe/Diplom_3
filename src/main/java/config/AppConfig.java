package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class AppConfig {
    public final static String APP_URL = "https://stellarburgers.nomoreparties.site/";

    public static RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(APP_URL)
                .build().log().all();
    }
}
