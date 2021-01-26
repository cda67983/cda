package com.demo.api.practice;

import com.demo.api.util.RandomUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: 曹达
 * @Date: 2021-1-3 10:30
 * @Description: 在基础脚本进行了优化，方法间进行解耦，每个方法可以独立行,且使用时间戳命名法避免入参重复造成的报错
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentBase3 {
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

    @AfterEach
    @BeforeEach
    void clearDepartment(){
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
                    .extract().response()
                    ;
        }
    }

    @DisplayName("创建部门")
    @Test
    @Order(1)
    public void createDepartment(){
        String name = "测试团队"+ RandomUtils.getTimeStamp();
        String eName = "test"+RandomUtils.getTimeStamp();
        String body ="{\n" +
                "   \"name\": \""+name+"\",\n" +
                "   \"name_en\": \""+eName+"\",\n" +
                "   \"parentid\": 1}";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(body)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        departmentId = createResponse.path("id") !=null ? createResponse.path("id").toString():null;
        logger.info(departmentId);
    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    public void updateDepartment(){
        String createBody ="{\n" +
                "   \"name\": \"广州研发中心3\",\n" +
                "   \"name_en\": \"RDGZ3\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "}\n";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") !=null ? createResponse.path("id").toString():null;

        String modifyBody = "{\n" +
                "   \"id\":"+departmentId+",\n" +
                "   \"name\": \"广州研发中心new\",\n" +
                "   \"name_en\": \"RDGZnew\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1\n" +
                "}";
        Response updateResponse = given().log().all()
                .when()
                .contentType("application/json")
                .body(modifyBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken)
                .then().log().all()
                .extract().response();
        assertEquals(0,updateResponse.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    public void queryDepartment(){
        String createBody ="{\n" +
                "   \"name\": \"广州研发中心3\",\n" +
                "   \"name_en\": \"RDGZ3\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "}\n";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") !=null ? createResponse.path("id").toString():null;
        Response queryResponse = given().log().all()
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/list")
                .then().log().all()
                .extract()
                .response();
        assertEquals("0",queryResponse.path("errcode").toString());

    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    public void deleteDepartment(){
        String createBody ="{\n" +
                "   \"name\": \"广州研发中心3\",\n" +
                "   \"name_en\": \"RDGZ3\",\n" +
                "   \"parentid\": 1,\n" +
                "   \"order\": 1,\n" +
                "}\n";
        final Response createResponse = given().log().all()
                .contentType("application/json")
                .body(createBody)
                .post("https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken)
                .then().log().all()
                .extract()
                .response();
        String departmentId = createResponse.path("id") !=null ? createResponse.path("id").toString():null;

        Response deleteResponse = given().log().all()
                .param("access_token",accessToken)
                .param("id",departmentId)
                .get("https://qyapi.weixin.qq.com/cgi-bin/department/delete")
                .then().log().all()
                .extract().response();
        assertEquals("0",deleteResponse.path("errcode").toString());
    }


}
