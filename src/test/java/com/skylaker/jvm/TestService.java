package com.skylaker.jvm;

/**
 * @author skylaker
 * @version V1.0 2020/3/28 11:03
 */
public class TestService {
    public static void main(String[] args) {
        int a = 1 + 3;
        System.out.println("Yes , JVM !");

        TestService service = new com.skylaker.jvm.TestService();
    }
}
