package lesson5;

public class ArraysElement {
    public static void main(String[] args) {
        int[] array = {-10, 0, 234, 444, 665, 234};
        System.out.println(maxElement(array));
        System.out.println(nCount(array, 234));
    }

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


}
