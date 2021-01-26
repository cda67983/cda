package com.demo.api.practice.apiobject;

import io.restassured.response.Response;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;

/**
 * @Author: 曹达
 * @Date: 2021-1-3 19:40
 * @Description:
 */
public class ClearDepartmentHelper {
    public static void clearDepartment(String accessToken){
        Response listResponse =given().log().all()
                .when()
                .param("id",1)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+accessToken)
                .then()
                .log().body()
                .extract()
                .response();
        ArrayList<Integer> departmentIdList = listResponse.path("department.id");
        for(int departmentId : departmentIdList){
            if(1==departmentId){
                continue;
            }
            Response DelResponse = given().log().all()
                    .contentType("application/json")
                    .param("access_token",accessToken)
                    .param("id",departmentId)
                    .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                    .then()
                    .log().body()
                    .extract().response();
        }

    }

}
