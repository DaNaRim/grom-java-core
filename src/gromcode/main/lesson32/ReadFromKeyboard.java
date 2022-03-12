package gromcode.main.lesson32;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ReadFromKeyboard {

    public static void main(String[] args) throws IOException {
        readKeyboardWithIOStream();
    }

    private static void readKeyboardWithScanner() {
        //using scanner
        Scanner scanner = new Scanner(System.in);

        int n = 5;

        System.out.println("Please enter your name");

        System.out.println("Hello, " + scanner.nextLine());
        scanner.close();
    }

    private static void readKeyboardWithIOStream() throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);

        System.out.println("Please enter your name");
        System.out.println("Hello, " + br.readLine() + "!");
    }
}
