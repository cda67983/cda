package com.demo.test;

import com.util.Calculator;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @Author: 曹达
 * @Date: 2021-1-17 19:29
 * @Description: testNG模拟多线程
 */
public class TestNgDemoTest5 {

    @Test(threadPoolSize = 2,invocationCount = 5)
    public void addTest() {
        int result = Calculator.add(4, 2);
        System.out.println("完成加法计算，结果为：" + result);
        Assert.assertEquals(result, 6);
    }

    @Test(threadPoolSize = 2,invocationCount = 5)
    public void subtractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println("完成减法计算，结果为："+result);
        Assert.assertEquals(result,2);
    }


}
