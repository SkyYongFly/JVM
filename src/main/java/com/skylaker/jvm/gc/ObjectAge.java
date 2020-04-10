package com.skylaker.jvm.gc;

/**
 * 测试对象年龄晋升
 *
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=3 -XX:MaxTenuringThreshold=15
 *
 * @author skylaker
 * @version V1.0 2020/4/10 21:35
 */
public class ObjectAge {
    private static final int SIZE_1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] a, b, c, d;

        a = new byte[10 * 1024];    // 10K
        b = new byte[4 * SIZE_1MB];
        c = new byte[4 * SIZE_1MB];
        c = null;
        d = new byte[4 * SIZE_1MB];
    }
}
