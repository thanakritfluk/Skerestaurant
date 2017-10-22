
// Ske-restaurant with read menu from txt
// @Thanakrit Daowrong 6010545773

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class SkeRestaurant {
        static Scanner sc = new Scanner(System.in);

        static int  choice,lastMenu;
        static double totalPrice=0;
        static String heart="\u2665\u2665 \u2665\u2665 \u2665\u2665 \u2665\u2665";
        static String   printCalpart="| %-20s\t |  %3d  |   %9.2f     |\n", printTotalpart ="+------------------------+-------+-----------------+\n| Total\t\t\t\t     |\t\t |%12.2f     |\n+------------------------+-------+-----------------+\n";
        static ArrayList<String> list= new ArrayList<>(),menuList= new ArrayList<>();
        static ArrayList <Double> menuPrice= new ArrayList<>();
        static Double[] sumPrice;
        static Integer[] menuNum,menuQuantity;

        public static int getQuatity(String prompt){
            System.out.print(prompt);
            return sc.nextInt();
        }

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

        public static void editOrder(int choice){
            if(choice==lastMenu+1){
                int numChange=getQuatity("Enter number of menu:");
                sc.nextLine();
                menuQuantity[numChange-1]=getQuatity("Change to:");
                System.out.printf("%s were change to %d%n",menuList.get(numChange-1),menuQuantity[numChange-1]);
            }
        }
        public static void printOrder(){
            totalPrice=0;
            System.out.print("+--------- Menu ---------+- Qty -+----- Price -----+\n");
            for(int j=0;j<menuPrice.size();j++) {
                if(menuQuantity[j]!=0){
                    sumPrice[j]=menuQuantity[j]*menuPrice.get(j);
                    totalPrice +=sumPrice[j];
                    System.out.printf(printCalpart,menuList.get(j),menuQuantity[j],sumPrice[j]);

                }

            }
            System.out.printf(printTotalpart,totalPrice);
        }

        public static void cancelOrder(int choice){
            if(choice==lastMenu+4){
                int numCancel=getQuatity("Enter number of menu:");
                sc.nextLine();
                menuQuantity[numCancel-1]=0;
                System.out.printf("%s were cancle%n",menuList.get(numCancel-1));
            }
        }


        public static  void checkProcess(){
            while (0 == 0) {
                System.out.printf("%nEnter your Choice: ");
                choice = sc.nextInt();

                for(int i=0;i<menuPrice.size();i++) {
                    if (choice==menuNum[i]) {
                        menuQuantity[i]+=getQuatity("Enter Quantity: ");
                        sc.nextLine();
                    }

                }

                editOrder(choice);

                if(choice==lastMenu+2){
                    printOrder();
                }

                if(choice==lastMenu+3){
                    printOrder();
                    System.out.printf("\n  "+heart+" Thank You For Your Order "+heart);
                    break;
                }

                cancelOrder(choice);

            }
        }

        static void SkeRestaurant() throws IOException {
            readFile();
            System.out.printf("--------- Welcome to SKE Restaurant ---------%n");
            for(int i=0;i<menuList.size()+1;i++){
                if(i<menuList.size()){
                    System.out.printf("[%d] %-30.20s  %9.2f%n",i+1,menuList.get(i),Double.parseDouble(String.valueOf(menuPrice.get(i))));
                }
                if(i==menuList.size()-1){
                    System.out.printf("%n[%d] Edit order\n[%d] Print order\n[%d] Review order and Checkout\n[%d] Cancel order",lastMenu+1,lastMenu+2,lastMenu+3,lastMenu+4);
                }
            }

            checkProcess();
        }

        public static void main(String[] args) throws IOException {
            SkeRestaurant();

        }

    }
