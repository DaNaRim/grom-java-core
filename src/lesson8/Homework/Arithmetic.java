package lesson8.Homework;

public class Arithmetic {

    static boolean check(int[] array) {
        if (array == null) return false;

        sortAscending(array);
        int a = array[0];
        sortDescending(array);
        int b = array[0];

        return a + b > 100 ? true : false;
    }

    static int[] sortAscending(int[] array) {
        if (array == null) return null;

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
        if (array == null) return null;

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
