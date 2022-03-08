package gromcode.main.lesson5;

import java.util.Arrays;

public class FirstArray {

    public static void main(String[] args) {
        int[] firstArray = new int[3];

        firstArray[0] = 11;
        firstArray[1] = 300;

        for (int i = 0; i < firstArray.length; i++) {
            System.out.println(firstArray[i]);
        }
        System.out.println(Arrays.toString(firstArray));

        int[] secondArray = {1, 234, 234, 444, -40};

        for (int element : secondArray) {
            System.out.println(element);
        }

        /*for (int i = 0; i < firstArray.length; i++) {
            int element = firstArray[i];
            System.out.println(element);
        }*/
    }
}
