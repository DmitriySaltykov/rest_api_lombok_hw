package api_lombok_test.test;

import config.ApiConfig;

import org.aeonbits.owner.ConfigFactory;


public class TestBase {
    protected static final ApiConfig config = ConfigFactory.create(ApiConfig.class, System.getProperties());
    }
