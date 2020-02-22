package gromcode.main.lesson8.Homework;

import java.util.Arrays;

public class Demo_L8_H {
    public static void main(String[] args) {
        Adder adder = new Adder();
        int[] array = {1, 234, 234, 444, -40};
        adder.add(1, 2 );
        adder.check(array);


        //test add Adder class

        //Додавання
        //Коли великі дані

        Adder adder1 = new Adder();
        System.out.println(adder1.add(4, 6));
        System.out.println(adder1.add(2147483647, 2147483647));
        System.out.println();

        //test check Arithmetic class

        //Коли Сума > 100
        //Коли Сума < 100
        //Коли Сума = 100
        //Коли array = null

        int[] array1 = {1, 66, 35, 100, 55};
        System.out.println(Arithmetic.check(array1));

        array1 = new int[]{1, 66, 35, 50, 55};
        System.out.println(Arithmetic.check(array1));

        array1 = new int[]{1, 66, 35, 99, 55};
        System.out.println(Arithmetic.check(array1));

        System.out.println(Arithmetic.check(null));
        System.out.println();

        //test sortAscending Arithmetic class

        //сортировка масива
        //когда array null
        //когда масив уже отсортирован

        System.out.println(Arrays.toString(Arithmetic.sortAscending(array)));
        System.out.println(Arrays.toString(Arithmetic.sortAscending(null)));
        System.out.println(Arrays.toString(Arithmetic.sortAscending(array)));
        System.out.println();

        //test sortDescending Arithmetic class

        //сортировка масива
        //когда array null
        //когда масив уже отсортирован

        System.out.println(Arrays.toString(Arithmetic.sortDescending(array)));
        System.out.println(Arrays.toString(Arithmetic.sortDescending(null)));
        System.out.println(Arrays.toString(Arithmetic.sortDescending(array)));
        System.out.println();
    }
}
