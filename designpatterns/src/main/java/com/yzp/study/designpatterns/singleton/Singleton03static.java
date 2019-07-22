package com.yzp.study.designpatterns.singleton;

/***
 * 第三种写法 静态内部类的写法  比较完美的写法
 *
 *
 */
public class Singleton03static {

    private Singleton03static() {
    }

    /**
     * 静态内部类 负责外部类的实例化 这样可以防止多线程问题 通过jvm实例化顺序的机制解决
     */
    private static  class Singleton03staticPlaceholder{

        private static Singleton03static INSTANCE=new Singleton03static();

    }
    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            new  Thread(()-> System.out.println(Singleton03staticPlaceholder.INSTANCE.hashCode())).start();
        }
    }
}

