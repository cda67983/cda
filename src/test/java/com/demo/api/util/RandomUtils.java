package com.demo.api.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @Author: 曹达
 * @Date: 2021-1-3 14:22
 * @Description: 随机数工具类
 */
public class RandomUtils {

    /**
     * 获取随机数的长度
     */
    public static int getRandomInt(int length){
        length = length -1;
        int randomInt = (int)((Math.random() * 9 + 1) * Math.pow(10,(double)length));
        return randomInt;
    }

    public static int getRandomInt(int min,int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    /**
     * 获取时间戳
     * @return
     */
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String date = sdf.format(System.currentTimeMillis());
        return date;
    }

    public static String getTimeStamp(){
        return String.valueOf(System.currentTimeMillis());
    }

    public static int getNum(int start,int end){
        return (int)(Math.random() * (end - start + 1) + start);
    }

    public static String orderNo(){
        String cardNo = "123456";
        for (int i = 0; i < 8; i++){
            cardNo += getNum(0,9);
        }
        return cardNo;
    }

    /**
     * 电话号码生成器
     */
    private static String[] telFirst = "134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,188,185,181".split(",");

    public static String getTel(){
        int index = getNum(0, telFirst.length - 1);
        String first = telFirst[index];
        String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
        String thrid = String.valueOf(getNum(1, 9100) + 10000).substring(1);
        return first + second + thrid;

    }

    public static Object[][] getTestData(String fileName) throws IOException {

        String projectRoot = new File("").getAbsolutePath();
        List<Object[]> records = new ArrayList<Object[]>();
        String record;
        //设置字符集为UTF-8
        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(projectRoot + fileName), "UTF-8"));
        //忽略CSV文件的标题行（第一行）
        file.readLine();
        //遍历读取文件中除第一行外的其它所有行内容，并存储在名为records的ArrayList中，每一个records中存储的对象为一个string数组；
        while ((record = file.readLine()) != null) {
            String fields[] = record.split(",");
            records.add(fields);
        }
        //关闭文件
        file.close();
        //定义函数返回值Object[][],将list转换为一个Object的二维数组；
        Object[][] results = new Object[records.size()][];
        //设置二维数组每行的值，每行是一个Object对象
        for (int i = 0; i < records.size(); i++) {
            results[i] = records.get(i);
        }
        return results;
    }

}

