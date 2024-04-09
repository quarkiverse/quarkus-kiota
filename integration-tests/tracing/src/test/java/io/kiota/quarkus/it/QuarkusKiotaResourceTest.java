package io.kiota.quarkus.it;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;

@QuarkusTest
public class QuarkusKiotaResourceTest {

    @BeforeEach
    @AfterEach
    void reset() {
        given().get("/reset").then().statusCode(HTTP_OK);
        await().atMost(5, SECONDS).until(() -> getSpans().size() == 0);
    }

    private List<Map<String, Object>> getSpans() {
        return get("/export").body().as(new TypeRef<>() {
        });
    }

    @Test
    public void testHelloEndpoint() {
        given().when()
                .get("/quarkus-kiota2")
                .then()
                .statusCode(200)
                .body(is("{\"value\":\"Hello quarkus-kiota myself\"}"));

        await().atMost(Duration.ofSeconds(30)).until(() -> getSpans().size() > 2);
        Map<String, Object> clientSpanData = getSpans().stream().filter(span -> span.get("kind").equals("CLIENT")).findAny()
                .get();
        assertNotNull(clientSpanData);
        assertNotNull(clientSpanData.get("spanId"));

        Map<String, Object> clientAttributes = (Map<String, Object>) clientSpanData.get("attributes");
        assertNotNull(clientAttributes);

        assertEquals("GET", clientAttributes.get("http.method"));
        assertEquals("http://localhost:8081/quarkus-kiota?name=myself", clientAttributes.get("http.url"));

        Map<String, Object> serverSpanData = getSpans().stream().filter(span -> span.get("kind").equals("SERVER")).findAny()
                .get();
        assertNotNull(serverSpanData);
        assertNotNull(serverSpanData.get("spanId"));

        Map<String, Object> serverAttributes = (Map<String, Object>) serverSpanData.get("attributes");
        assertNotNull(serverAttributes);

        assertTrue(
                serverAttributes.get("user_agent.original").toString().toLowerCase(Locale.ROOT).contains("vert.x-webclient"));
        assertEquals("/quarkus-kiota?name=myself", serverAttributes.get("http.target"));

        assertEquals(serverSpanData.get("parentSpanId"), clientSpanData.get("spanId"));
    }

}
