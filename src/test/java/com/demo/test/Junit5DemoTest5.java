package com.demo.test;

import com.util.Calculator;
import org.junit.jupiter.api.RepeatedTest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: 曹达
 * @Date: 2021-1-14 19:03
 * @Description: 线程安全场景验证
 */
public class Junit5DemoTest5 {
    @RepeatedTest(10)
    public void countTest() throws InterruptedException {
        int result = Calculator.count(1);
        System.out.println(result);
    }

    @RepeatedTest(10)
    public void synTest() throws InterruptedException {
        int result = Calculator.synCount(1);
        System.out.println(result);
    }

    @RepeatedTest(10)
    public void testAtomic() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        int result = Calculator.count(atomicInteger.get());
        System.out.println(result);
    }
}
