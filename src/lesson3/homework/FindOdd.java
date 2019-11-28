package lesson3.homework;

public class FindOdd {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i <= 1000; i++) {
            if (i % 2 == 1) {
                sum += i;
                System.out.println("Found");
            }
        }

        if (sum * 5 >= 5000) {
            System.out.println("Bigger");
        } else {
            System.out.println("Smaller or equal");
        }
    }
}