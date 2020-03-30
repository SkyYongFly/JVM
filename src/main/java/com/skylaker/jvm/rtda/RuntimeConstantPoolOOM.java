package com.skylaker.jvm.rtda;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池内存溢出测试
 *
 *  VM参数设置：
 *      JDK1.6  -XX:PermSize=10M  -XX:MaxPermSize=10M
 *      JDK1.7  -XX:PermSize=10M  -XX:MaxPermSize=10M
 *      JDK1.7  -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 *      JDK1.8  -Xms10m -Xmx10m -XX:+HeapDumpOnOutOfMemoryError
 *      JDK1.8  -Xms100m -Xmx100m -XX:+HeapDumpOnOutOfMemoryError  -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 *
 *
 * @author skylaker
 * @version V1.0 2020/3/30 20:56
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        // 这里List保持对常量池中字符串常量引用，避免 Full GC 时候回收常量池
        List<String> list = new ArrayList<String>();

        // 这里循环产生字符串，而 i 变量是有范围的，Integer 范围内，
        // 不过这个范围内产生的字符串数量足够超出方法区 10M 的限制
        int  i = 0;
        while (true){
            System.out.println(i);
            list.add(String.valueOf(i++).intern());
        }
    }
}
