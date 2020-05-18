package com.skylaker.jvm.classloader;

/**
 * 被动引用例子
 * @author skylaker
 * @version V1.0 2020/5/18 20:32
 */
public class ClassLoaderService {
    public static void main(String[] args) {
        // 通过子类引用父类的静态资源，不会导致子类初始化
        System.out.println(SubClass.value);

        // 通过数组定义来引用类，不会触发此类的初始化
        SubClass[] sca = new SubClass[10];
        System.out.println(sca.length);

        // 常量在编译阶段会存入调用类的常量池中，本质上并没有直接引用到定义常量的类，因此不会触发定义常量的类的初始化
        System.out.println(SubClass.HELLOWORLD);
    }
}
