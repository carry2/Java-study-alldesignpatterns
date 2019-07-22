package com.yzp.study.designpatterns.singleton;

import com.sun.source.tree.SynchronizedTree;

import java.util.concurrent.SynchronousQueue;

/**
 * 懒汉式 需要时再加载
 * 优点 不占用空间
 * 缺点 会有多线程问题 可以枷锁 synchronized  但是效率会降低 可以把锁加到代码块上 双重判断  建议用饿汉式
 */
public class Singleton02lazy {
    private static  volatile  Singleton02lazy INSTANCE;
    //加volatile关键字是为了防止重排序导致的多线程问题 就是类被分配内存 但是还未实例化时 多线程会任何当前实例不存在

    private Singleton02lazy() {
    }

    public static synchronized Singleton02lazy getInstance(){
        /**
         * 双重校验的写法
         */
        if(INSTANCE == null) {
            synchronized (Singleton02lazy.class) {

                if (INSTANCE == null) {
                    INSTANCE = new Singleton02lazy();
                }
            }
        }
        return INSTANCE;

    }

    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            new  Thread(()-> System.out.println(Singleton02lazy.getInstance().hashCode())).start();
            Singleton02lazy instance = getInstance();
            System.out.println(instance.hashCode());
        }
    }
}
