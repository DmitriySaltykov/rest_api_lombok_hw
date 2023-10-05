package api_lombok_test.test;

import config.ApiConfig;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());
    }
