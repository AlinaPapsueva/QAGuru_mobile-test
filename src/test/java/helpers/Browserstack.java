package helpers;

import config.AndroidBrowserstackConfig;
import org.aeonbits.owner.ConfigFactory;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    // https://qaguruqaguru_cWEn5u:JrvHcugRGP42jAPdVsop@api.browserstack.com/app-automate/sessions/0bbf61520103a3f9ab6ec22b79d8d0208c2c6ff8.json

    public static AndroidBrowserstackConfig config = ConfigFactory.create(AndroidBrowserstackConfig.class);

    public static String getVideoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .log().all()
                .filter(withCustomTemplates())
                .auth().basic(config.getUser(),
                        config.getKey())
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract().path("automation_session.video_url");
    }
}
