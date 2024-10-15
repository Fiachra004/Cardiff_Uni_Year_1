package Cardiff_University.Year_1.CM1210.CM1210_Exercise_Set_1_Code;

import java.util.Scanner;

public class RewriteNumber {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		System.out.println("Enter an integer between 0 and 9");
		int num = in.nextInt();

		switch (num) {
			case 0:
				System.out.println("You are zero");
			case 1:
				System.out.println("You are one");
			case 2:
				System.out.println("You are two");
				break;
			default:
				System.out.println("Wrong number");
		}

	}
}
