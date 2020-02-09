package lesson32.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    public static int readNumbers() {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        int countNumbers = 0;
        int attempts = 3;

        outer:
        while (attempts > 0) {
            try {
                String[] numbers = br.readLine().split(" ");

                if (numbers.length != 10) {
                    if (attempts-- == 1) break;
                    System.out.println("Your numbers are wrong. You have " + attempts + " attempts to try again");
                    continue;
                }

                for (String str : numbers) {
                    if (Integer.parseInt(str) > 99) {
                        if (attempts-- == 1) break;
                        System.out.println("Your numbers are wrong. You have " + attempts + " attempts to try again");
                        countNumbers = 0;
                        continue outer;
                    }
                    countNumbers += Integer.parseInt(str);
                }
                return countNumbers;

            } catch (IOException e) {
                if (attempts-- == 1) break;
                System.out.println("Your numbers are wrong. You have " + attempts + " attempts to try again");
            }
        }
        System.out.println("Your numbers are wrong. Number of attempts exceeded");
        return 0;
    }
}
