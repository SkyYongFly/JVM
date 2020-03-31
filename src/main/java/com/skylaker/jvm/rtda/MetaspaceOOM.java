package com.skylaker.jvm.rtda;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

/**
 * JDK8 测试元数据区OOM
 * @author skylaker
 * @version V1.0 2020/3/31 19:48
 */
public class MetaspaceOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        while (true) {
            System.out.println(1);
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(objects, args));
            enhancer.create();
        }
    }
}
