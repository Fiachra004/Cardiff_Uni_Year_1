package Cardiff_University.Year_1.CM1210.CM1210_Exercise_Set_1_Code;

import java.util.Scanner;

public class Stars {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("How wide would you like your box to be? ");
        int w = input.nextInt();

        System.out.println("How long would you like your box to be?");
        int l = input.nextInt();

        for (int a = 1; a <= l; a++) {
            for (int b = 1; b <= w; b++) {
                System.out.print("*");
            }
            System.out.println("");
        }

    }
}
