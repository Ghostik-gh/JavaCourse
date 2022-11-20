import java.util.ArrayList;
import java.util.Arrays;

public class Task6 {

    public static int bell(int n) {
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        int cur = 1;
        arr1[0] = 1;
        for (int i = 1; i < n; i++) {
            arr2[0] = cur;
            for (int j = 1; j < i + 1; j++) {
                arr2[j] = cur + arr1[j - 1];
                cur = arr2[j];
            }
            cur = arr2[i];
            for (int j = 0; j < n; j++) {
                arr1[j] = arr2[j];
            }
            // System.out.println(Arrays.toString(arr1));
        }
        return cur;
    }

    public static String translateWord(String word) {
        String[] letters = word.split("");
        if (word.length() < 1) {
            return "";
        }
        String prefix = "";
        String translatedWord = "";
        String punctuation = "";
        int cur = 0;
        for (int i = 0; i < letters.length; i++) {
            if ("[euioaAEUIO]".contains(letters[i])) {
                cur = i;
                break;
            } else {
                prefix += letters[i];
            }
        }
        for (int i = cur; i < letters.length; i++) {
            if (letters[i].matches("\\W")) {
                punctuation += letters[i];
            } else {
                translatedWord += letters[i];
            }
        }
        if (cur == 0) {
            translatedWord += "yay";
        } else {
            translatedWord += prefix + "ay";
        }
        return translatedWord + punctuation;
    }

    public static String translateSentence(String sentence) {
        String[] words = sentence.split(" ");
        String translatedSentence = "";
        for (int i = 0; i < words.length; i++) {
            translatedSentence += translateWord(words[i]) + " ";
        }
        return translatedSentence;
    }

