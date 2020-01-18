package lesson17.homework.CountWords;

public class Solution {


    public static int countWords(String input) {
        if (input == null) return 0;

        int result = 0;
        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) result++;
        }
        return result;
    }

    private static boolean checkWord(String word) {
        for (char checkChar : word.toCharArray()) {
            if (!Character.isLetter(checkChar)) return false;
        }
        return true;
    }
}
