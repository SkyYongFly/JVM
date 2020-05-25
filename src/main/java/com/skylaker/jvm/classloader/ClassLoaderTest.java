package com.skylaker.jvm.classloader;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author skylaker
 * @version V1.0 2020/5/25 21:37
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader myLoader = new ClassLoader(){
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException{
                try{
                    String fileName = name.substring(name.lastIndexOf(".") + 1 ) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if(is == null){
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name,b,0,b.length);
                }catch(IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj= myLoader.loadClass("com.skylaker.jvm.classloader.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }
}
