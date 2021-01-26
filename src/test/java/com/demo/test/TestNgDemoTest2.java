package com.demo.test;

import com.util.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @Author: 曹达
 * @Date: 2021-1-17 17:04
 * @Description:
 */
public class TestNgDemoTest2 {
    @BeforeMethod
    public void clearResult(){
        Calculator.clear();
        System.out.println("计算器结果清零");
    }

    @Test(priority = 2)
    public void addTest(){
        SoftAssert assertion = new SoftAssert();
        int result01 = Calculator.add(4,2);
        System.out.println(result01);
        assertion.assertEquals(result01,6);
        int result02 = Calculator.add(5,2);
        System.out.println(result02);
        assertion.assertEquals(result02,7);
        int result03 = Calculator.add(6,2);
        System.out.println(result03);
        assertion.assertEquals(result03,8);
        assertion.assertAll();
    }

    @Test(priority = 1)
    public void subtractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        Assert.assertEquals(result,2);
    }

    @Test(priority = 4)
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        Assert.assertEquals(result,8);
    }

    @Test(priority = 3)
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        Assert.assertEquals(result,2);
    }
}
