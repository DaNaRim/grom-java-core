package main.lesson08.Homework;

public class Arithmetic {

    static boolean check(int[] array) {
        if (array == null) return false;

        sortAscending(array);
        return array[0] + array[array.length - 1] > 100;
    }

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
}
