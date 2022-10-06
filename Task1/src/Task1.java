public class Task1 {
    public static void main(String[] args) throws Exception {
        System.out.println(addUpTo(10));
    }

    public static int remainder(int x, int y) {
        return x % y;
    }

    public static double triArea(int x, int y) {
        return x * y * 0.5;
    }

    public static int animals(int chikens, int cows, int pigs) {
        return chikens * 2 + (cows + pigs) * 2;
    }

    public static boolean profitableGamble(double prob, double prize, double pay) {
        return prob * prize > pay ? true : false;
    }

    public static String operation(int N, int a, int b) {
        String s = "none";
        if (a + b == N)
            s = "added";
        if (a - b == N || b - a == N)
            s = "subtracted";
        if (a * b == N)
            s = "product";
        if (a / b == N || b / a == N)
            s = "division";

        return s;
    }

    public static int ctoa(char c) {

        return (int) c;
    }

    public static int addUpTo(int n) {
        return (1 + n) * n / 2;
    }

    public static int nextEdge(int a, int b) {
        return a + b - 1;
    }

    public static int sumOfCubes(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i] * arr[i] * arr[i];
        }
        return sum;
    }

    public static boolean abcmath(int a, int b, int c) {
        int sum = a;
        for (int i = 0; i < b; i++) {
            sum += sum;
        }

        return remainder(sum, c) == 0 ? true : false;
    }
}
