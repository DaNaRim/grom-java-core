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
        return resultWord.isEmpty() ? null : resultWord;
    }

    public static String minWord(String input) {
        if (input == null) return null;

        String resultWord = input.trim().split(" ")[0];

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && checkWord(str)) {
                if (resultWord.length() > str.length()) resultWord = str;
            }
        }
        return resultWord.isEmpty() ? null : resultWord;
    }

    public static String mostCountedWord(String input) {
        if (input == null) return null;

        String resultWord = null;
        int maxNumOfRepWord = 1;

        for (String str : input.trim().split(" ")) {
            if (!str.equals("") && countDuplicates(input, str) > maxNumOfRepWord) {
                resultWord = str;
                maxNumOfRepWord = countDuplicates(input, str);
            }
        }
        return resultWord;
    }

  /*  public static String validate(String address) {
        if (address == null) return null;

        if (!address.substring(0, 7).equals("http://") || !address.substring(0, 8).equals("https://"))
            address = "http://" + address;

        if (!address.substring(address.length() - 2, address.length() + 1).equals("com")
                || !address.substring(address.length() - 2, address.length() + 1).equals("org")
                || !address.substring(address.length() - 2, address.length() + 1).equals("net")) {
            if (!address.substring(address.length() - 1, address.length() + 1).equals(".")) {
                address += ".com";
            } else address += "com";
        }


        if (!address.substring(address.length() - 3, address.length() - 2).equals("."))
            address += ".";

        char[] ch = address.trim().toCharArray();



    }*/


    private static boolean checkWord(String word) {
        for (char checkChar : word.toCharArray()) {
            if (!Character.isLetter(checkChar)) return false;
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
