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

    private static Person p = new Person("小丽");


    public static void main(String[] args) {
        System.out.println("Yes , JVM !");

        System.out.println(a);
        System.out.println(b);
        System.out.println(name);
        System.out.println(age);

        TestService service = new TestService();
        TestService service2 = new TestService();
        System.out.println(service.p == service2.p);
    }

    // 同步方法
    public synchronized void getData(){
        // TODO
        System.out.println("操作数据成功！");
    }

    private static final Object lock = new Object();
    private static final Object[] lock2 = new Object[1];

    public void getData2(){
        // 同步代码块
        synchronized (lock2) {
            // TODO
            System.out.println("操作数据成功！");
        }
    }
}
