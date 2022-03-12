package main.lesson31.homework;

import java.util.HashMap;

public class Solution {

    public static HashMap<Character, Integer> countSymbols(String text) {
        HashMap<Character, Integer> countSymbols = new HashMap<>();

        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                countSymbols.put(ch, countSymbols.get(ch) == null ? 1 : countSymbols.get(ch) + 1);
            }
        }
        return countSymbols;
    }

    public static HashMap<String, Integer> words(String text) {
        HashMap<String, Integer> countWords = new HashMap<>();

        for (String word : text.split(" ")) {
            if (validate(word) && word.length() > 2) {
                countWords.put(word, countWords.get(word) == null ? 1 : countWords.get(word) + 1);
            }
        }
        return countWords;
    }

    private static boolean validate(String word) {
        if (word.length() <= 2) return false;

        for (char ch : word.toCharArray()) {
            if (!Character.isLetter(ch)) return false;
        }
        return true;
    }
}
