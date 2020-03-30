package com.skylaker.jvm.rtda;

/**
 * @author skylaker
 * @version V1.0 2020/3/30 22:06
 */
public class StringIntern {
    public static void main(String[] args) {
        String a = new String("中国");
        String b = a.intern();

        System.out.println(a == b);

        String c = "中国";
        System.out.println(a == c);
        System.out.println(b == c);

        String d = new StringBuilder("华").append("夏").toString();
        String e = d.intern();

        System.out.println(d == e);
    }
}
