package com.skylaker.jvm.rtda;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JDK7 测试方法区OOM
 *
 * JVM参数：-XX:PermSize=20M   -XX:MaxPermSize=20M  -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author skylaker
 * @version V1.0 2020/3/31 19:48
 */
public class MethodAreaOOM {

    static class OOMObject {

    }

    public static void main(final String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(objects, args);
                }
            });
            enhancer.create();
        }
    }
}
