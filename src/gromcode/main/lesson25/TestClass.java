package gromcode.main.lesson25;

public class TestClass<T, K, V> {

    T doSomething1(T t) {
        System.out.println("1");
        //logic
        return t;
    }

    K doSomething2(K k) {
        System.out.println("2");
        //logic2
        return k;
    }

    V doSomething3(V v) {
        System.out.println("3");
        //logic
        return v;
    }
}