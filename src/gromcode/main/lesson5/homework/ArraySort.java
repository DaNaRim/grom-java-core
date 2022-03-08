package gromcode.main.lesson5.homework;

import java.util.Arrays;

public class ArraySort {

    public static void main(String[] args) {
        int[] sp = {11, 12, 10, 5, 4, 42, 100, 44, 50, 2};
        System.out.println(Arrays.toString(sortAscending(sp)));
        System.out.println(Arrays.toString(sortDescending(sp)));
    }

//    static int[] sortAscending(int[] array) {
//        //Bubble sort
//        if (array == null) return null;
//
//        boolean sorted = false;
//        int temp;
//        while (!sorted) {
//            sorted = true;
//            for (int i = 0; i < array.length - 1; i++) {
//                if (array[i] > array[i + 1]) {
//                    temp = array[i];
//                    array[i] = array[i + 1];
//                    array[i + 1] = temp;
//                    sorted = false;
//                }
//            }
//        }
//        return array;
//    }

    static int[] sortAscending(int[] array) {
        //Insertion Sort
        if (array == null) return null;

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }

    static int[] sortDescending(int[] array) {
        if (array == null) return null;

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current > array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
        return array;
    }
}
