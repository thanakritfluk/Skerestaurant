

//elab source:Homework.java
// @Thanakrit Daowrong 6010545773
import java.util.Scanner;

public class Homework {
	static Scanner  sc = new Scanner(System.in);

	static int      choice, a1 = 0, a2 = 0, a3 = 0;
	static String   printCalpart="| %-10s\t\t |  %3d  |   %5d       |\n", printTotalpart ="+--------------------+-------+---------------+\n| Total\t\t\t\t |\t\t |   %5d       |\n+--------------------+-------+---------------+\n";

	static String[] Menu = {"Pizza","Chicken","Coke"};
	static int   [] Quantity = {a1,a2,a3};
	static int   [] Price = {250,120,45};

	public static int getQuatity(){
		return sc.nextInt();
	}

	public static String Calculat(int a1, int a2, int a3) {

		String total = "+------- Menu -------+- Qty -+-- Price ------+\n";
		if (a1 != 0) {
			total += printCalpart;
		}
		if (a2 != 0) {
			total += printCalpart;
		}
		if (a3 != 0) {
			total += printCalpart;
		}

		return total;

	}

	static void SkeRestaurant() {
		System.out.printf("--------- Welcome to SKE Restaurant ---------%n"
				+ "1.) %-10s\t%3d Baht.%n"
				+ "2.) %-10s\t%3d Baht.%n"
				+ "3.) %-10s\t%3d Baht.%n"
				+ "4.) Total%n5.) Exit%n"
				,Menu[0],Price[0],Menu[1],Price[1],Menu[2],Price[2]);

		while (0 == 0) {
			System.out.printf("%nEnter your Choice: ");
			choice = sc.nextInt();
			if (choice == 5) {
				System.out.print("=====Thank you=====");
				break;
			}

			switch (choice) {
			case 1:
				System.out.print("Enter Quantity: ");
				Quantity[0] += getQuatity();
				break;
			case 2:
				System.out.print("Enter Quantity: ");
				Quantity[1] += getQuatity();
				break;
			case 3:
				System.out.print("Enter Quantity: ");
				Quantity[2] += getQuatity();
				break;
			case 4:
				int sumPrice0 = Quantity[0] * Price[0];
				int sumPrice1 = Quantity[1] * Price[1];
				int sumPrice2 = Quantity[2] * Price[2];
				String calculatPart = Calculat(Quantity[0], Quantity[1], Quantity[2]);

				System.out.printf(calculatPart, Menu[0] , Quantity[0], sumPrice0,
												Menu[1] , Quantity[1], sumPrice1,
												Menu[2] , Quantity[2], sumPrice2);

				System.out.printf(printTotalpart,sumPrice0+sumPrice1+sumPrice2);
				break;
			}

		}
	}

	public static void main(String[] args) {
		SkeRestaurant();
	}

}
