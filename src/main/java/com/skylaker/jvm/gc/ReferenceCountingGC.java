package com.skylaker.jvm.gc;

/**
 * 判断当前虚拟机是否采用的引用计数算法来判断垃圾
 *
 * @author skylaker
 * @version V1.0 2020/4/1 18:54
 */
public class ReferenceCountingGC {
    // 设置一个临时的2M大小的数组，只为了占据内存
    private byte[] data = new byte[2 * 1024 * 1024];

    private ReferenceCountingGC instance;

    public static void main(String[] args) {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;
        System.gc();
    }
}
