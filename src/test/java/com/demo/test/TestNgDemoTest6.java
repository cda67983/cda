package com.demo.test;

import com.util.Calculator;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * @Author: 曹达
 * @Date: 2021-1-17 19:52
 * @Description: xml参数化
 */
public class TestNgDemoTest6 {

    @Test(threadPoolSize = 1, invocationCount = 1)
    @Parameters({"x","y","result"})
    public void addTest(int x, int y, int result){
        int result01 = Calculator.add(x,y);
        System.out.println("完成加法计算，结果为："+result01);
        Assert.assertEquals(result,result01);
    }
}
