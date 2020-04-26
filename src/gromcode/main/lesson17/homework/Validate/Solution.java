package gromcode.main.lesson17.homework.Validate;

public class Solution {
    public static boolean validate(String address) {
        if (address == null || address.isEmpty() || !address.startsWith("http://") && !address.startsWith("https://") ||
                !address.endsWith(".com") && !address.endsWith(".org") && !address.endsWith(".net")) return false;

        if (address.startsWith("http://")) {
            address = address.replaceFirst("http://", "");
        }
        else if (address.startsWith("https://")) {
            address = address.replaceFirst("https://", "");
        }

        address = address.substring(0, address.length() - 4);

        if (address.startsWith("www.")) address = address.replaceFirst("www.", "");

        for (char ch : address.toCharArray()) {
            if (!Character.isLetter(ch) && !Character.isDigit(ch)) return false;
        }
        return true;
    }
}