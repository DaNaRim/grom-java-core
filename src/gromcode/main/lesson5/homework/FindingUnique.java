package gromcode.main.lesson5.homework;

public class FindingUnique {
    public static void main(String[] args) {
        int[] sp = {11, 12, 10, 5, 4, 44, 100, 44, 11, 10, 11};
        System.out.println(uniqueCount(sp));
    }

    public static int uniqueCount(int[] array) {
        if (array == null) return 0;

        ArraySort.sortAscending(array);
        int count = 1;

        int currentUnique = array[0];
        for (int el : array) {
            if (el != currentUnique) {
                currentUnique = el;
                count++;
            }
        }
        return count;
    }
}
