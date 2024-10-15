import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileWriter;
import java.io.IOException;

public class Q1_A {
    public static void main(String[] args) {
        String[] day_names = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        int[] day_values = { 1000, 5000, 10000, 50000, 75000, 100000, 500000 };
        clearCSV("sorted.csv");
        clearCSV("Unsorted.csv");
        for (int i = 0; i < day_values.length; i++) {
            Day random = new Day(day_values[i], "");
            writeArrayToCSV(random.Array, day_names[i], "unsorted.csv");
            System.out.print("[");
            for (int j = 0; j < 1000; j++) {
                System.out.print(random.Array.get(j) + ", ");
            }
            System.out.println("] \n");
            quickSort(random.Array, 0, random.Array.size() - 1);
            writeArrayToCSV(random.Array, day_names[i], "sorted.csv");
            System.out.print("[");
            for (int j = 0; j < 1000; j++) {
                System.out.print(random.Array.get(j) + ", ");
            }
            System.out.println("] \n");
        }
    }

    // quicksort function which calls the partions function which is the main
    // feature of a quicksort algorithm
    public static ArrayList<Integer> quickSort(ArrayList<Integer> dayArray, int left, int right) {
        // partition(dayArray, left, right);
        if (left < right) {
            int p = partition(dayArray, left, right);
            // quicksort being called either side of the pivot value of the last partion
            // making this function recursive
            quickSort(dayArray, left, p - 1);
            quickSort(dayArray, p, right);
        }
        return dayArray;

    }

    public static int partition(ArrayList<Integer> dayArray, int left, int right) {
        // pivot of the middle value which makes it more efficient especially for
        // reverse sorted list instead of choosing one side
        int mid = (left + right) / 2;
        int pivot = dayArray.get(mid);

        int l = left;
        int r = right;
        // this will loop over specified section of the Array and will left counter
        // meets the right marker, whilst swapping values either side of the
        // pivot
        while (l <= r) {
            while (dayArray.get(l) < pivot) {
                l++;
            }
            while (dayArray.get(r) > pivot) {
                r--;
            }
            // swapping section which swaps the values, then moves to the next sections on
            // the pivot
            if (l <= r) {
                int temp = dayArray.get(l);
                dayArray.set(l, dayArray.get(r));
                dayArray.set(r, temp);
                l++;
                r--;

            }
        }
        // this returns the 'middle' value or the pivot
        return l;
    }

    public static void writeArrayToCSV(ArrayList<Integer> array, String Day, String filePath) {
        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(Day + ",");
            for (int i = 0; i < array.size(); i++) {
                writer.append(String.valueOf(array.get(i)) + ",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearCSV(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.append("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class Day {
    Random rand = new Random();
    ArrayList<Integer> Array = new ArrayList<Integer>();

    // will change the contents of the list dependent of the number and words passed
    Day(int size, String sort) {
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            Array.add(random.nextInt(500001 - 1000) + 1000);
        }
        if (sort == "sorted") {
            Collections.sort(Array);
        } else if (sort == "reverse") {
            Collections.sort(Array, Collections.reverseOrder());
        }
    }

}
