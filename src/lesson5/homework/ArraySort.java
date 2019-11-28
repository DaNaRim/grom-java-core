package lesson5.homework;

import java.util.Arrays;

public class ArraySort {
    public static void main(String[] args) {
        int[] sp = {11, 12, 10, 5, 4, 42, 100, 44, 50, 2};
        System.out.println(Arrays.toString(sortAscending(sp)));
        System.out.println(Arrays.toString(sortDescending(sp)));
    }

    static int[] sortAscending(int[] array) {
        int per;
        int a = 1;
        while (a > 0) {
            a = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i] < array[i - 1]) {
                    per = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = per;
                    a++;
                }
            }
        }
        return array;
    }

    static int[] sortDescending(int[] array) {
        int per;
        int a = 1;
        while (a > 0) {
            a = 0;
            for (int i = 1; i < array.length; i++) {
                if (array[i] > array[i - 1]) {
                    per = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = per;
                    a++;
                }
            }
        }
        return array;
    }
}
