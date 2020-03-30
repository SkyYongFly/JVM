package com.skylaker.jvm.rtda;

/**
 * HotSpot 虚拟机栈（和本地方法栈）溢出异常测试
 *
 *  VM 参数设置 -Xss128k
 *
 * @author skylaker
 * @version V1.0 2020/3/30 20:33
 */
public class StackSOF {
    private int stackLength = 1;

    /**
     * 这里通过递归循环调用自身，造成大量方法栈栈入栈
     */
    public void stackLeak(){
        int a = 0;
        int b = 0;
        int c = 0;
        int d = 0;
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        StackSOF stackSOF = new StackSOF();

        try{
            stackSOF.stackLeak();
        } catch (Throwable e){
            System.out.println("当前栈深度：" + stackSOF.stackLength);
            throw e;
        }
    }
}
