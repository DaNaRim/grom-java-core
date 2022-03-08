package gromcode.main.lesson4.homework;

public class MultiplesInTheInterval {

    public static void main(String[] args) {
        short a = Short.MIN_VALUE;
        short b = Short.MAX_VALUE;

        System.out.println(findDivCount(a, b, 0));
    }

    public static int findDivCount(short a, short b, int n) {
        if (n == 0) return 0;

        int res = 0;
        for (int i = a; i <= b; i++) {
            if (i % n == 0) res++;
        }
        return res;
    }
}
