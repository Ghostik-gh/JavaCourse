import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;

public class App {
    public static void main(String[] args) throws Exception {
        print(1, "solutions");
        System.out.println(solutions(1, 0, -1)); // 2
        System.out.println(solutions(1, 0, 0)); // 1
        System.out.println(solutions(1, 0, 1)); // 0

        print(2, "findZip");
        System.out.println(findZip("all zip files are zipped")); // 18
        System.out.println(findZip("all zip files are compressed")); // -1

        print(3, "checkPerfect");
        System.out.println(checkPerfect(6)); // true
        System.out.println(checkPerfect(28)); // true
        System.out.println(checkPerfect(496)); // true
        System.out.println(checkPerfect(12)); // false
        System.out.println(checkPerfect(97)); // false

        print(4, "flipEndChars");
        System.out.println(flipEndChars("Cat, dog, and mouse.")); // ".at, dog, and mouseC"
        System.out.println(flipEndChars("ada")); // "Two's a pair."
        System.out.println(flipEndChars("Ada")); // "adA"
        System.out.println(flipEndChars("z")); // "Incompatible."

        print(5, "isValidHexCode");
        System.out.println(isValidHexCode("#CD5C5C")); // ➞ true
        System.out.println(isValidHexCode("#EAECEE")); // ➞ true
        System.out.println(isValidHexCode("#eaecee")); // ➞ true
        System.out.println(isValidHexCode("#CD5C58C")); // ➞ false
        System.out.println(isValidHexCode("#CD5C5Z")); // ➞ false
        System.out.println(isValidHexCode("#CD5C&C")); // ➞ false
        System.out.println(isValidHexCode("CD5C5C")); // ➞ false

        print(6, "same");
        System.out.println(same(new Integer[] { 1, 3, 4, 4, 4 }, new Integer[] { 2, 5, 7 })); // ➞ true
        System.out.println(same(new Integer[] { 9, 8, 7, 6 }, new Integer[] { 4, 4, 3, 1 })); // ➞ false
        System.out.println(same(new Integer[] { 2 }, new Integer[] { 3, 3, 3, 3, 3 })); // ➞ true

        print(7, "isKaprekar");
        System.out.println(isKaprekar(3)); // false
        System.out.println(isKaprekar(5)); // false
        System.out.println(isKaprekar(297)); // ➞ true

        print(8, "longestZero");
        System.out.println(longestZero("01100001011000")); // "0000"
        System.out.println(longestZero("100100100")); // "00"
        System.out.println(longestZero("11111")); // ""

        print(9, "nextPrime");
        System.out.println(nextPrime(12)); // ➞ 13
        System.out.println(nextPrime(24)); // ➞ 29
        System.out.println(nextPrime(11)); // ➞ 11

        print(10, "rightTriangle");
        System.out.println(rightTriangle(3, 4, 5)); // ➞ true
        System.out.println(rightTriangle(145, 105, 100)); // ➞ true
        System.out.println(rightTriangle(70, 130, 110)); // ➞ false
    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(25 - name.length()));
    }

    /*
     * return
     */
    public static int solutions(int a, int b, int c) {
        double d = b * b - 4 * a * c;
        if (a == 0 || d == 0) {
            return 1;
        } else if (d > 0) {
            return 2;
        }
        return 0;
    }

    /*
     * return index second includes substring "zip"
     * or -1 if substringl less then 2
     */
    public static int findZip(String s) {
        boolean first = false;
        if (s.length() < 5) {
            return -1;
        }
        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == 'p' && s.charAt(i - 1) == 'i' && s.charAt(i - 2) == 'z') {
                if (first)
                    return i - 2;
                first = true;
            }
        }
        return -1;
    }

    public static boolean checkPerfect(int x) {
        int sum = 0;
        for (int i = 1; i < x; i++) {
            if (x % i == 0)
                sum += i;
        }
        return sum == x ? true : false;
    }

    public static String flipEndChars(String s) {
        if (s.length() < 2) {
            return "Incompatible.";
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return "Two's a pair.";
        }
        return s.charAt(s.length() - 1) + "" + s.subSequence(1, s.length() - 1) + s.charAt(0);
    }

    public static boolean isValidHexCode(String s) {
        // Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
        return s.matches("^#[a-fA-F0-9]{6}") ? true : false;
    }

    public static boolean same(Integer[] arr1, Integer[] arr2) {
        // List<Integer> list1 = Arrays.asList(arr1);
        Set<Integer> set1 = new TreeSet<Integer>();
        set1.addAll(Arrays.asList(arr1));
        Set<Integer> set2 = new TreeSet<Integer>();
        set2.addAll(Arrays.asList(arr2));

        return set1.size() == set2.size() ? true : false;
    }

    public static boolean isKaprekar(int x) {
        if (x == 0 || x == 1)
            return true;
        if (x == 3) {
            return false;
        }
        Integer xx = x * x;
        String s = xx.toString();
        String left = "";
        String right = "";

        if (s.length() % 2 == 0) {
            left = String.copyValueOf(s.toCharArray(), 0, (s.length()) / 2);
            right = String.copyValueOf(s.toCharArray(), (s.length()) / 2, s.length() / 2);
        } else {
            left += String.copyValueOf(s.toCharArray(), 0, s.length() / 2);
            right += String.copyValueOf(s.toCharArray(), (s.length()) / 2, s.length() / 2 + 1);
        }

        int ans = Integer.parseInt(left) + Integer.parseInt(right);

        return ans == x ? true : false;
    }

    public static String longestZero(String s) {
        if (s.length() == 0 || !s.contains("0"))
            return "";
        if (s.length() == 1) {
            if (s.charAt(0) == '0')
                return "0";
            else
                return "";
        }
        int max = 0;
        int cur = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) == '0' && s.charAt(i) == '0') {
                cur++;
            } else {
                max = Math.max(max, cur);
                cur = 1;
            }
        }
        return "0".repeat(max);
    }

    public static int nextPrime(int x) {
        if (x < 3) {
            return 2;
        }
        while (!isPrime(x)) {
            x++;
        }
        return x;
    }

    private static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }

    public static boolean rightTriangle(int a, int b, int c) {
        int gip = 0;
        int kat1 = 0;
        int kat2 = 0;
        if (a >= b && a >= c) {
            gip = a;
            kat1 = b;
            kat2 = c;
        }
        if (b >= a && b >= c) {
            gip = b;
            kat1 = a;
            kat2 = c;
        }
        if (c >= b && c >= a) {
            gip = c;
            kat1 = b;
            kat2 = a;
        }

        return gip * gip == kat1 * kat1 + kat2 * kat2 ? true : false;
    }
}