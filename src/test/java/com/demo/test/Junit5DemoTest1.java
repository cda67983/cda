package com.demo.test;

import com.util.Calculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: 曹达
 * @Date: 2021-1-14 17:14
 * @Description: 基础脚本，添加自动断言，解决了需要人工检查结果的问题
 */
public class Junit5DemoTest1 {

    @Test
    public void addTest(){
        int result = Calculator.add(4,2);
        System.out.println(result);
        assertEquals(6,result);
    }

    @Test
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        assertEquals(8,result);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        result = Calculator.count(1);
        System.out.println(result);
        assertEquals(5,result);
    }

}
