package com.demo.api.practice;

import com.demo.api.practice.apiobject.DepartmentService;
import com.demo.api.practice.apiobject.TokenHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;


/**
 * @Author: 曹达
 * @Date: 2021-1-4 11:24
 * @Description: 对创建部门进行并发测试
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentBaseThread {
    private static final Logger logger = LoggerFactory.getLogger(DepartmentBaseThread.class);
    public static String accessToken;

    @BeforeAll
    public static void getAccessToken(){
        accessToken = TokenHelper.getAccessToken();
        logger.info(accessToken);
    }

    @DisplayName("创建部门")
    @RepeatedTest(100)
    @Execution(CONCURRENT)
    public void createDepartment(){
        String creatName= "name1234567";
        String creatEnName="en_name1234567";
        System.out.println( "线程名字是"+ this.getClass()+Thread.currentThread().getName());
        Response createResponse = DepartmentService.createDepartment(creatName,creatEnName,accessToken);
        assertEquals("0",createResponse.path("errcode").toString());
    }
}
