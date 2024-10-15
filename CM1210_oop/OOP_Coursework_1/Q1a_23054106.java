package Cardiff_University.Year_1.CM1210.OOP_Coursework_1;

import java.util.Scanner;

public class Q1a_23054106 {

    // Method that only allows for the input of an odd integer value
    public static int getInt(String prompt) {
        Scanner in = new Scanner(System.in);
        System.out.print(prompt + " : ");
        int result;
        while (true) {
            if (in.hasNextInt()) {
                result = in.nextInt();
                if (((result + 1) % 2 == 0) && result >= 1) {
                    break;
                } else {
                    System.out.print(prompt + " : ");
                }
            } else {
                System.out.print(prompt + " : ");
                in.nextLine();
            }
        }
        return result;
    }

    /*
     * As the only goal is to call the magic square, the code can be written within
     * the main method
     * however, a sperate method could have been made and then called within the
     * main class
     */
    public static void main(String[] args) {
        // Uses getInt method to get the odd integer which will be used to create the
        // magic square
        int n = getInt("Enter Odd Integer");
        int[][] myArray = new int[n][n];
        /*
         * Algorithm then slightly changed as pseudocde example does not take into
         * account the different way
         * in which an array is indexed in java starting at 0 instead of 1
         */
        int x = 0;
        int y = (n - 1) / 2;
        myArray[x][y] = 1;
        for (int i = 2; i <= (n * n); i++) {
            int nextX = (x - 1 + n) % n;
            int nextY = (y - 1 + n) % n;

            if (myArray[nextX][nextY] == 0) {
                x = nextX;
                y = nextY;
            } else {
                x = (x + 1) % n;
            }
            myArray[x][y] = i;
        }

        for (int b = 0; b < n; b++) {
            for (int a = 0; a < n; a++) {
                System.out.print(myArray[b][a] + "\t|");
            }
            System.out.println();
        }
    }
}
