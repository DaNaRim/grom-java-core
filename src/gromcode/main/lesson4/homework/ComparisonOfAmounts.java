package gromcode.main.lesson4.homework;

public class ComparisonOfAmounts {

    public static void main(String[] args) {
        System.out.println(compareSums(Integer.MIN_VALUE, Integer.MAX_VALUE, -200000, 0));

        System.out.println(-2147483648);
        System.out.println(sum(Integer.MIN_VALUE, Integer.MAX_VALUE));

        System.out.println(sum(-200000, 0));
    }

    public static boolean compareSums(int a, int b, int c, int d) {
        return sum(a, b) > sum(c, d);
    }

    public static long sum(int from, int to) {
        long sum = 0;
        for (long i = from; i <= to; i++) {
            sum += i;
        }
        return sum;
    }
}
