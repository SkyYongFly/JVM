package com.skylaker.jvm.rtda;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 直接内存溢出测试
 *
 *  JVM 参数：
 *
 * @author skylaker
 * @version V1.0 2020/3/31 20:41
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        // 通过反射获取 Unsafe 实例
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            // 向底层操作系统申请分配内存
            unsafe.allocateMemory(_1MB);
        }
    }
}
