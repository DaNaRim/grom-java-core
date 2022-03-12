package gromcode.main.lesson22.arrays;

public class ArrayUtils {

    public static int maxElement(int[] array) {
        int max = array[0];
        for (int el : array) {
            max = Math.max(el, max);
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

    public static int[] sortDescending(int[] array) {
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
