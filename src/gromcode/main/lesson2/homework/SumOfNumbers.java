package gromcode.main.lesson2.homework;

public class SumOfNumbers {
    public static void main(String[] args) {
        int n = 0;
        long sum = 0;
        while (n <= 10000000) {
            sum += n;
            n++;
        }

        System.out.println(sum);
    }
}