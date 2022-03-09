package gromcode.main.lesson18.homework2;

public class Solution {

    public static int[] findNumbers(String text) {
        int[] res = new int[countNumbers(text)];

        int i = 0;
        for (String string : text.split(" ")) {
            if (checkNumber(string)) {
                res[i] = Integer.parseInt(string);
                i++;
            } else {
                System.out.println("not a number");
            }
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
