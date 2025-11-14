package org.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.example.apis.TokenApi;
import org.example.base.BaseApi;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import pojo.CreateTokenPojo;

public class BaseTest {

    protected RequestSpecification requestSpec;
    protected String token;

    @BeforeClass
    public void setup() {
        requestSpec = BaseApi.getRequestSpec();
        RestAssured.baseURI = BaseApi.BASE_URL;

        System.out.println("Generating auth token before running tests...");

        CreateTokenPojo tokenData = new CreateTokenPojo();
        tokenData.setUsername("admin");
        tokenData.setPassword("password123");

        TokenApi tokenApi = new TokenApi();
        Response tokenResponse = tokenApi.createToken(tokenData);

        Assert.assertEquals(tokenResponse.statusCode(), 200, "Token generation failed!");
        token = tokenResponse.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token is null!");

        System.out.println("Token Generated Successfully: " + token);
    }
}