    public static boolean validColor(String color) {
        if (color.matches("rgba?\\(\\d+,\\s*\\d+,\\s*\\d+\\s*(,0.\\d+)?\\)")) {
            String[] right = color.split("\\(");
            String[] brackets = right[1].split("\\)");
            String[] numbers = brackets[0].split(",");
            for (int i = 0; i < 3; i++) {
                if (Integer.parseInt(numbers[i]) > 255) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static String stripUrlParams(String s) {
        return stripUrlParams(s, new String[] { "" });
    }

    public static String stripUrlParams(String s, String[] b) {
        ArrayList<String> deletes = new ArrayList<>();
        for (int i = 0; i < b.length; i++) {
            deletes.add(b[i]);
        }
        String ans = "";
        if (s.matches(".*\\?.*")) {
            String[] url = s.split("\\?");
            ans += url[0];
            if (url[1].matches(".*\\&.*")) {
                ans += "?";
                String[] search = url[1].split("\\&");
                // System.out.println(Arrays.toString(search));
                // System.out.println(Arrays.toString(b));
                String[] params = new String[2];
                for (int i = search.length - 1; i >= 0; i--) {
                    boolean flag = false;
                    params = search[i].split("=");
                    for (int j = 0; j < deletes.size(); j++) {
                        if (params[0].equals(deletes.get(j))) {
                            flag = true;
                        }
                    }
                    if (flag == false) {
                        ans += search[i] + "&";
                        deletes.add(params[0]);
                    }

                }
            }
            return ans.substring(0, ans.length() - 1);
        }
        return s;

    }

    public static String[] getHashTags(String s) {
        String[] words = s.split(" ");
        if (s.length() < 1) {
            return new String[] {};
        }
        String[] tags = new String[words.length];
        for (int i = 0; i < tags.length; i++) {
            words[i] = words[i].toLowerCase();
            if (words[i].matches(".*\\W")) {
                tags[i] = "#" + words[i].substring(0, words[i].length() - 1);

            } else {

                tags[i] = "#" + words[i].toLowerCase();
            }
        }
        ArrayList<String> titles = new ArrayList<>(3);
        if (tags.length == 1) {
            titles.add(tags[0]);
        } else if (tags.length == 2) {
            titles.add(tags[0]);
            titles.add(tags[1]);

        } else {
            titles.add(tags[0]);
            titles.add(tags[1]);
            titles.add(tags[2]);
        }
        for (int i = 3; i < tags.length; i++) {
            int min = 1000000;
            int size = tags[i].length();
            int id = -1;
            for (int j = 0; j < titles.size(); j++) {
                if (titles.get(j).length() < min) {
                    min = titles.get(j).length();
                    id = j;
                }
            }
            if (size > min) {
                titles.set(id, tags[i]);
            }
        }
        for (int g = 0; g < 2; g++) {
            int max = titles.get(0).length();
            for (int i = 1; i < titles.size(); i++) {
                String tmp = "";
                if (titles.get(i).length() > max) {
                    tmp = titles.get(i);
                    titles.remove(titles.get(i));
                    titles.add(0, tmp);
                }
            }
        }
        String[] dsf = new String[titles.size()];
        titles.toArray(dsf);
        return dsf;
    }

    public static int ulam(int n) {
        ArrayList<Integer> ulamSeq = new ArrayList<>(n);
        ulamSeq.add(1);
        ulamSeq.add(2);
        ulamSeq.add(3);
        int num = 4;
        int cur = 4;
        int max = 3;
        while (cur <= n) {
            int count = 0;
            int findNum = 0;
            // System.out.println("Итерация:" + num);
            for (int i = 0; i < ulamSeq.size() - 1; i++) {
                if (num < max) {
                    num++;
                    continue;
                }
                findNum = num - ulamSeq.get(i);
                // System.out.println("Ищем: " + findNum + " Для " + ulamSeq.get(i));
                for (int j = ulamSeq.size() - 1; j > i; j--) {
                    if (ulamSeq.get(j) == ulamSeq.get(i)) {
                        num++;
                        break;
                    }
                    if (findNum == ulamSeq.get(j)) {
                        count++;
                        // System.out.println("Попыток" + count);
                        break;
                    }
                }
            }
            if (count == 1) {
                ulamSeq.add(num);
                max = num;
                cur++;
            }
            num++;

        }
        return ulamSeq.get(n - 1);
    }

    public static String longestNonrepeatingSubstring(String s) {
        String tmp = "";
        String ans = "";
        ArrayList<String> letters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (letters.contains(s.charAt(i) + "")) {
                if (tmp.length() > ans.length()) {
                    ans = tmp;
                }
                tmp = "";
            } else {
                letters.add(s.charAt(i) + "");
                tmp += s.charAt(i) + "";
                if (tmp.length() > ans.length()) {
                    ans = tmp;
                }
            }
        }
        return ans;
    }

    public static String convertToRoman(int n) {
        String roman = "";
        while (n - 1000 >= 0) {
            n -= 1000;
            roman += "M";
        }
        while (n - 900 >= 0) {
            n -= 900;
            roman += "CM";
        }
        while (n - 500 >= 0) {
            n -= 500;
            roman += "D";
        }
        while (n - 400 >= 0) {
            n -= 400;
            roman += "CD";
        }
        while (n - 100 >= 0) {
            n -= 100;
            roman += "C";
        }
        while (n - 90 >= 0) {
            n -= 90;
            roman += "XC";
        }
        while (n - 50 >= 0) {
            n -= 50;
            roman += "L";
        }
        while (n - 40 >= 0) {
            n -= 40;
            roman += "XL";
        }
        while (n - 10 >= 0) {
            n -= 10;
            roman += "X";
        }
        while (n - 9 >= 0) {
            n -= 9;
            roman += "IX";
        }
        while (n - 5 >= 0) {
            n -= 5;
            roman += "V";
        }
        while (n - 4 >= 0) {
            n -= 4;
            roman += "IV";
        }
        while (n - 1 >= 0) {
            n -= 1;
            roman += "I";
        }
        return roman;
    }

    /*
     * Слишком мало информации по вводимым данным
     * Первый вариант проверяет только формулы с одним знаком равно и
     * одним действием в левой части
     * будет работать только если все данные введены через пробел
     */
    public static boolean formula(String form) {
        if (form.matches("\\d+\s[-+*/]\s\\d+\s=\s\\d+")) {
            String[] splitted = form.split(" ");
            if (splitted[1].equals("*")) {
                if (Integer.parseInt(splitted[0]) * Integer.parseInt(splitted[2]) == Integer.parseInt(splitted[4])) {
                    System.out.println(splitted[0] + " " + splitted[2] + " " + splitted[4]);
                    return true;
                }
            }
            if (splitted[1].equals("+")) {
                if (Integer.parseInt(splitted[0]) + Integer.parseInt(splitted[2]) == Integer.parseInt(splitted[4])) {
                    return true;
                }
            }
            if (splitted[1].equals("/")) {
                if (Integer.parseInt(splitted[0]) / Integer.parseInt(splitted[2]) == Integer.parseInt(splitted[4])) {
                    return true;
                }
            }
            if (splitted[1].equals("-")) {
                if (Integer.parseInt(splitted[0]) - Integer.parseInt(splitted[2]) == Integer.parseInt(splitted[4])) {
                    return true;
                }
            }
        }
        return false;
    }

    /*
     * Принимает формулу с любым количеством равенств
     * при условии что в каждом либо ноль, либо одно действие
     * формула без знака "=" не является верной
     */
    public static boolean formula2(String form) {
        if (!form.contains("=")) {
            return false;
        }
        String[] parts = form.split("=");
        ArrayList<Integer> partAns = new ArrayList<>(parts.length);
        for (int i = 0; i < parts.length; i++) {

            if (parts[i].matches("\s*\\d*\s*")) {
                partAns.add(Integer.parseInt(parts[i].strip()));
            }
            String[] numbers = parts[i].split("[*-+/]");
            for (int j = 0; j < numbers.length; j++) {
                numbers[j] = numbers[j].strip();
            }

            if (parts[i].contains("*")) {
                partAns.add(Integer.parseInt(numbers[0]) * Integer.parseInt(numbers[1]));
            }
            if (parts[i].contains("+")) {
                partAns.add(Integer.parseInt(numbers[0]) + Integer.parseInt(numbers[1]));
            }
            if (parts[i].contains("/")) {
                partAns.add(Integer.parseInt(numbers[0]) / Integer.parseInt(numbers[1]));
            }
            if (parts[i].contains("-")) {
                partAns.add(Integer.parseInt(numbers[0]) - Integer.parseInt(numbers[1]));
            }
        }
        boolean flag = true;
        for (int i = 1; i < partAns.size(); i++) {
            if (!partAns.get(i).equals(partAns.get(i - 1))) {
                flag = false;
                break;
            }

        }
        return flag;
    }

    public static boolean isPalindrome(String str) {
        String str2 = new StringBuilder(str).reverse().toString();
        return str.equals(str2);
    }

    /*
     * Что делать с переполнениями
     * куда девать едииницу если это первый разряд
     * Если получилось нечетное число
     * 1. проверить на палиндром и сразу false иначе -
     * 2. добавить незначащий ноль в начало +
     */
    public static boolean palindromeDescendant(int n) {
        Integer cur = n;
        String str = cur + "";
        if (isPalindrome(str)) {
            return true;
        }
        while (cur > 9) {
            String tmpStr = "";
            if (str.length() % 2 == 1) {
                String buf = str;
                str = "0" + buf;
            }
            for (int i = 0; i < str.length() / 2; i++) {
                int tmp = Integer.parseInt(str.charAt(2 * i) + "")
                        + Integer.parseInt(str.charAt(2 * i + 1) + "");
                tmpStr += tmp;
            }
            if (tmpStr.length() < 2) {
                return false;
            }
            cur = Integer.parseInt(tmpStr);
            str = tmpStr;

            if (isPalindrome(str)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        print(1, "bell");
        System.out.println(bell(1)); // ➞1
        System.out.println(bell(2)); // ➞2
        System.out.println(bell(5)); // ➞52

        print(2, "translateWord");
        System.out.println(translateWord("flag")); // ➞"agflay"
        System.out.println(translateWord("Apple")); // ➞"Appleyay"
        System.out.println(translateWord("button")); // ➞"uttonbay"
        System.out.println(translateWord("")); // ➞""
        System.out.println(translateSentence("I like to eat honey waffles."));
        // ➞"Iyay ikelay otay eatyay oneyhay afflesway."
        System.out.println(translateSentence("Do you think it is going to rain today?"));
        // ➞"Oday youyay inkthay ityay isyay oinggay otay ainray odaytay?"

        print(3, "validColor");
        System.out.println(validColor("rgb(0,0,0)")); // ➞true
        System.out.println(validColor("rgb(0,,0)")); // ➞false
        System.out.println(validColor("rgb(255,256,255)")); // ➞false
        System.out.println(validColor("rgba(0,0,0,0.123456789)")); // ➞true

        print(4, "stripUrlParams");
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2")); // ➞"https://edabit.com?a=2&b=2"
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[] { "b" })); // ➞"https://edabit.com?a=2"
        System.out.println(stripUrlParams("https://edabit.com", new String[] { "b" })); // ➞"https://edabit.com"

        print(5, "getHashTags");
        System.out.println(Arrays.toString(getHashTags("How the Avocado Became the Fruit of the Global Trade")));
        // ➞["#avocado", "#became", "#global"]
        System.out.println(
                Arrays.toString(getHashTags("Why You Will Probably Pay More for Your Christmas Tree This Year")));
        // ➞["#christmas", "#probably", "#will"]
        System.out.println(Arrays.toString(getHashTags("Hey Parents, Surprise, Fruit Juice Is Not Fruit")));
        // ➞["#surprise", "#parents", "#fruit"]
        System.out.println(Arrays.toString(getHashTags("Visualizing Science")));
        System.out.println(Arrays.toString(getHashTags("")));
        // ➞["#visualizing", "#science"]

        print(6, "ulam");
        System.out.println(ulam(4));// ➞4
        System.out.println(ulam(9));// ➞16
        System.out.println(ulam(206));// ➞1856

        print(7, "longestNonrepeatingSubstring");
        System.out.println(longestNonrepeatingSubstring("abcabcbb")); // ➞ "abc"
        System.out.println(longestNonrepeatingSubstring("aaaaaa")); // ➞ "a"
        System.out.println(longestNonrepeatingSubstring("abcde")); // ➞ "abcde"
        System.out.println(longestNonrepeatingSubstring("abcda")); // ➞ "abcd"

        print(8, "convertToRoman");
        System.out.println(convertToRoman(2)); // ➞"II"
        System.out.println(convertToRoman(12)); // ➞"XII"
        System.out.println(convertToRoman(16)); // ➞"XVI"
        System.out.println(convertToRoman(3999)); // ➞"MMMCMXCIX"

        print(9, "formula");
        System.out.println(formula2("6 * 4 = 24")); // ➞ true
        System.out.println(formula2("18 / 17 = 2")); // ➞ false
        System.out.println(formula2("16 * 10 = 160 = 40 + 120")); // ➞ true
        System.out.println(formula2("16 * 10")); // ➞ false

        print(10, "palindromeDescendant");
        System.out.println(palindromeDescendant(11211230)); // ➞true
        // 11211230 ➞ 2333 ➞ 56 ➞ 11
        System.out.println(palindromeDescendant(13001120)); // ➞true
        // 13001120 ➞ 4022 ➞ 44
        System.out.println(palindromeDescendant(23336015)); // ➞ false
        // 23336014 ➞ 5666 ➞ 1112 ➞ 23 ➞ false
        System.out.println(palindromeDescendant(23330015)); // ➞ false
        // 23330015 ➞ 5606 ➞ 116 ➞ 17 ➞ false
        System.out.println(palindromeDescendant(11)); // ➞true
        // Number itself is a palindrome.
    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(40 - name.length()));
    }
}
