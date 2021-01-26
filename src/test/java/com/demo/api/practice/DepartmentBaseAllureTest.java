package com.demo.api.practice;

import com.demo.api.practice.apiobject.ClearDepartmentHelper;
import com.demo.api.practice.apiobject.DepartmentService;
import com.demo.api.practice.apiobject.TokenHelper;
import com.demo.api.util.RandomUtils;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: 曹达
 * @Date: 2021-1-3 10:30
 * @Description: 进行了优化，对脚本进行了分层，减少了重复代码，提高了代码复用率，并减少了维护成本
 */
@Epic("Epic企业微信接口测试用例")
@Feature("Feature部门相关功能测试")
public class DepartmentBaseAllureTest {
    private static final Logger logger = LoggerFactory.getLogger("DepartmentBaseAllureTest.class");
    public static String accessToken;
    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @AfterEach
    @BeforeEach
    public void clearDepartment(){
        ClearDepartmentHelper.clearDepartment(accessToken);
    }

    @Description("Description这个测试方法会测试创建部门的功能-入参数据驱动")
    @DisplayName("创建部门")
    @Story("Story创建部门测试")
    @ParameterizedTest
    @CsvFileSource(resources = "/data/createDepartment.csv",numLinesToSkip = 1)
    public void createDepartment(String name,String eName,String returnCode){
        Response response = DepartmentService.createDepartment(name,eName,accessToken);
        assertEquals("0",response.path("errcode").toString());
    }

    @Description("Description这个测试方法会测试修改部门的功能")
    @Story("Story修改部门测试")
    @DisplayName("DisplayName修改部门测试")
    @Test
    public void updateDepartment(){
        String name = "测试团队"+ RandomUtils.getTimeStamp();
        String eName = "test"+RandomUtils.getTimeStamp();
        String departmentId = DepartmentService.createDepartment(accessToken);
        Response response = DepartmentService.updateDepartment(departmentId,name,eName,accessToken);
        assertEquals("0",response.path("errcode").toString());
    }

    @DisplayName("DisplayName查询部门")
    @Description("Description这个测试方法会测试查询部门的功能")
    @Story("Story查询部门测试")
    @Test
    public void queryDepartment(){
        String name = "测试团队"+ RandomUtils.getTimeStamp();
        String eName = "test"+RandomUtils.getTimeStamp();
        Response createResponse = DepartmentService.createDepartment(name,eName,accessToken);
        String departmentId= createResponse.path("id")!=null ? createResponse.path("id").toString():null;
        Response queryResponse = DepartmentService.queryDepartment(accessToken,departmentId);

//        assertEquals("0",queryResponse.path("errcode").toString());
//        assertEquals(departmentId,queryResponse.path("department.id[0]").toString());

        assertAll("查询返回值校验!",
                ()->assertEquals("0",queryResponse.path("errcode").toString()),
                ()->assertEquals(departmentId,queryResponse.path("department.id[0]").toString()),
                ()->assertEquals(name,queryResponse.path("department.name[0]").toString()),
                ()->assertEquals(eName,queryResponse.path("department.name_en[0]").toString()));
    }

    @DisplayName("删除部门")
    @Test
    public void deleteDepartment(){
        String departmentId = DepartmentService.createDepartment(accessToken);
        Response deleteResponse = DepartmentService.deleteDepartment(accessToken,departmentId);
        assertEquals("0",deleteResponse.path("errcode").toString());
    }

}
