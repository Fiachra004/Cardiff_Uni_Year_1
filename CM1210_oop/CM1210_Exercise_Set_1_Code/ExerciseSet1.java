package Cardiff_University.Year_1.CM1210.CM1210_Exercise_Set_1_Code;

public class ExerciseSet1 {
    public static void main(String args[]) {
        FloatingPointTest();
    }

    public static void FloatingPointTest() {
        float a = 1.36f;
        double b = 1.36;
        System.out.println(a == b);

        float c = 0.1f;
        float d = c + 0.6f;
        System.out.println(d);

        float e = 1 / 49;
        System.out.println(e * 49);

        double f = 1 / 49;
        System.out.println(f * 49);
    }
}
