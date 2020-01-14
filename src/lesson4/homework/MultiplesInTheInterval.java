package lesson4.homework;

public class MultiplesInTheInterval {
    public static void main(String[] args) {
        short a = 100, b = 1000;

        System.out.println(findDivCount(a, b, 3));
    }

    public static int findDivCount(short a, short b, int n) {
        int res = 0;

        if (n == 0) return 0;

        for (int i = a; i <= b; i++)
            if (i % n == 0) res++;
        return res;
    }
}