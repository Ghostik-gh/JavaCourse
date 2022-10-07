public class Task1 {
    public static void main(String[] args) throws Exception {
        print(1, "remainder");
        System.out.println(remainder(1, 3)); // 1
        System.out.println(remainder(3, 4)); // 3
        System.out.println(remainder(-9, 45)); // -9
        System.out.println(remainder(5, 5)); // 0
        print(2, "triArea");
        System.out.println(triArea(3, 2)); // 3
        System.out.println(triArea(7, 4)); // 14
        System.out.println(triArea(10, 10)); // 50
        print(3, "animals");
        System.out.println(animals(2, 3, 5)); // 36
        System.out.println(animals(1, 2, 3)); // 22
        System.out.println(animals(5, 2, 8)); // 50
        print(4, "profitableGamble");
        System.out.println(profitableGamble(0.2, 50, 9)); // true
        System.out.println(profitableGamble(0.9, 1, 2)); // false
        System.out.println(profitableGamble(0.9, 3, 2)); // true
        print(5, "operation");
        System.out.println(operation(24, 15, 9)); // "added"
        System.out.println(operation(24, 26, 2)); // "subtracted"
        System.out.println(operation(15, 11, 11)); // "none"
        print(6, "ctoa");
        System.out.println(ctoa('A')); // 65
        System.out.println(ctoa('m')); // 109
        System.out.println(ctoa('[')); // 91
        System.out.println(ctoa('\\')); // 92
        print(7, "addUpTo");
        System.out.println(addUpTo(3)); // 6
        System.out.println(addUpTo(10)); // 55
        System.out.println(addUpTo(7)); // 28
        print(8, "nextEdge");
        System.out.println(nextEdge(8, 10)); // 17
        System.out.println(nextEdge(5, 7)); // 11
        System.out.println(nextEdge(9, 2)); // 10
        print(9, "sumOfCubes");
        System.out.println(sumOfCubes(new int[] { 1, 5, 9 })); // 855
        System.out.println(sumOfCubes(new int[] { 3, 4, 5 })); // 216
        System.out.println(sumOfCubes(new int[] { 2 })); // 8
        System.out.println(sumOfCubes(new int[] {}));// 0
        print(10, "abcmath");
        System.out.println(abcmath(42, 5, 10));// false
        System.out.println(abcmath(5, 2, 1)); // true
        System.out.println(abcmath(1, 2, 3)); // false
    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(25 - name.length()));
    }

    public static int remainder(int x, int y) {
        return x % y;
    }

    public static double triArea(int x, int y) {
        return x * y * 0.5;
    }

    public static int animals(int chikens, int cows, int pigs) {
        return chikens * 2 + (cows + pigs) * 4;
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
