package main.lesson09;

import main.lesson09.utils.Checker;

public class Demo9 {

    public static void main(String[] args) {
        Company company = new Company("NewValue", "IBM");
        System.out.println(company.getCountryFounded());
        System.out.println(company.getName());

        Checker checker = new Checker();
        System.out.println(checker.checkCompanyName(company.getName()));
    }
}
