package gromcode.main.lesson4.homework;

public class Demo_L4_H {

    public static void main(String[] args) {

        //test sum ComparisonOfAmounts class

        //подсчет суми от from до to
        //когда to менше за from
        //когда from равна to

        System.out.println(ComparisonOfAmounts.sum(1, 5));
        System.out.println(ComparisonOfAmounts.sum(5, 1));
        System.out.println(ComparisonOfAmounts.sum(6, 6));
        System.out.println();

        //test compareSums ComparisonOfAmounts class

        //когда сума от a до b больша за суму от c до d
        //когда сума от a до b меньша за суму от c до d
        //когда сума от a до b равна суме от c до d

        System.out.println(ComparisonOfAmounts.compareSums(3, 4, 1, 2));
        System.out.println(ComparisonOfAmounts.compareSums(1, 2, 0, 8));
        System.out.println(ComparisonOfAmounts.compareSums(3, 4, 7, 7));
        System.out.println();

        //test findDivCount MultiplesInTheInterval class

        //подсчет деленних
        //когда нет деленних
        //когда b > a
        //когда n = 0

        System.out.println(MultiplesInTheInterval.findDivCount((short) 1, (short) 40, 5));
        System.out.println(MultiplesInTheInterval.findDivCount((short) 1, (short) 40, 41));
        System.out.println(MultiplesInTheInterval.findDivCount((short) 40, (short) 1, 5));
        System.out.println(MultiplesInTheInterval.findDivCount((short) 1, (short) 40, 0));
        System.out.println();

        //test concat StringConcatenationMethod class

        //конкатенация
        //когда один из стрингов null
        //когда все sting null

        System.out.println(StringConcatenationMethod.concat("AAA", "BBB", "CCC"));
        System.out.println(StringConcatenationMethod.concat("AAA", null, "CCC"));
        System.out.println(StringConcatenationMethod.concat(null, null, null));
    }
}
