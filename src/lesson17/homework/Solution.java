package lesson17.homework;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        System.out.println(countWords("    AAA    BBB CCC"));
    }

    public static int countWords(String input) {
        if (input == null) return 0;

        int result = 0;
        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) result++;
        }
        return result;
    }

    public static String maxWord(String input) {
        if (input == null) return null;

        String resultWord = "";

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) {
                if (resultWord.length() < str.length()) resultWord = str;
            }
        }
        return resultWord;
    }

    public static String minWord(String input) {
        if (input == null) return null;

        String resultWord = input.trim().split(" ")[0];

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) {
                if (resultWord.length() > str.length()) resultWord = str;
            }
        }
        return resultWord;
    }

    public static String mostCountedWord(String input) {
        if (input == null) return null;

        String resultWord = "";
        int maxNumOfRepWord = 0;

        for (String str : input.trim().split(" ")) {
            if (countDuplicates(input, str) > maxNumOfRepWord) {
                resultWord = str;
                maxNumOfRepWord = countDuplicates(input, str);
            }
        }
        return resultWord.equals("") ? null : resultWord;
    }

    private static boolean checkWord(String word) {
        for (char checkChar : word.toCharArray()) {
            if (/*!чекаєм чи буква це*/) return false;
        }
        return true;
    }

    private static int countDuplicates(String input, String word) {
        String[] strings = input.split(" ");

        int res = 0;
        for (String string : strings) {
            if (string.equals(word)) res++;
        }
        return res;
    }
}
