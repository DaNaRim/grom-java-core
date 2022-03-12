package main.lesson08.Homework;

public class Demo {

    public static void main(String[] args) {
        int[] array = {1, 234, 234, 444, -40};
        System.out.println(Adder.check(array));
        System.out.println(Adder.add(2,6));

        //test add Adder class

        System.out.println(Adder.add(4, 6));
        System.out.println(Adder.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
        System.out.println();

        //test check Arithmetic class

        //Коли Сума > 100
        //Коли Сума < 100
        //Коли Сума = 100
        //Коли array = null

        System.out.println(Arithmetic.check(new int[]{1, 66, 35, 100, 55}));
        System.out.println(Arithmetic.check(new int[]{1, 66, 35, 50, 55}));
        System.out.println(Arithmetic.check(new int[]{1, 66, 35, 99, 55}));
        System.out.println(Arithmetic.check(null));
        System.out.println();
    }
}
