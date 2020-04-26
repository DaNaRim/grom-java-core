package gromcode.main.lesson21;

public class Company {
    private int numberOfEmployees;
    private String name;
    private static String licence;
    private static int maxNumberOfEmployees = 100;

    static {
        System.out.println("Code block s called");
        init();
    }


    public Company(int numberOfEmployees, String name) throws Exception {
        if (numberOfEmployees > maxNumberOfEmployees) {
            throw new Exception("Company can have max " + maxNumberOfEmployees + " employees");
        }

        this.numberOfEmployees = numberOfEmployees;
        this.name = name;
    }

    private static void init() {
        maxNumberOfEmployees = 100;
    }

    public static void validate() throws Exception {
        if (!licence.equals("TTT111")) throw new Exception("Wrong licence" + licence);
    }

    public static void setLicence(String licence) {
        Company.licence = licence;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getName() {
        return name;
    }

    public static String getLicence() {
        return licence;
    }
//    private static class Test {
//
//
//
//    }
}