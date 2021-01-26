package com.demo.api.practice.apiobject;

import com.demo.api.util.RandomUtils;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * @Author: 曹达
 * @Date: 2021-1-3 19:26
 * @Description:
 */
public class DepartmentService {

    public static Response createDepartment(String name,String eName,String accessToken) {
        String body = "{\n" +
                "   \"name\": \"" + name + "\",\n" +
                "   \"name_en\": \"" + eName + "\",\n" +
                "   \"parentid\": 1}";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token=" + accessToken)
                .then().log().all()
                .extract()
                .response();
        returnRequest(body);
        returnResponse(response.asString());

        return response;
    }

    public static String createDepartment(String accessToken){
        String name = "测试团队"+ RandomUtils.getTimeStamp();
        String eName = "test"+RandomUtils.getTimeStamp();
        Response creatResponse = createDepartment(name,eName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
        returnRequest("name:"+name+"eName:"+eName+"accessToken:"+accessToken);
        returnResponse(creatResponse.asString());
        return departmentId;
    }


    public static String createDepartMentByRandomInt(String accessToken){
        String name = "测试团队"+ RandomUtils.getRandomInt(1000);
        String eName = "test"+RandomUtils.getRandomInt(1000);
        Response creatResponse = createDepartment(name,eName,accessToken);
        String departmentId= creatResponse.path("id")!=null ? creatResponse.path("id").toString():null;
        returnRequest("name:"+name+"eName:"+eName+"accessToken:"+accessToken);
        returnResponse(creatResponse.asString());
        return departmentId;
    }

    public static Response updateDepartment(String departmentId, String updateName, String updateEnName, String accessToken) {
        String updateBody = "{\n" +
                "   \"id\": " + departmentId + ",\n" +
                "   \"name\": \"" + updateName + "\",\n" +
                "   \"name_en\": \"" + updateEnName + "\",\n" +
                "   \"order\": 1\n" +
                "}\n";
        Response updateResponse = given().log().all()
                .when()
                .contentType("application/json")
                .body(updateBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token=" + accessToken)
                .then().log().all()
                .extract().response();
        returnRequest(updateBody);
        returnResponse(updateResponse.asString());
        return updateResponse;
    }

    public static Response queryDepartment(String accessToken,String departmentId){
        Response queryResponse = given().log().all()
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract()
                .response();
        returnRequest("access_token:"+accessToken+"id:"+departmentId);
        returnResponse(queryResponse.asString());
        return queryResponse;
    }

    public static Response deleteDepartment(String accessToken,String departmentId){
        Response deleteResponse = given().log().all()
                .param("access_token", accessToken)
                .param("id", departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        returnRequest("access_token:"+accessToken+"id:"+departmentId);
        returnResponse(deleteResponse.asString());
        return deleteResponse;
    }


    @Step
    public static void returnRequest(String params){
    }

    @Step
    public static void returnResponse(String body){
    }

}
