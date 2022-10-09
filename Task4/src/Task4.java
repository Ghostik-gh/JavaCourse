import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;

public class Task4 {

    public static String bessieProc(int n, int k, String s) {
        String[] words = s.split(" ");
        int cur = 0;
        String ans = "";
        for (int i = 0; i < words.length; i++) {
            if (words[i].length() + cur <= k) {
                cur += words[i].length();
                ans += words[i] + " ";
            } else {
                ans += "\n" + words[i] + " ";
                cur = words[i].length();
            }
        }
        return ans;
    }

    public static List<String> split(String s) {
        // + 1 if '('
        // - 1 if ')'
        int countBracets = 0;
        String one = "";
        List<String> answer = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                countBracets++;
                one += "(";
            }
            if (s.charAt(i) == ')') {
                countBracets--;
                one += ")";
            }
            if (countBracets == 0) {
                answer.add(one);
                one = "";
            }
        }
        return answer;
    }

    public static String toCamelCase(String s) {
        char[] letters = s.toCharArray();
        if (letters[letters.length - 1] == '_') {
            letters[letters.length - 1] = ' ';
        }
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == '_') {
                letters[i + 1] = (char) (int) (letters[i + 1] - 32);
            }

        }
        return new String(letters).replaceAll("_", "").trim();
    }

    public static String toSnakeCase(String s) {
        char[] letters = s.toCharArray();
        String answer = "";
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] <= 90 && letters[i] >= 65) {
                answer += "_";
                answer += (char) (int) (letters[i] + 32);
            } else
                answer += letters[i];
        }

        return answer;
    }

    public static String overTime(double[] arr) {
        double start = arr[0];
        double end = arr[1];
        double pay = arr[2];
        double kef = arr[3];
        Double ans = 0.00;
        if (end <= 17) {
            ans = (end - start) * pay;
        } else {
            ans = (17 - start) * pay;
            ans += (end - 17) * pay * kef;
        }
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("$####0.00");
        return df.format(ans);
    }

    public static String BMI(String s1, String s2) {
        double weight = Double.parseDouble(s1.split(" ")[0]);
        double height = Double.parseDouble(s2.split(" ")[0]);
        if (s1.contains("pounds")) {
            weight *= 0.45359237;
        }
        if (s2.contains("inches")) {
            height *= 0.0254;
        }
        double bmi = weight / (height * height);
        NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern("######0.0");
        String bmiString = df.format(bmi);
        if (bmi < 18.5) {
            return bmiString + " Underweight";
        } else if (bmi < 25) {
            return bmiString + " Normal weight";
        } else
            return bmiString + " Overweight";
    }

    public static int bugger(int n) {
        String s = Integer.toString(n);
        String[] s1 = s.split("");
        int count = 0;
        while (s1.length != 1) {
            count++;
            int ans = 1;
            for (int i = 0; i < s1.length; i++) {
                ans *= Integer.parseInt(s1[i]);
            }
            s1 = Integer.toString(ans).split("");
        }
        return count;
    }

    public static String toStarShorthand(String s) {
        if (s.length() == 0)
            return "";
        String ans = "";
        char current = s.charAt(0);
        int count = 1;
        for (int i = 1; i < s.length(); i++) {
            if (current == s.charAt(i)) {
                count++;
            } else {
                if (count == 1) {
                    ans += current;
                } else {
                    ans += current + "*" + count;
                }
                count = 1;
                current = s.charAt(i);
            }
        }
        if (count == 1) {
            ans += current;
        } else {
            ans += current + "*" + count;
        }
        return ans;
    }

    public static boolean doesRhyme(String s1, String s2) {
        String parse1 = s1.split(" ")[s1.split(" ").length - 1].toLowerCase();
        String parse2 = s2.split(" ")[s2.split(" ").length - 1].toLowerCase();
        String word1 = "";
        String word2 = "";
        for (int j = 0; j < parse1.length(); j++) {
            if (new StringBuilder().append(parse1.charAt(j)).toString().matches("[aoiue]")) {
                word1 += new StringBuilder().append(parse1.charAt(j));
            }
        }
        for (int j = 0; j < parse2.length(); j++) {
            if (new StringBuilder().append(parse2.charAt(j)).toString().matches("[aoiue]")) {
                word2 += new StringBuilder().append(parse2.charAt(j));
            }
        }
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars1);
        String sorted1 = new String(chars1);
        String sorted2 = new String(chars2);
        return sorted1.equals(sorted2) ? true : false;
    }

    public static boolean trouble(int a, int b) {
        Integer[] arr1 = new Integer[10];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = 0;
        }
        String aStr = new StringBuilder().append(a).toString();
        for (int i = 0; i < aStr.length(); i++) {
            arr1[Integer.parseInt(new StringBuilder().append(aStr.charAt(i)).toString())]++;
        }
        Integer[] arr2 = new Integer[10];
        for (int i = 0; i < arr1.length; i++) {
            arr2[i] = 0;
        }
        String bStr = new StringBuilder().append(b).toString();
        for (int i = 0; i < bStr.length(); i++) {
            arr2[Integer.parseInt(new StringBuilder().append(bStr.charAt(i)).toString())]++;
        }
        for (int i = 0; i < 10; i++) {
            if (arr1[i] >= 3 && arr2[i] >= 2) {
                return true;
            }
        }
        return false;
    }

    public static int countUniqueBooks(String stringSequence, String bookEnd) {
        String[] books = stringSequence.split("[" + bookEnd + "]");
        String answer = "";
        for (int i = 1; i < books.length; i += 2) {
            answer += books[i];
        }
        Set<String> set2 = new TreeSet<String>();
        String[] splited = answer.split("");
        set2.addAll(Arrays.asList(splited));
        return answer == "" ? 0 : set2.size();
    }

    public static void main(String[] args) throws Exception {
        print(1, "bessieProc");
        System.out.println(bessieProc(10, 7, "hello my name is Bessie and this is my essay"));

        print(2, "split");
        System.out.println(split("()()()")); // ["()", "()", "()"]
        System.out.println(split("((()))")); // ["((()))"]
        System.out.println(split("((()))(())()()(()())"));// ["((()))", "(())", "()", "()", "(()())"]
        System.out.println(split("((())())(()(()()))")); // ["((())())", "(()(()()))"]

        print(3, "split");
        System.out.println(toCamelCase("hello_edabit")); // "helloEdabit"
        System.out.println(toSnakeCase("helloEdabit")); // "hello_edabit"
        System.out.println(toCamelCase("is_modal_open")); // "isModalOpen"
        System.out.println(toSnakeCase("getColor")); // "get_color"

        print(4, "overTime");
        System.out.println(overTime(new double[] { 9, 17, 30, 1.5 })); // "$240.00"
        System.out.println(overTime(new double[] { 16, 18, 30, 1.8123123 })); // "$84.00"
        System.out.println(overTime(new double[] { 13.25, 15, 30, 1.5 })); // "$52.50"

        print(5, "BMI");
        System.out.println(BMI("205 pounds", "73 inches")); // "27.0 Overweight"
        System.out.println(BMI("55 kilos", "1.65 meters")); // "20.2 Normal weight"
        System.out.println(BMI("154 pounds", "2 meters")); // "17.5 Underweight"

        print(6, "bugger");
        System.out.println(bugger(39)); // 3
        System.out.println(bugger(999)); // 4
        System.out.println(bugger(4)); // 0

        print(7, "toStarShorthand");
        System.out.println(toStarShorthand("abbccc")); // "ab*2c*3"
        System.out.println(toStarShorthand("77777geff")); // "7*5gef*2"
        System.out.println(toStarShorthand("abc")); // "abc"
        System.out.println(toStarShorthand("")); // ""

        print(8, "doesRhyme");
        System.out.println(doesRhyme("Sam I am!", "Green eggs and ham.")); // true
        System.out.println(doesRhyme("Sam I am!", "Green eggs and HAM.")); // true
        System.out.println(doesRhyme("You are off to the races", "a splendid day.")); // false
        System.out.println(doesRhyme("and frequently do?", "you gotta move.")); // false

        print(9, "trouble");
        System.out.println(trouble(451999277, 41179922)); // True
        System.out.println(trouble(1222345, 12345)); // False
        System.out.println(trouble(666789, 12345667)); // True
        System.out.println(trouble(33789, 12345337)); // False

        print(10, "countUniqueBooks");
        System.out.println(countUniqueBooks("AZYWABBCATTTA", "A")); // 4
        // // 1st bookend group: "AZYWA" : 3 unique books: "Z", "Y", "W"
        // // 2nd bookend group: "ATTTA": 1 unique book: "T"
        System.out.println(countUniqueBooks("$AA$BBCATT$C$$B$", "$")); // 3
        System.out.println(countUniqueBooks("ZZABCDEF", "Z")); // 0

    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(25 - name.length()));
    }
}
