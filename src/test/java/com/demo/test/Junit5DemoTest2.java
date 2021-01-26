package com.demo.test;

import com.util.Calculator;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: 曹达
 * @Date: 2021-1-14 17:14
 * @Description: 使用Junit5提供的assertAll进行断言，增加了脚本的容错性
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Junit5DemoTest2 {

    @Test
    @Order(5)
    public void addTest(){
        int result01 = Calculator.add(4,2);
        System.out.println(result01);

        int result02 = Calculator.add(2,2);
        System.out.println(result02);

        int result03 = Calculator.add(5,2);
        System.out.println(result03);

        int result04 = Calculator.add(6,2);
        System.out.println(result04);

        int result05 = Calculator.add(7,2);
        System.out.println(result05);

        int result06 = Calculator.add(8,2);
        System.out.println(result06);

        assertAll("计算结果校验",
                ()-> assertEquals(6,result01),
                ()-> assertEquals(4,result02),
                ()-> assertEquals(7,result03),
                ()-> assertEquals(8,result04),
                ()-> assertEquals(9,result05),
                ()-> assertEquals(10,result06));
    }

    @Test
    @Order(4)
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test()
    @Order(3)
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        assertEquals(8,result);
    }

    @Test
    @Order(2)
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test
    @Order(1)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println(result);
        assertEquals(5,result);
    }

    @BeforeEach
    public void clear(){
        Calculator.clear();
    }

}
