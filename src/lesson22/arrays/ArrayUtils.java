package lesson22.arrays;

public class ArrayUtils {

    public static int maxElement(int[] array) {
        int max = array[0];
        for (int el : array) {
            max = el > max ? el : max;
        }
        return max;
    }

    public static int nCount(int[] array, int n) {
        int count = 0;
        for (int el : array) {
            count = el == n ? count + 1 : count;
        }

        return count;
    }

    public static int[] sortAscending(int[] array) {
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

    public static int[] sortDescending(int[] array) {
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
