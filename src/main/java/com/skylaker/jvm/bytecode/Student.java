package com.skylaker.jvm.bytecode;

/**
 * @author skylaker
 * @version V1.0 2020/4/13 20:30
 */
public class Student {
    private static final String address = "南京";

    private int age;

    private String name;

    public Student(){

    }

    public Student(String name){
        this.name = name;
    }

    public int nextYearAge(){
        return this.age + 1;
    }

//    public static int num(){
//        int x;
//
//        try {
//            x = 1;
//            int a = 1/0;
//            return x;
//        } catch (Exception e){
//            x = 2;
//            return x;
//        } finally {
//            System.out.println("asds");
//            x = 3;
//        }
//    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
