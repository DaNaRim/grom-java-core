package gromcode.main.lesson2.homework;

public class SumAndDivision {

    public static void main(String[] args) {
        long sum = 0;
        for (int i = 1; i < 1000; i++) {
            sum += i;
        }
        System.out.println(sum % 1234 > sum / 1234);
    }
}
