package com.skylaker.jvm.classloader;

/**
 * @author skylaker
 * @version V1.0 2020/5/18 20:34
 */
public class SuperClass {
    static {
        System.out.println("SuperClass init !");
    }

    public static int value = 123;
}
