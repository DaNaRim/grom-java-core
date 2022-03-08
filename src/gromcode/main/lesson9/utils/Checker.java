package gromcode.main.lesson9.utils;

public class Checker {
    //public
    //private - 90+%
    //default (package-private)
    //protected

    //private
    //default (package-private)
    //protected
    //public

    int companyNamesValidatedCount = 0;

    public boolean checkCompanyName(String companyName) {
        if (companyNamesValidatedCount > 10) return false;
        companyNamesValidatedCount++;
        return companyName != "Google" || companyName != "Amazon";
    }
}
