package gromcode.main.lesson4.homework;

public class ComparisonOfAmounts {
    public static void main(String[] args) {
        System.out.println(compareSums(1, 2, 3, 4));
    }

    public static boolean compareSums(int a, int b, int c, int d) {
        return sum(a, b) > sum(c, d) ? true : false;
    }

    public static long sum(int from, int to) {
        long sum = 0;

        for (long num = from; num <= to; num++) sum += num;
        return sum;
    }
}