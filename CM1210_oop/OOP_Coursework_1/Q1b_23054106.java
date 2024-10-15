package Cardiff_University.Year_1.CM1210.OOP_Coursework_1;

import java.util.*;
import java.util.regex.*;

public class Q1b_23054106 {
    // main method that calls method which allow the game to run, main game within a
    // do while loop
    public static void main(String[] args) {
        int counter = 0;
        int n = getInt("Enter Odd Integer");
        int[][] shuffledArray = shuffleArray(magicSquare(n));
        printArray(shuffledArray);
        int[][] changedArray = shuffledArray;
        do {
            changedArray = changeArray(changedArray);
            counter += 1;
        } while (checkArray(changedArray) == false);
        System.out.println("Well done you have matched the magic square");
        System.out.println("It took you " + counter + " moves to complete");
    }

    // method used to check that the input is both odd and integer, if not then it
    // will continue to print the prompt
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
                    System.out.print("\n" + prompt + " : ");
                }
            } else {
                System.out.print("\n" + prompt + " : ");
                in.nextLine();
            }
        }
        return result;
    }

    // method for printing array in athetic format, so all values are evenly spaced
    // between each other
    public static void printArray(int[][] array) {
        int n = array.length;
        for (int b = 0; b < n; b++) {
            for (int a = 0; a < n; a++) {
                System.out.print(array[b][a] + "\t|");
            }
            System.out.println();
        }
    }

    // method from 1a which takes n as an argument then creates the magic square the
    // get the specific values then returns the array
    public static int[][] magicSquare(int n) {
        int[][] myArray = new int[n][n];
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
        return myArray;
    }

    // method to shuffle array n * n times to make the numbers in the array in
    // random orders
    public static int[][] shuffleArray(int[][] array) {
        int x, y, randInt, temp;
        Random rand = new Random();
        // as we don't need the original array to remain the same, we can shuffle the
        // referenced array as this has not affect on end outcome
        int[][] newArray = array;
        int n = newArray.length;
        // i begins at 0 so even though it is less than n * n it will still loop the
        // same amount of times
        for (int i = 0; i < (n * n); i++) {
            x = rand.nextInt(n);
            y = rand.nextInt(n);
            randInt = rand.nextInt(4);
            temp = newArray[x][y];
            // random int means we will randomly choose a case that will shuffle our square
            switch (randInt) {
                case 0:
                    if (x == 0) {
                        newArray[x][y] = newArray[x + 1][y];
                        newArray[x + 1][y] = temp;
                    } else {
                        newArray[x][y] = newArray[x - 1][y];
                        newArray[x - 1][y] = temp;
                    }
                    break;
                case 1:
                    if (x == n - 1) {
                        newArray[x][y] = newArray[x - 1][y];
                        newArray[x - 1][y] = temp;
                    } else {
                        newArray[x][y] = newArray[x + 1][y];
                        newArray[x + 1][y] = temp;
                    }
                    break;
                case 2:
                    if (y == 0) {
                        newArray[x][y] = newArray[x][y + 1];
                        newArray[x][y + 1] = temp;
                    } else {
                        newArray[x][y] = newArray[x][y - 1];
                        newArray[x][y - 1] = temp;
                    }
                    break;
                case 3:
                    if (y == n - 1) {
                        newArray[x][y] = newArray[x][y - 1];
                        newArray[x][y - 1] = temp;
                    } else {
                        newArray[x][y] = newArray[x][y + 1];
                        newArray[x][y + 1] = temp;
                    }
                    break;
            }
        }
        return newArray;
    }

    /*
     * Valid input ensure that the input is in the formate i j Direction, where i
     * and j are integers within range, and D is direction which can be either U D L
     * or R
     */
    public static boolean ValidInput(String input, int[][] array) {
        int n = array.length;
        // regex refers to regular expression where d+ represents digits s is whitespace
        // and [U|D|L|R] represents the final input you can have
        String regex = "^(\\d+)\\s(\\d+)\\s[U|D|L|R]$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (!matcher.matches()) {
            return false;
        }
        // this turns the first two inputs into integers that can then be checked to
        // ensure they are within the correct range
        int i = Integer.parseInt(matcher.group(1));
        int j = Integer.parseInt(matcher.group(2));

        if (i < 1 || i > n || j < 1 || j > n) {
            return false;
        }
        return true;
    }

    // Method to change the array to make it so that
    public static int[][] changeArray(int[][] array) {
        int i, j, n, b, tempInt;
        n = array.length - 1;
        // we can reference the array directly as we are not going to need the original
        // array that is used in the argument.
        int[][] newArray = array;
        String input = "";
        Scanner in = new Scanner(System.in);
        // loop checks that the input is in the correct format, if it is not it will
        // repeat until do while loop is broken.
        do {
            System.out.print("Input in format: i j Direction (U, D, L, R) : ");
            input = in.nextLine();
        } while ((ValidInput(input, newArray) == false));
        String[] strArray = input.split(" ");
        i = (Integer.parseInt(strArray[0]) - 1);
        j = (Integer.parseInt(strArray[1]) - 1);
        String direction = strArray[2];
        tempInt = newArray[i][j];
        /*
         * As there are only four direction in which they can move we can use switch
         * case statement and we can
         * wrap around if the values are on the edges of the square
         */
        switch (direction) {
            case ("U"):
                if (i == 0) {
                    newArray[i][j] = newArray[n][j];
                    newArray[n][j] = tempInt;
                } else {
                    newArray[i][j] = newArray[i - 1][j];
                    newArray[i - 1][j] = tempInt;
                }
                break;
            case ("D"):
                if (i == n) {
                    newArray[i][j] = newArray[0][j];
                    newArray[0][j] = tempInt;
                } else {
                    newArray[i][j] = newArray[i + 1][j];
                    newArray[i + 1][j] = tempInt;
                }
                break;
            case ("L"):
                if (j == 0) {
                    newArray[i][j] = newArray[i][n];
                    newArray[i][n] = tempInt;
                } else {
                    newArray[i][j] = newArray[i][j - 1];
                    newArray[i][j - 1] = tempInt;
                }
                break;
            case ("R"):
                if (j == n) {
                    newArray[i][j] = newArray[i][0];
                    newArray[i][0] = tempInt;
                } else {
                    newArray[i][j] = newArray[i][j + 1];
                    newArray[i][j + 1] = tempInt;
                }
                break;
        }
        printArray(newArray);
        return newArray;
    }

    // method to check that all coloumns, rows and diagonals add up the the same
    // number
    public static boolean checkArray(int[][] array) {
        int n, b, Row, Col, Diagonal1 = 0, Diagonal2 = 0;
        n = array.length;
        b = (n * ((n * n) + 1)) / 2;
        boolean isEqual = true;
        for (int i = 0; i < n; i++) {
            Row = 0;
            Col = 0;
            for (int j = 0; j < n; j++) {
                Row += array[i][j];
                Col += array[j][i];
            }
            if (Row != b || Col != b) {
                isEqual = false;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            Diagonal1 += array[i][i];
        }
        for (int i = 0; i < n; i++) {
            Diagonal2 += array[i][n - 1 - i];
        }
        if (Diagonal1 != b || Diagonal2 != b) {
            isEqual = false;
        }
        return isEqual;
    }
}