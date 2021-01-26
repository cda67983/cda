package com.demo.test;

import com.util.Calculator;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 曹达
 * @Date: 2021-1-15 18:16
 * @Description: 基础脚本
 */
public class TestNgDemoTest1 {

    @BeforeMethod
    public void clearResult(){
        Calculator.clear();
        System.out.println("计算器结果清零");
    }

    @Test
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

    @Test
    public void subtractTest(){
        int result = Calculator.subtract(4,2);
        System.out.println(result);
        Assert.assertEquals(result,2);
    }

    @Test
    public void multiplyTest(){
        int result = Calculator.multiply(4,2);
        System.out.println(result);
        Assert.assertEquals(result,8);
    }

    @Test
    public void divideTest(){
        int result = Calculator.divide(4,2);
        System.out.println(result);
        Assert.assertEquals(result,2);
    }

    @Test
    public void printArray(){
        int[] test = {1,3,4,5};
        twoSum(test,7);
    }

    public int[] twoSum(int[] nums, int target){
        for (int i=0; i<nums.length-1;i++){
            for (int j=i+1; j<nums.length; j++)
                if (nums[i] + nums[j] == target){
                    return new int[]{i,j};
            }
        }
        return nums;
    }

    public int[] twoSum2(int[] nums, int target){
        int n = nums.length;
        int[] result = new int[2];
        for (int i=0; i<n-1;i++){
            for (int j=i+1; j<n; j++)
                if (nums[i] + nums[j] == target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
        }
        return result;
    }

    public int[] twoSum3(int[] nums, int target){
        int len = nums.length;
        Map<Integer,Integer> map = new HashMap<>(len-1);
        map.put(nums[0],0);
        for (int i = 1; i < len; i++){
            int another = target-nums[i];
            if (map.containsKey(another)){
                return new int[]{i,map.get(another)};
            }
            map.put(nums[i],i);
        }
        return nums;
    }

    



}
