package com.demo.test;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: 曹达
 * @Date: 2021-1-15 15:14
 * @Description: 分布式锁演练
 */
public class Junit5DemoTest6 {

    @RepeatedTest(10)
    public void addTest(){
        int result = Calculator.add(4,2);
        System.out.println(result);
        assertEquals(6,result);
    }

    @RepeatedTest(10)
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }
}
