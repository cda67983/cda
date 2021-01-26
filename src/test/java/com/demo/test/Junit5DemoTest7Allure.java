package com.demo.test;

import com.util.Calculator;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author: 曹达
 * @Date: 2021-1-15 16:00
 * @Description:
 */
@Epic("Epic 计算器项目")
@Feature("Feature 冒烟测试用例")
public class Junit5DemoTest7Allure {

    @Test
    @Description("Description")
    @Story("Story 加法测试")
    @DisplayName("DisplayName 加法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7718")
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
    @Description("Description")
    @Story("Story 减法测试")
    @DisplayName("DisplayName 减法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7718")
    public void subTractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        Allure.addAttachment("pic","image/png",this.getClass().getResourceAsStream("D:/project/rest_assured_practice/src/main/resources/pic01.png"),".png");
        assertEquals(2,result);
    }

    @Test()
    @Description("Description")
    @Story("Story 乘法测试")
    @DisplayName("DisplayName 乘法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7718")
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        Allure.addAttachment("pic","image/png",this.getClass().getResourceAsStream("/pic01.png"),".png");
        assertEquals(8,result);
    }

    @Test
    @Description("Description")
    @Story("Story 除法测试")
    @DisplayName("DisplayName 除法测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7718")
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        assertEquals(2,result);
    }

    @Test
    @Description("Description")
    @Story("Story 累加测试")
    @DisplayName("DisplayName 累加测试")
    @Severity(SeverityLevel.BLOCKER)
    @Issue("www.baidu.com")
    @Link(name = "Link 社区贴",type = "mylink",url = "https://ceshiren.com/t/topic/7718")
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
