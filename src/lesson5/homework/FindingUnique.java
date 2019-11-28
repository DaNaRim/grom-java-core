package lesson5.homework;

public class FindingUnique {
    public static void main(String[] args) {
        int[] sp = {11, 12, 10, 5, 4, 44, 100, 44, 11, 10, 11};
        System.out.println(uniqueCount(sp));
    }

    public static int uniqueCount(int[] array) {
        /*int count = 1;
        int prev = array[0];
        for (int element : array) {
            if (element != prev) {
                prev = element;
                count++;
            }
        }
        return count;*/

        int res = 0;
        int a = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] == array[j]){
                    res = a == 1 ? res : res - 1;
                    a = 1;
                }
            }
            a = 0;
            res++;
        }
        return res;
    }
}
        /*for (int i = 0; i < firstArray.length; i++) {
            int element = firstArray[i];
            System.out.println(element);
        }*/