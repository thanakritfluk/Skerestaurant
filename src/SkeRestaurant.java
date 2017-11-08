
// Ske-restaurant with read menu from txt
// @Thanakrit Daowrong 6010545773

import java.util.Scanner;
import java.io.*;

public class SkeRestaurant {
    private static Scanner sc = new Scanner(System.in);
    private static String choice;
    private static String heart = "\u2665\u2665 \u2665\u2665 \u2665\u2665 \u2665\u2665";
    private static String printCalpart = "| %-20s\t |  %3d  |   %,9.2f     |\n", printTotalpart = "+------------------------+-------+-----------------+\n| Total\t\t\t\t     |\t\t |%,12.2f     |\n+------------------------+-------+-----------------+\n";
    private static int count;
    private static double totalPrice = 0;


    private static void menuList() {

        System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
        for (int i = 0; i < RestaurantManager.menuList.size() + 1; i++) {

            if (i < RestaurantManager.menuList.size()) {
                System.out.printf("[%d] %-30.20s  %9.2f%n", i + 1, RestaurantManager.menuList.get(i), Double.parseDouble(String.valueOf(RestaurantManager.menuPrice.get(i))));
            }

            if (i == RestaurantManager.menuList.size() - 1) {
                System.out.printf("\n[%s] Cancel order\n[%s] Print order\n[%s] Review order and Checkout\n[%s] Exit \n", "c", "p", "r","x");
            }
        }

    }

    private static int getQuantity(String prompt) {

        System.out.print(prompt);
        return sc.nextInt();
    }

    private static void printOrder() {
        totalPrice = 0;
        System.out.print("+--------- Menu ---------+- Qty -+----- Price -----+\n");
        for (int j = 0; j < RestaurantManager.menuPrice.size(); j++) {
            if (RestaurantManager.menuQuantity[j] != 0) {
                RestaurantManager.sumPrice[j] = RestaurantManager.menuQuantity[j] * RestaurantManager.menuPrice.get(j);
                totalPrice += RestaurantManager.sumPrice[j];
                System.out.printf(printCalpart, RestaurantManager.menuList.get(j), RestaurantManager.menuQuantity[j], RestaurantManager.sumPrice[j]);

            }

        }
        System.out.printf(printTotalpart, totalPrice);
    }

    private static void cancelOrder(String choice) {
        if (choice.equalsIgnoreCase("c")) {
            int numCancel = getQuantity("***** Cancel order *****\nEnter number of menu: ");
            sc.nextLine();
            RestaurantManager.menuQuantity[numCancel - 1] = 0;
            System.out.printf("%s were cancle%n", RestaurantManager.menuList.get(numCancel - 1));
        }
    }

    private static void getOrder(String choice) {
        for (int i = 0; i < RestaurantManager.menuPrice.size(); i++) {
            if (choice.equalsIgnoreCase(Integer.toString(RestaurantManager.menuNum[i]))) {
                RestaurantManager.menuQuantity[i] += getQuantity("Enter Quantity: ");
                sc.nextLine();
            }

        }
    }

    private static void checkInvalid(String choice) {
        for (int i = 0; i < RestaurantManager.menuPrice.size(); i++) {
            if (!choice.equalsIgnoreCase("c") && !choice.equalsIgnoreCase("p") && !choice.equalsIgnoreCase("r") && !choice.equalsIgnoreCase("x") && !choice.equalsIgnoreCase(Integer.toString(RestaurantManager.menuNum[i]))) {
                count++;
            }
            if (count == RestaurantManager.menuPrice.size()) {
                System.out.printf("%n==Invalid Menu==%n");
            }
        }
        count = 0;
    }


    private static void checkProcess() {

        while (0 == 0) {
            System.out.printf("%nEnter your Choice: ");
            choice = sc.nextLine();

            getOrder(choice);

            if (choice.equalsIgnoreCase("p")) {
                printOrder();
            }

            if (choice.equalsIgnoreCase("r")) {
                printOrder();
                System.out.printf("\n  " + heart + " Thank You For Your Order " + heart +"\n");
                continue;
            }

            cancelOrder(choice);

            checkInvalid(choice);

            if(choice.equalsIgnoreCase("x")){
                break;
            }

        }
    }

    public static void main(String[] args) throws IOException {

        RestaurantManager.readFile();
        RestaurantManager.getPrice();
        RestaurantManager.getMenu();
        RestaurantManager.setData();
        menuList();
        checkProcess();

    }

}
