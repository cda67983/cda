package com.demo.api.practice;

import com.demo.api.practice.apiobject.DepartmentService;
import com.demo.api.practice.apiobject.TokenHelper;
import com.demo.api.util.RandomUtils;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * @Author: 曹达
 * @Date: 2021-1-4 11:24
 * @Description: 对创建部门进行并发测试
 */
public class DepartmentBaseThreadCurrent {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentBaseThreadCurrent.class);
    public static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @DisplayName("创建部门")
    @Test
    @RepeatedTest(10)
    public void createDepartment() throws InterruptedException {
        System.out.println( "线程名字是"+ this.getClass()+Thread.currentThread().getName());
        String backendStr = Thread.currentThread().getId()+ RandomUtils.getTimeStamp()+"";
        String name = "name"+ backendStr;
        String enName = "en_name"+backendStr;
        Response createResponse = DepartmentService.createDepartment(name,enName,accessToken);
        assertEquals("0",createResponse.path("errcode").toString());
    }

    @DisplayName("修改部门")
    @Test
    @RepeatedTest(10)
    public void updateDepartment()  {
        System.out.println( "线程名字是"+ this.getClass()+Thread.currentThread().getName());
        String backendStr = Thread.currentThread().getId()+ RandomUtils.getTimeStamp()+"";

        String creatName = "name"+backendStr;
        String creatEnName = "en_name"+backendStr;

        Response createResponse = DepartmentService.createDepartment(creatName,creatEnName,accessToken);
        String departmentId = createResponse.path("id")!=null ? createResponse.path("id").toString():null;

        String updateName = "name" + backendStr;
        String updateEnName = "en_name" + backendStr;

        Response updateResponse= DepartmentService.updateDepartment(departmentId,updateName,updateEnName,accessToken);
        assertEquals("0",updateResponse.path("errcode").toString());
    }
}
