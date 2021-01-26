package com.demo.test;

import com.util.Calculator;
import org.testng.annotations.Test;

/**
 * @Author: 曹达
 * @Date: 2021-1-17 19:22
 * @Description: testNG模拟多线程
 */
public class TestNgDemoTest4 {
    @Test(threadPoolSize = 3,invocationCount = 10,timeOut = 6000)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println("计算加法结果为："+result);
    }

    @Test(threadPoolSize = 3,invocationCount = 10,timeOut = 60000)
    public void countSyncTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        System.out.println("计算加法结果为："+result);
    }
}
