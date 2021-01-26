package com.demotest;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @Author: 曹达
 * @Date: 2020-12-25 20:20
 * @Description:
 */
public class DemoTest {
    public static String access_token;
    public static String accessToken;
    public static final String CORPID = "ww710dce4b307a5f5a";
    public static final String CORPSECRET ="";
    @Test
    public void fun(){
        given()
                .get("https://www.baidu.com")
                .then()
                .statusCode(200)
                .log().all();

    }

    @Test
    public void getMethod(){
        access_token = given()
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=ww6721ac4d87f5824c&corpsecret=T_BLeVdkmCVxbrFIWGhF_-iKUVIEyhzLuR3-gm1kWHg")
                .then()
                .extract().response().path("access_token");
        System.out.println(access_token);
    }

    @Test
    public void postMethod(){
        given()
                .contentType("application/json;charset=utf-8")
                .body("{\n" +
                        "   \"touser\" : \"@all\",\n" +
                        "   \"msgtype\" : \"text\",\n" +
                        "   \"agentid\" : 1000002,\n" +
                        "   \"text\" : {\n" +
                        "       \"content\" : \"你的快递已到，请携带工卡前往邮件中心领取。\\n出发前可查看<a href=\\\"http://work.weixin.qq.com\\\">邮件中心视频实况</a>，聪明避开排队。\"\n" +
                        "   }" +
                        "}")
                .post(" https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+access_token)
                .then()
                .log().all();
    }

    @Test
    public void testLogin(){
        given()
                .when()
                .get("http://release-uc.dc.servyou-it.com/v1/oauth2.0/authorize?password=1722442b586a85c95593a9c6131a0ebd&loginName=15958040216&appId=19060100010000")
                .then()
                .body("data.accountInfo.accountId",equalTo(127027527))
                .log().all();
    }

    @BeforeMethod
    public void getToken(){
         accessToken = given()
                .when()
                .get("http://release-uc.dc.servyou-it.com/v1/oauth2.0/authorize?password=1722442b586a85c95593a9c6131a0ebd&loginName=15958040216&appId=19060100010000")
                .then()
                .extract().response().path("data.accessToken");
        System.out.println(accessToken);
    }

    @Test
    public void getCompany(){
        given()
                .header("Cookie","uc_session_id="+accessToken)
                .header("X-Requested-With","XMLHttpRequest")
                .when()
                .get("http://release-17dz.dc.servyou-it.com/xqy-portal-web/manage/login/getLoginSessionForClient")
                .then()
                .log().all();
    }

    @Test
    public void queryInvoice(){
        given()
                .header("Cookie","uc_session_id="+accessToken)
                .header("Content-Type","application/json")
                .body("{\"invoiceId\":528345,\"invoiceClass\":\"8\",\"companyId\":1297718870160}")
                .when()
                .post("http://release-17dz.dc.servyou-it.com/invoice-web/invoice/input/general/querySingle")
                .then()
                .log().all();
    }



}
