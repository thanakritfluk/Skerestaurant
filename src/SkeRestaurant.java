/**
 * Ske-restaurant can check process that user input and run with interface.
 * @author Thanakrit Daowrong #6010545773
 */

import java.util.Scanner;
import java.io.*;

public class SkeRestaurant {

    public static Scanner sc = new Scanner(System.in);
    private static String choice;
    private static String heart = "\u2665\u2665 \u2665\u2665 \u2665\u2665 \u2665\u2665";
    public static String printCalpart = "| %-20s\t |  %3d  |   %,9.2f     |\n", printTotalpart = "+------------------------+-------+-----------------+\n| Total\t\t\t\t     |\t\t |%,12.2f     |\n+------------------------+-------+-----------------+\n";
    private static int count;
    public static double totalPrice = 0;
    private static RestaurantManager manager = new RestaurantManager();


    private static void menuList() {//Show menu name and other functions.
        System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
        for (int i = 0; i < RestaurantManager.menuList.size() + 1; i++) {

            if (i < RestaurantManager.menuList.size()) {
                System.out.printf("[%d] %-30.20s  %9.2f%n", i + 1, RestaurantManager.menuList.get(i), Double.parseDouble(String.valueOf(RestaurantManager.menuPrice.get(i))));
            }

            if (i == RestaurantManager.menuList.size() - 1) {
                System.out.printf("\n[%s] Cancel order" +
                        "\n[%s] Print order\n[%s] " +
                        "Review order and Checkout" +
                        "\n[%s] Exit " +
                        "\n", "c", "p", "r", "x");
            }
        }

    }

    private static int getQuantity(String prompt) {//Get number from input;
        System.out.print(prompt);
        return sc.nextInt();
    }

    private static void getOrder(String choice) { //Get order from user.
        for (int i = 0; i < RestaurantManager.menuPrice.size(); i++) {
            if (choice.equalsIgnoreCase(Integer.toString(RestaurantManager.menuNum[i]))) {
                RestaurantManager.menuQuantity[i] += getQuantity("Enter Quantity: ");
                sc.nextLine();
            }

        }
    }
    private static void cancelOrder() {//Cancel the order.

        int numCancel = getQuantity("***** Cancel order *****\nEnter number of menu: ");
        sc.nextLine();
        RestaurantManager.menuQuantity[numCancel - 1] = 0;
        System.out.printf("%s were cancle%n", RestaurantManager.menuList.get(numCancel - 1));

    }
    private static void printOrder() { //Print all order.
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

    private static void reviewAndcheckout() throws IOException { //Print order and check out.
        printOrder();
        RestaurantManager.recordOrder();
        for (int i = 0; i < RestaurantManager.menuList.size(); i++) {
            RestaurantManager.menuQuantity[i] = 0;
            totalPrice = 0;
        }
        System.out.printf("\n  " + heart + " Thank You For Your Order " + heart + "\n");
    }



    private static void checkInvalid(String choice) { // Check input right or wrong.
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


    private static void checkProcess() throws IOException { //Run Interface with string that user input.

        while (0 == 0) {

            System.out.printf("%nEnter your Choice: ");
            choice = sc.nextLine();
            getOrder(choice);

            switch (choice) {
                case "c":
                cancelOrder();
                case "p":
                    printOrder();
                    break;
                case "r":
                    reviewAndcheckout();
                    continue;
                case "x":
                    System.exit(0);
                default:
                    checkInvalid(choice);
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
