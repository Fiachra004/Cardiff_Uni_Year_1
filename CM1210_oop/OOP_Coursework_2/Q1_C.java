import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Q1_C {
    public static void main(String[] args) {
        System.out.println("Hybrid Efficiency");
        Q1_B.efficiency("hybrid");
    }

    // Basic insertion sort fucntion that loops through and swaps the values in an
    // array, it does this within a partiton that can be called upon in the hybrid
    // algorithm
    public static void insertionSort(ArrayList<Integer> Array, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int key = Array.get(i);
            int j = i - 1;
            while (j >= left && Array.get(j) > key) {
                Array.set(j + 1, Array.get(j));
                j--;
            }
            Array.set(j + 1, key);
        }
    }

    public static void HybridSort(ArrayList<Integer> dayArray, int left, int right) {
        // Standard recursive sort algorithm similar to that found in the quicksort
        // algorithm however has conditon which leads to inseration sort
        if (right - left > 15) {
            int p = Q1_A.partition(dayArray, left, right);
            HybridSort(dayArray, left, p - 1);
            HybridSort(dayArray, p + 1, right);
        } else {
            // Insertion sort on the partion that is less than 15 hence rapid insertion
            // sorting
            insertionSort(dayArray, left, right);
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