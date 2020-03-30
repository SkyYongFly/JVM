package com.skylaker.jvm.rtda;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟堆内存溢出
 *
 *  VM 参数设置：-Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author skylaker
 * @version V1.0 2020/3/30 19:45
 */
public class HeapOOM {

    static class  OOMObject {

    }


    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
