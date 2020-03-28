package com.skylaker.jvm;

/**
 * @author skylaker
 * @version V1.0 2020/3/28 11:03
 */
public class TestService {
    private static int a;
    private static double b;
    private static String name;
    private static Integer age;

    public static void main(String[] args) {
        System.out.println("Yes , JVM !");

        System.out.println(a);
        System.out.println(b);
        System.out.println(name);
        System.out.println(age);

        TestService service = new com.skylaker.jvm.TestService();
    }
}
