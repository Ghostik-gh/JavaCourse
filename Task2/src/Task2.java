public class Task2 {
    public static void main(String[] args) throws Exception {
        print(1, "repeat");
        System.out.println(repeat("mice", 5));// "mmmmmiiiiiccccceeeee"
        System.out.println(repeat("hello", 3)); // "hhheeellllllooo"
        System.out.println(repeat("stop", 1));// "stop"
        print(2, "differenceMaxMin");
        System.out.println(differenceMaxMin(new int[] { 10, 4, 1, 4, -10, -50, 32, 21 })); // 82
        System.out.println(differenceMaxMin(new int[] { 44, 32, 86, 19 })); // 67
        print(3, "isAvgWhole");
        System.out.println(isAvgWhole(new int[] { 1, 3 })); // true
        System.out.println(isAvgWhole(new int[] { 1, 2, 3, 4 })); // false
        System.out.println(isAvgWhole(new int[] { 1, 5, 6 })); // true
        System.out.println(isAvgWhole(new int[] { 1, 1, 1 })); // true
        System.out.println(isAvgWhole(new int[] { 9, 2, 2, 5 }));// false
        print(4, "cumulativeSum");
        int[][] tests = new int[][] {
                new int[] { 1, 2, 3 }, // [1, 3, 6]
                new int[] { 1, -2, 3 }, // [1, -1, 2]
                new int[] { 3, 3, -2, 408, 3, 3 } // [3, 6, 4, 412, 415, 418]
        };
        for (int i = 0; i < 3; i++) {
            int[] arr = cumulativeSum(tests[i]);
            System.out.print("Test " + (int) (i + 1) + " : ");
            if (arr.length == 0) {
                System.out.println("[]");
            } else if (arr.length == 1) {
                System.out.println("[" + arr[0] + "]");
            } else {
                System.out.print("[");
                for (int j = 0; j < arr.length - 1; j++) {
                    System.out.print(arr[j] + ", ");
                }
                System.out.print(arr[arr.length - 1]);
                System.out.println("]");
            }
        }
        print(5, "getDecimalPlaces");
        System.out.println(getDecimalPlaces("43.20")); // 2
        System.out.println(getDecimalPlaces("400")); // 0
        System.out.println(getDecimalPlaces("3.1")); // 1

        print(6, "fibonacci");
        System.out.println(fibonacci(3));// 3
        System.out.println(fibonacci(7));// 21
        System.out.println(fibonacci(12));// 233
        print(7, "isValid");
        System.out.println(isValid("59001"));// true
        System.out.println(isValid("853a7"));// false
        System.out.println(isValid("732 32"));// false
        System.out.println(isValid("393939"));// false

        print(8, "isStrangePair");
        System.out.println(isStrangePair("ratio", "orator")); // true
        System.out.println(isStrangePair("sparkling", "groups")); // true
        System.out.println(isStrangePair("bush", "hubris")); // false
        System.out.println(isStrangePair("", "")); // true

        print(9, "Prefix & Suffix");
        System.out.println(isPrefix("automation", "auto-")); // true
        System.out.println(isSuffix("arachnophobia", "-phobia")); // true
        System.out.println(isPrefix("retrospect", "sub-")); // false
        System.out.println(isSuffix("vocation", "-logy")); // false

        print(10, "boxSeq");
        System.out.println(boxSeq(1)); // 3
        System.out.println(boxSeq(2)); // 2
        System.out.println(boxSeq(0)); // 0
    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(25 - name.length()));
    }

    public static String repeat(String s, int n) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            ans += (s.charAt(i) + "").repeat(n);
        }
        return ans;
    }

    public static int differenceMaxMin(int[] arr) {
        if (arr.length == 0)
            return -1;
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        return max - min;
    }

    public static boolean isAvgWhole(int[] arr) {
        int sum = 0;
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
        }

        return (sum % size == 0) ? true : false;
    }

    public static int[] cumulativeSum(int[] arr) {
        if (arr.length == 0) {
            return new int[] {};
        }
        int[] mas = new int[arr.length];
        mas[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            mas[i] += mas[i - 1] + arr[i];
        }
        return mas;
    }

    public static int getDecimalPlaces(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') {
                return s.length() - i - 1;
            }
        }
        return 0;
    }

    public static int fibonacci(int n) {
        if (n == 0) {
            return -1;
        } else if (n < 2) {
            return 1;
        }
        int[] fib = new int[n + 1];
        fib[0] = 1;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    public static boolean isValid(String s) {
        // TODO: regex
        return s.matches("\\d{5}") ? true : false;
    }

    public static boolean isStrangePair(String s1, String s2) {
        if (s1.length() == 0 && s2.length() == 0) {
            return true;
        }
        if (s1.length() == 0 || s2.length() == 0) {
            return false;
        }
        if (s1.charAt(0) == s2.charAt(s2.length() - 1) &&
                s2.charAt(0) == s1.charAt(s1.length() - 1)) {
            return true;
        }
        return false;
    }

    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(String.copyValueOf(prefix.toCharArray(), 0, prefix.length() - 1)) ? true : false;
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.endsWith(String.copyValueOf(suffix.toCharArray(), 1, suffix.length() - 1)) ? true : false;
    }

    public static int boxSeq(int n) {
        int ans = 0;
        if (n % 2 == 0)
            ans = n;
        else
            ans = n + 2;

        return ans;
    }
}
