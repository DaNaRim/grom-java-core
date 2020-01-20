package lesson17.homework.MostCountedWord;

public class Solution {

    public static String mostCountedWord(String input) {
        if (input == null) return null;

        String resultWord = null;
        int maxNumOfRepWord = 0;

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str) && countDuplicates(input, str) > maxNumOfRepWord) {
                resultWord = str;
                maxNumOfRepWord = countDuplicates(input, str);
            }
        }
        return resultWord;
    }

    private static int countDuplicates(String input, String word) {
        String[] strings = input.split(" ");

        int res = 0;
        for (String string : strings) {
            if (string.equals(word)) res++;
        }
        return res;
    }

    private static boolean checkWord(String word) {
        for (char checkChar : word.toCharArray()) {
            if (!Character.isLetter(checkChar)) return false;
        }
        return true;
    }
}
