package lesson17.homework.MaxAndMinWord;

public class Solution {

    public static String maxWord(String input) {
        if (input == null) return null;

        String resultWord = "";

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) {
                if (resultWord.length() < str.length()) resultWord = str;
            }
        }
        return resultWord.isEmpty() ? null : resultWord;
    }

    public static String minWord(String input) {
        if (input == null) return null;

        String resultWord = null;
        int minLength = Integer.MAX_VALUE;

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) {
                if (minLength > str.length()){
                    resultWord = str;
                    minLength = str.length();
                }
            }
        }
        return resultWord;
    }

    private static boolean checkWord(String word) {
        for (char checkChar : word.toCharArray()) {
            if (!Character.isLetter(checkChar)) return false;
        }
        return true;
    }
}
