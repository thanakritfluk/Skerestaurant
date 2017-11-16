/**
 * This class can read menu from txt file,manage size of any variable,record order in to txt file,set menu name and menu prices.
 *
 * @author Thanakrit Daowrueang #6010545773
 */

import java.util.ArrayList;
import java.io.*;

public class RestaurantManager extends SkeRestaurant {

    private static ArrayList<String> list = new ArrayList<>();
    public static ArrayList<String> menuList = new ArrayList<>();
    static ArrayList<Double> menuPrice = new ArrayList<>();
    private static Double[] sumPrice;
    static Integer[] menuNum;
    static int[] menuQuantity;
    private static int orderNumber = 0;


    static void readFile() throws IOException { //Read data from files.
        FileReader file = new FileReader("src\\data\\menu.txt");
        BufferedReader reader = new BufferedReader(file);
        String readline = reader.readLine();
        while (readline != null) {
            if (!readline.startsWith("#")) {
                list.add(readline);
            }
            readline = reader.readLine();
        }

    }

    public static ArrayList getMenu() { //Add menu Items from file.
        for (int i = 0; i < list.size(); i++) {
            menuList.add(list.get(i).split(";")[0]);
        }
        return menuList;
    }

    public static ArrayList getPrice() { //Add menu Price from file.
        for (int i = 0; i < list.size(); i++) {
            menuPrice.add(Double.valueOf(list.get(i).split(";")[1]));
        }
        return menuPrice;
    }

    public static void setData() { //Set size of any data
        menuNum = new Integer[menuPrice.size()];
        menuQuantity = new int[menuPrice.size()];
        sumPrice = new Double[menuPrice.size()];
        for (int i = 0; i < menuPrice.size(); i++) {
            menuNum[i] = i + 1;
            menuQuantity[i] = 0;
            sumPrice[i] = Double.valueOf(0);
        }
    }

    public static int getLastOrder() throws IOException { //Get last order number in record order file.
        FileReader file = new FileReader("src\\data\\recordOrder.txt");
        BufferedReader reader = new BufferedReader(file);
        String readline = reader.readLine();

        while (readline != null) {
            if (readline.startsWith("=")) {
                orderNumber = Integer.parseInt("0");
            } else {
                if (readline.startsWith("OrderNumber")) {
                    String[] input = readline.trim().split(": ");
                    orderNumber = Integer.parseInt(input[1]);
                }
            }
            readline = reader.readLine();
        }
        return orderNumber;
    }


    public static void recordOrder(double total) throws IOException { //Write the order that user input to record order file.
        String outPut = "src/data/recordOrder.txt";
        OutputStream out = null;
        try {
            out = new FileOutputStream(outPut, true);
        } catch (FileNotFoundException ex) {
            System.out.println("Couldn't open output file " + outPut);
            return;
        }
        PrintStream printOut = new PrintStream(out);
        int lastOrder = getLastOrder();
        printOut.printf("\nOrderNumber: %d\n", lastOrder + 1);
        printOut.print("+--------- Menu ---------+- Qty -+----- Price -----+\n");
        for (int j = 0; j < menuPrice.size(); j++) {
            if (menuQuantity[j] != 0) {
                sumPrice[j] = menuQuantity[j] * menuPrice.get(j);
                printOut.printf(printCalpart, menuList.get(j), menuQuantity[j], sumPrice[j]);
            }
        }
        printOut.printf(printTotalpart, total);
        printOut.close();
    }


}