package gromcode.main.lesson5.homework;

import java.util.Arrays;

public class Demo_L5_H {

    public static void main(String[] args) {
        int[] array = {11, 12, 100, 5, 4, 42, 100, 4, 50, 2};

        //test sortAscending ArraySort class

        //сортировка масива
        //когда array null
        //когда масив уже отсортирован

        System.out.println(Arrays.toString(ArraySort.sortAscending(array)));
        System.out.println(Arrays.toString(ArraySort.sortAscending(null)));
        System.out.println(Arrays.toString(ArraySort.sortAscending(array)));
        System.out.println();

        //test sortDescending ArraySort class

        //сортировка масива
        //когда array null
        //когда масив уже отсортирован

        System.out.println(Arrays.toString(ArraySort.sortDescending(array)));
        System.out.println(Arrays.toString(ArraySort.sortDescending(null)));
        System.out.println(Arrays.toString(ArraySort.sortDescending(array)));
        System.out.println();

        //test uniqueCount FindingUnique class

        //знаходження уныкальних
        //якщо array null

        System.out.println(FindingUnique.uniqueCount(array));
        System.out.println(FindingUnique.uniqueCount(null));
        System.out.println();
    }
}
