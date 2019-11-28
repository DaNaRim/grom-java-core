package lesson5;

import java.util.Arrays;

public class BanksPractice {
    public static void main(String[] args) {
        String[] names = {"Jack", "Ann", "Denis", "Andrey", "Nikolay", "Irina", "John"};
        int[] balances = {100, 500, 8432, -99, 1200, -54, 0};

        /*System.out.println(Arrays.toString(findClientsByBalance(names, balances, -100)));
        System.out.println(Arrays.toString(findClientsWithNegativeBalance(names, balances)));

        depositMoney(names, balances, "Ann", 2000);
        System.out.println(Arrays.toString(balances));*/
        System.out.println(withdraw(names, balances, "Ann", 600));
    }

    static String[] findClientsByBalance(String[] clients, int[] balances, int n) {
        //String[] result = new String[];
        int count = 0;
        for (int balance : balances) {
            count = balance >= n ? count + 1 : count;
        }
        String[] results = new String[count];

        int index = 0, resIndex = 0;
        for (int balance : balances) {
            if (balance >= n) {
                results[resIndex] = clients[index];
                resIndex++;
            }
            index++;
        }
        return results;
    }

    static String[] findClientsWithNegativeBalance(String[] clients, int[] balances) {
        //String[] result = new String[];
        int count = 0;
        for (int balance : balances) {
            count = balance < 0 ? count + 1 : count;
        }
        String[] results = new String[count];

        int index = 0, resIndex = 0;
        for (int balance : balances) {
            if (balance < 0) {
                results[resIndex] = clients[index];
                resIndex++;
            }
            index++;
        }
        return results;
    }

    static void depositMoney(String[] clients, int[] balances, String client, int money) {

        balances[findClientIndexByName(clients, client)] += calculateDepositAmountAfterCommission(money);
    }

    static int findClientIndexByName(String[] clients, String client) {
        int clientIndex = 0;
        for (String cl : clients) {
            if (cl == client) {
                break;
            }
            clientIndex++;
        }
        return clientIndex;
    }

    static int calculateDepositAmountAfterCommission(int money) {
        return money <= 100 ? (int) (money - money * 0.02) : (int) (money - money * 0.01);
    }

    static int withdraw(String[] clients, int[] balances, String client, int amount) {
        int clientIndex = 0;
        for (String cl : clients) {
            if (cl == client) {
                break;
            }
            clientIndex++;
        }

        if (balances[clientIndex] > amount) {
            balances[clientIndex] -= amount;
            return balances[clientIndex];
        } else {
            return -1;
        }


    }

}
