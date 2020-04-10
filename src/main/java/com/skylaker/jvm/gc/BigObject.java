package com.skylaker.jvm.gc;

/**
 * 测试大对象直接进入老年代
 *
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8  -XX:PretenureSizeThreshold=3145728
 *
 * @author skylaker
 * @version V1.0 2020/4/10 21:15
 */
public class BigObject {
    public static void main(String[] args) {
        byte[] a = new byte[4 * 1024 * 1024];
    }
}
