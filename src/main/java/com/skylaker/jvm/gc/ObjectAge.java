package com.skylaker.jvm.gc;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

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
//        byte[] a, b, c, d, e;
//
//        a = new byte[512 * 1024];    // 0.5M
//        e = new byte[512 * 1024];    // 0.5M
//        b = new byte[4 * SIZE_1MB];
//        c = new byte[4 * SIZE_1MB];
//        c = null;
//        d = new byte[4 * SIZE_1MB];
        byte[] b1, b2, b3, b4, b5, b6, b7;

        b1 = new byte[2 * SIZE_1MB];
        b2 = new byte[2 * SIZE_1MB];

        b3 = new byte[2 * SIZE_1MB];  // 分配空间之前 MinorGC
        b1 = null;
        b4 = new byte[2 * SIZE_1MB];
        b5 = new byte[2 * SIZE_1MB];

        b6 = new byte[2 * SIZE_1MB]; // 分配空间之前 MinorGC 、Full GC
        b4 = null;
        b5 = null;
        b6 = null;
        b7 = new byte[2 * SIZE_1MB];
    }
}
