package gromcode.main.lesson18.homework1;

public class Solution {

    public static int[] findNumbers(String text) {
        int[] res = new int[countNumbers(text)];

        int i = 0;
        for (String string : text.split(" ")) {
            try {
                res[i] = Integer.parseInt(string);
            } catch (Exception e) {
                System.out.println("not a number");
            }
            i++;
        }
        return res;
    }

    private static int countNumbers(String text) {
        int countNumbers = 0;
        for (String string : text.split(" ")) {
            if (checkNumber(string)) countNumbers++;
        }
        return countNumbers;
    }

    private static boolean checkNumber(String word) {
        for (char checkChar : word.toCharArray()) {
            if (!Character.isDigit(checkChar)) return false;
        }
        return true;
    }
}