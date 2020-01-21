package lesson17.homework.Validate;

public class Solution {
  public class Solution {
    public static String validate(String address) {
        if (address == null) return null;


        for (int i = 0; i < address.toCharArray().length; i++) {
            if (!Character.isLetter(i)){
                address = address.replace(i, "");
            }
        }


        for (char ch : address.toCharArray()) {
            if (!Character.isLetter(ch))

                address = address.replace(ch, );
        }



        if (!address.startsWith("http://") || !address.startsWith("https://")){
            address = "http://" + address;
            
            if (address.endsWith(".com") || address.endsWith(".org") || address.endsWith(".net")){

            }
        }

        String subString = address.substring(address.length() - 2, address.length() + 1);

        if (subString.equals("com") || subString.equals("org") || subString.equals("net")) {
            if (!address.substring(address.length() - 3, address.length() - 2).equals(".")) {
                String firstString = address.substring(0, address.length() - 3);
                String thirdString = address.substring(address.length() - 2);
                address = firstString + "." + thirdString;
            } else {
                if (!address.substring(address.length() - 3, address.length() - 2).equals("."))
                    address += ".com";
                else
                    address += "com";
            }
        }



        if (!address.substring(address.length() - 3, address.length() - 2).equals("."))
            address += ".";
        char[] ch = address.trim().toCharArray();
    }
   
}
