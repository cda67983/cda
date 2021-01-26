package com.demo.api.practice.apiobject;

import static io.restassured.RestAssured.given;

/**
 * @Author: 曹达
 * @Date: 2021-1-3 19:14
 * @Description:
 */
public class TokenHelper {
    public static String getAccessToken(){
        String accessToken = given().log().all()
                .when()
                .param("corpid","ww710dce4b307a5f5a")
                .param("corpsecret","Es1YJye9q84GrT_Vp74yhaQoM35iDw807U5FlenH87o")
                .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                .then().log().all()
                .extract().response().path("access_token");
        DepartmentService.returnResponse(accessToken);
        return accessToken;
    }
}
