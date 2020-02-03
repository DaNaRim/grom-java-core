package lesson25.homework;

import java.util.Arrays;

class Test1 {
    private int testInt;

    public Test1(int testInt) {
        this.testInt = testInt;
    }
}

class Test2 {
    private final String testString = "test";
    private int testInt = 0;

    public Test2(int testInt) {
        this.testInt = testInt;
    }
}

class Test3 {
    private long testLong;
    private short testShort;
    private String testString;

    public Test3(long testLong, short testShort, String testString) {
        this.testLong = testLong;
        this.testShort = testShort;
        this.testString = testString;
    }
}

public class Demo {

    public static void main(String[] args) {
        Test1 test1 = new Test1(0);
        Test2 test2 = new Test2(2134);
        Test3 test3 = new Test3(444, Short.MAX_VALUE, "111");

        GeneralDAO<Test1> gTest1 = new GeneralDAO<>();
        try {
            gTest1.save(test1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(gTest1.getAll()));

        GeneralDAO<Test2> gTest2 = new GeneralDAO<>();
        try {
            gTest2.save(test2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(gTest2.getAll()));

        GeneralDAO<Test3> gTest3 = new GeneralDAO<>();
        try {
            gTest3.save(test3);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println(Arrays.toString(gTest3.getAll()));

    }
}
