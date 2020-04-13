package com.skylaker.jvm.bytecode;

/**
 * @author skylaker
 * @version V1.0 2020/4/13 20:30
 */
public class Student {
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
