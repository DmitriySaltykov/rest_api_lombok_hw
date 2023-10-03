package specs;

import io.restassured.specification.RequestLogSpecification;

import static helpers.CustomAllureListener.withCustomTemplates;
import static io.restassured.filter.log.RequestLoggingFilter.with;
import static io.restassured.http.ContentType.JSON;

public class CreateSpec {

    public static RequestLogSpecification  createRequestSpec = with()
            .filter(withCustomTemplates())
            .log().uri()
            .log().method()
            .log().body()
            .contentType(JSON)

}
