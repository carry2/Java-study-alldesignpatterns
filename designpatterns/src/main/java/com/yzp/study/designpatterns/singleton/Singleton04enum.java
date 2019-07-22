package com.yzp.study.designpatterns.singleton;

/**
 * 枚举单例是最完美写法 可以防止多线程和序列化破坏的问题
 * 枚举无法反序列化 通过反射被破坏 因为枚举没有构造方法
 * 设计模式的本质是用来解决问题 不是用来扣的 所以大多数情况下没必要研究多种写法 饿汉式就可以
 * 另外spring注入的bean 都是单例 如果要定义单例 交给spring管理就行  没有必要自己写了
 */
public enum Singleton04enum {

    INSTANCE;

    public static void main(String[] args) {

        for (int i = 0; i <100 ; i++) {
            new  Thread(()-> System.out.println(Singleton04enum.INSTANCE.hashCode())).start();
        }
    }


}
