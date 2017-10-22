// Ske-restaurant with read menu from txt
// @Thanakrit Daowrong 6010545773
import java.util.ArrayList;
import java.io.*;

public class RestaurantManager {
    static int lastMenu;
    static ArrayList<String> list= new ArrayList<>(),menuList= new ArrayList<>();
    static ArrayList <Double> menuPrice= new ArrayList<>();
    static Double[] sumPrice;
    static Integer[] menuNum,menuQuantity;

    public static void readFile() throws IOException {
        FileReader file = new FileReader("src\\menu.txt");
        BufferedReader reader = new BufferedReader(file);
        String text = "";
        String readline =reader.readLine();
        while(readline!=null){
            list.add(readline);
            readline = reader.readLine();
        }

        for(int i=6;i<list.size();i++) {
            if (i % 2 == 0) {
                menuList.add(list.get(i));
            }
            if(i%2!=0){
                menuPrice.add(Double.parseDouble(list.get(i)));
            }
        }
        menuNum = new Integer[menuPrice.size()];
        menuQuantity = new Integer[menuPrice.size()];
        sumPrice=new Double[menuPrice.size()];
        for(int i=0;i<menuPrice.size();i++) {
            menuNum[i]=i+1;
            menuQuantity[i]=0;
            sumPrice[i] = Double.valueOf(0);
        }
        lastMenu=menuPrice.size();
    }
}
