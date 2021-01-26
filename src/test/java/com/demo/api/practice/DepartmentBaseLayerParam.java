package com.demo.api.practice;

import com.demo.api.practice.apiobject.ClearDepartmentHelper;
import com.demo.api.practice.apiobject.DepartmentService;
import com.demo.api.practice.apiobject.TokenHelper;
import com.demo.api.util.RandomUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: 曹达
 * @Date: 2021-1-3 10:30
 * @Description: 进行了优化，对脚本进行了分层，减少了重复代码，提高了代码复用率，并减少了维护成本
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentBaseLayerParam {
    private static final Logger logger = LoggerFactory.getLogger("DepartmentBase.class");
    public static String accessToken;
    public static String departmentId;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

//    @AfterEach
//    @BeforeEach
    public void clearDepartment(){
        ClearDepartmentHelper.clearDepartment(accessToken);
    }

    @DisplayName("创建部门")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void createDepartment(String name,String eName,String returnCode){
        Response response = DepartmentService.createDepartment(name,eName,accessToken);
        assertEquals("0",response.path("errcode").toString());
    }

    @DisplayName("修改部门")
    @Test
    @Order(2)
    public void updateDepartment(){
        String name = "测试团队"+ RandomUtils.getTimeStamp();
        String eName = "test"+RandomUtils.getTimeStamp();
        String departmentId = DepartmentService.createDepartment(accessToken);
        Response response = DepartmentService.updateDepartment(departmentId,name,eName,accessToken);
        assertEquals("0",response.path("errcode").toString());
    }

    @DisplayName("查询部门")
    @Test
    @Order(3)
    public void queryDepartment(){
        String departmentId = DepartmentService.createDepartment(accessToken);
        Response queryResponse = DepartmentService.queryDepartment(accessToken,departmentId);
        assertEquals("0",queryResponse.path("errcode").toString());
        assertEquals(departmentId,queryResponse.path("department.id[0]").toString());
    }

    @DisplayName("删除部门")
    @Test
    @Order(4)
    public void deleteDepartment(){
        String departmentId = DepartmentService.createDepartment(accessToken);
        Response deleteResponse = DepartmentService.deleteDepartment(accessToken,departmentId);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }

}
