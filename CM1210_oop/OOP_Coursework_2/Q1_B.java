import java.util.ArrayList;

public class Q1_B {
    public static void main(String[] args) {
        System.out.println("Quicksort Efficiency");
        efficiency("quicksort");
    }

    public static void efficiency(String sorting) {
        // arrays filled that allow for data to be presented
        String[] day = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" };
        int[] day_values = { 1000, 5000, 10000, 50000, 75000, 100000, 500000 };
        for (int i = 0; i < day.length; i++) {
            int sorted_time = 0, reverse_time = 0, random_time = 0;
            for (int j = 0; j < 100; j++) {
                // creating new arrays each time so that they do not remain the same each time
                // effciency is checked
                Day sorted = new Day(day_values[i], "");
                Day random = new Day(day_values[i], "random");
                Day reverse = new Day(day_values[i], "reverse");
                // time taken to total time so average can be found
                sorted_time += timeTaken(sorted.Array, sorting);
                reverse_time += timeTaken(random.Array, sorting);
                random_time += timeTaken(reverse.Array, sorting);
            }
            // this is how the data is presented witht he time being divided by the number
            // of times in which it was looped
            System.out.println(day[i] + " a sorted List will tak " + sorted_time / 100 + " milliseconds to sort.");
            System.out.println(day[i] + " a random list will take " + reverse_time / 100 + " milliseconds to sort.");
            System.out.println(day[i] + " a reverse list will take " + random_time / 100 + " milliseconds to sort.");

        }
    }

    // java is sequential so we can measure time before sorting and after to find
    // how long it took
    public static long timeTaken(ArrayList<Integer> array, String sorting) {
        long start = System.currentTimeMillis();
        if (sorting == "quicksort") {
            Q1_A.quickSort(array, 0, array.size() - 1);
        } else {
            Q1_C.HybridSort(array, 0, array.size() - 1);
        }
        long finish = System.currentTimeMillis();
        long total = finish - start;
        return total;
    }
}
