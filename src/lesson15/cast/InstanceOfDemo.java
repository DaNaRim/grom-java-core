package lesson15.cast;

public class InstanceOfDemo {
    public static void main(String[] args) {
        Provider provider = new Provider();
        InternetProvider internetProvider = new InternetProvider();
        FoodProvider foodProvider = new FoodProvider();

        System.out.println(provider instanceof Provider);
        System.out.println(provider instanceof InternetProvider);
        System.out.println(provider instanceof FoodProvider);
        System.out.println(internetProvider instanceof Provider);

        if (test() instanceof InternetProvider) {
            System.out.println(test());
            //
        } else if (test() instanceof FoodProvider) {
            //
            System.out.println(test());
        }
    }

    private static Provider test(){
        //logic

        return new InternetProvider();
    }
}
