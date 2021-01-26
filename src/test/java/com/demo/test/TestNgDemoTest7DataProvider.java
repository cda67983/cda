package com.demo.test;

import com.util.Calculator;
import com.util.FakerUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @Author: 曹达
 * @Date: 2021-1-17 19:59
 * @Description: dataProvider数据驱动
 */
public class TestNgDemoTest7DataProvider {

    @DataProvider(name = "testData")
    public static Object[][] words(){
        return FakerUtils.getTestData("/src/main/resources/data/divideparam.csv");
    }

    @Test(threadPoolSize = 1,invocationCount = 1,dataProvider = "testData")
    public void divTest(String x,String y){
        int result = Calculator.divide(Integer.valueOf(x),Integer.valueOf(y));
        System.out.println(result);
    }

}
