package lesson17.homework.Validate;

public class Solution {
    public static boolean validate(String address) {
        if (address == null || address.isEmpty()) return false;

        if (!address.startsWith("http://") && !address.startsWith("https://"))
            return false;

        if (!address.endsWith(".com") && !address.endsWith(".org") && !address.endsWith(".net"))
            return false;

        if (!checker(address.substring(6, address.length() - 3)) && !checker(address.substring(7, address.length() - 3)))
            return false;

        return true;
    }

    public static boolean checker(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isLetter(ch) && !Character.isDigit(ch))
                return false;
        }
        return true;
    }

}