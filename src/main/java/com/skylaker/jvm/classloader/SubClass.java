package com.skylaker.jvm.classloader;

/**
 * @author skylaker
 * @version V1.0 2020/5/18 20:34
 */
public class SubClass extends SuperClass{
    static {
        System.out.println("SubClass init !");
    }

    public static final String HELLOWORLD = "hello world";

    public static  String NAME = "SUB CLASS";

    public static void info(){
        System.out.println("Hello, I am Subclass !");
    }
}
