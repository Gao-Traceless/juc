package com.gao.juc;

public class LambdaExpressDemo {

    public static void main(String[] args) {

     /*   Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello java00001");
            }
        };

        foo.sayHello(); */
        Foo foo = () ->{System.out.println("hello java");};
        foo.sayHello();

        Foo1 foo1 = (x, y) ->{
            System.out.println("come in here");
            return x + y;
        };

        System.out.println(foo1.add(2,4));
        System.out.println(foo1.div(10, 5));

        System.out.println(Foo1.mv(3, 5));

    }

}

/**
 * 2    lambda Express
 *  2.1 口诀：
 *      拷贝小括号，写死右箭头，落地大括号
 *  2.2 @FunctionalInterface
 *
 *  2.3 default
 *
 *  2.4 静态方法实现
 */
@FunctionalInterface
interface Foo{

    void sayHello();

}

interface Foo1{

    int add(int x, int y);

    default int div(int x, int y){
        System.out.println("hello java");
        return x/y;
    }

    public static int mv(int x, int y){
        return x * y;
    }

}