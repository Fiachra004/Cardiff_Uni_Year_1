package Cardiff_University.Year_1.CM1210.CM1210_Exercise_Set_1_Code;

import java.util.Scanner;

public class Loops {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("How many numbers would you like to present?");
        int num = in.nextInt();

        System.out.println("Would you like to present it v for vertical or h for horizontal?");
        char cha = in.next().charAt(0);

        System.out.println("Would you like the numbers to be a multiple of you numbers, y or n?");
        char mul = in.next().charAt(0);

        for (int s = 1; s <= num; s++) {
            if (mul == 'y') {
                if (cha == 'v') {
                    System.out.println(s * num);
                } else {
                    System.out.print((s * num) + " ");
                }
            } else {
                if (cha == 'v') {
                    System.out.println(s);
                } else {
                    System.out.print(s + " ");
                }
            }
        }
    }
}
