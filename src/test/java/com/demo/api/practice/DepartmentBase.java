package com.demo.api.practice;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: 曹达
 * @Date: 2021-1-3 10:30
 * @Description: 基础脚本，分别执行了，创建、修改、查询、删除接口并进行了校验
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentBase {
    private static final Logger logger = LoggerFactory.getLogger("DepartmentBase.class");
    public static String accessToken;
    public static String departmentId;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = given().log().all()
                            .when()
                            .param("corpid","ww710dce4b307a5f5a")
                            .param("corpsecret","Es1YJye9q84GrT_Vp74yhaQoM35iDw807U5FlenH87o")
                            .get("https://qyapi.weixin.qq.com/cgi-bin/gettoken")
                            .then().log().all()
                            .extract().response().path("access_token");

    }
    @DisplayName("创建部门")
    @Test
    @Order(1)
    public void createDepartment(){
        String body ="{\n" +
                "   \"name\": \"广州研发中心3\",\n" +
                "   \"name_en\": \"RDGZ3\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "}\n";
        Response response = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = response.path("id").toString();
        logger.info(departmentId);
    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    public void updateDepartment(){
        String body = "{\n" +
                "   \"id\":"+departmentId+",\n" +
                "   \"name\": \"广州研发中心new\",\n" +
                "   \"name_en\": \"RDGZnew\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response response = given().log().all()
                .when()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken)
                .then().log().all()
                .extract().response();
        assertEquals(0,response.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    public void queryDepartment(){
        Response response = given().log().all()
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract()
                .response();
        assertEquals("0",response.path("errcode").toString());

    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    public void deleteDepartment(){
        Response response = given().log().all()
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        assertEquals("0",response.path("errcode").toString());
    }


}
