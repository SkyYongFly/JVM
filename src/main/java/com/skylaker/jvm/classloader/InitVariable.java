package com.skylaker.jvm.classloader;

/**
 * @author skylaker
 * @version V1.0 2020/5/25 21:04
 */
public class InitVariable {
    static {
        i = 1;  // 给后边的类变量赋值可以编译通过
//        System.out.println(i); // Error:(10, 28) java: 非法前向引用
    }

    static int i = 0;
}
