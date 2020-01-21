package lesson17.homework.Validate;

public class Solution {
    public static boolean validate(String address) {
        if (address == null || address.isEmpty()) return false;

        if (!address.startsWith("http://") && !address.startsWith("https://"))
            return false;

        if (!address.endsWith(".com") && !address.endsWith(".org") && !address.endsWith(".net"))
            return false;

        address = address.substring(0, address.length() - 3);

        for (char ch : address.toCharArray()) {
            if (!Character.isLetter(ch) && !Character.isDigit(ch))
                return false;
        }
        return true;
    }
}