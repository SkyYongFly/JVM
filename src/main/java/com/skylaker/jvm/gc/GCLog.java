package com.skylaker.jvm.gc;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:SurvivorRatio=8
 *
 * @author skylaker
 * @version V1.0 2020/4/10 19:36
 */
public class GCLog {
    private static final int SIZE_1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] a, b, c, d;

        a = new byte[2 * SIZE_1MB];
        b = new byte[2 * SIZE_1MB];
        c = new byte[2 * SIZE_1MB];
        d = new byte[4 * SIZE_1MB];
    }
}
