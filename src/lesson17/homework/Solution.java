package lesson17.homework;

public class Solution {


    public int countWords(String input) {
        if (input == null || input.isEmpty()) return 0;

        String[] words = input.split(" ");

        return words.length;
    }
}
