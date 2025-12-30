package serviceUtils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public final class CommonResponseSpec {

    private static ResponseSpecification responseSpec;

    private CommonResponseSpec() {
        // Prevent instantiation
    }

    /**
     * Builds and returns a reusable ResponseSpecification
     */
    public static ResponseSpecification getResponseSpec() {

        if (responseSpec == null) {

            responseSpec = new ResponseSpecBuilder()
                    .expectStatusCode(200)
                    .expectContentType(ContentType.JSON)
                    .build();
        }
        return responseSpec;
    }
}

