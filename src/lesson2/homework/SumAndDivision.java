package lesson2.homework;

public class SumAndDivision {
    public static void main(String[] args) {
        int n = 0;
        long sum = 1;
        while (n <= 1000) {
            sum += n;
            n++;
        }
        n = 1234;

        System.out.println(sum % n > sum / n);
    }
}