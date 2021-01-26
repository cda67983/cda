package com.demo.test;

import org.junit.jupiter.api.*;

/**
 * @Author: 曹达
 * @Date: 2021-1-14 17:37
 * @Description: Fixture演练
 */
public class Junit5DemoTest4 extends Junit5DemoTest3 {

    @BeforeAll
    public static void beforeChildAll(){
        System.out.println("child beforeAll 执行！");
    }

    @AfterAll
    public static void afterChildAll(){
        System.out.println("child afterAll 执行！");
    }

    @BeforeEach
    public void beforeChildEach(){
        System.out.println("child beforeEach 执行！");
    }

    @AfterEach
    public void afterChildEach(){
        System.out.println("child afterEach 执行！");
    }

    @Test
    public void testChildMethod01(){
        System.out.println("child testMethod01 执行");
    }

    @Test
    public void testChildMethod02(){
        System.out.println("child testMethod02 执行");
    }

    @Test
    public void testChildMethod03(){
        System.out.println("child testMethod03 执行");
    }

}
