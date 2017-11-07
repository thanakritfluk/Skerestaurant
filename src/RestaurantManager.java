
// @Thanakrit Daowrong 6010545773

import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class RestaurantManager extends SkeRestaurant {

    static ArrayList<String> list = new ArrayList<>(), menuList = new ArrayList<>();
    static ArrayList<Double> menuPrice = new ArrayList<>();
    static Double[] sumPrice;
    static Integer[] menuNum, menuQuantity;

    public static void readFile() throws IOException {

        FileReader file = new FileReader("src\\data\\menu.txt");
        BufferedReader reader = new BufferedReader(file);
        String text = "";
        String readline = reader.readLine();
        while (readline != null) {
            if (!readline.startsWith("#")) {
                list.add(readline);
            }
            readline = reader.readLine();
        }

    }

    public static ArrayList getMenu() {
        for (int i = 0; i < list.size(); i++) {
            menuList.add(list.get(i).split(";")[0]);
        }
        return menuList;
    }

    public static ArrayList getPrice() {
        for (int i = 0; i < list.size(); i++) {
            menuPrice.add(Double.valueOf(list.get(i).split(";")[1]));
        }
        return menuPrice;
    }

    public static void setData() { //set amount of any data
        menuNum = new Integer[menuPrice.size()];
        menuQuantity = new Integer[menuPrice.size()];
        sumPrice = new Double[menuPrice.size()];
        for (int i = 0; i < menuPrice.size(); i++) {
            menuNum[i] = i + 1;
            menuQuantity[i] = 0;
            sumPrice[i] = Double.valueOf(0);
        }
    }


}