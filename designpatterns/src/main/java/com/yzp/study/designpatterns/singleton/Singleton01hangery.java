package com.yzp.study.designpatterns.singleton;

/**
 * 单例饿汉式 调用之前就初始化好
 * 优点 不会有多线程问题  缺点是不用的时候浪费内存空间  推荐使用 简单
 * create by yzp 2019-07-21
 */
public class Singleton01hangery {
    private static final Singleton01hangery INSTANCE=new Singleton01hangery();

    private  Singleton01hangery() {

    }
    public  Singleton01hangery getInstance(){

        return INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(INSTANCE.getInstance().hashCode());
        }

    }

}
