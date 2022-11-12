import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task5 {
    public static Integer[] encrypt(String s) {
        Integer[] intStr = new Integer[s.length()];
        for (int i = 0; i < s.length(); i++) {
            // char ch = s.charAt(i);
            intStr[i] = Integer.valueOf(s.charAt(i));
        }
        Integer[] answer = new Integer[s.length()];
        answer[0] = intStr[0];
        for (int i = 1; i < intStr.length; i++) {
            answer[i] = intStr[i] - intStr[i - 1];
        }
        return answer;
    }

    public static String decrypt(Integer[] arr) {
        // 72, 33, -73, 84, -12, -3, 13, -13, -68
        String s = "";
        s += (char) (int) arr[0];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i];
            s += (char) (int) (arr[i]);
        }
        return s;
    }

    /*
     * На ввод в координатах подаются токлько заглавные буквы
     * Название фигуры только на английском не зависимо от регистра
     */
    public static boolean canMove(String figure, String from, String to) {
        int x1 = from.charAt(0) - 64;
        int x2 = to.charAt(0) - 64;
        int y1 = from.charAt(1) - 48;
        int y2 = to.charAt(1) - 48;
        if (x1 == x2 && y1 == y2) {
            // Фигура не может двигаться в точку в которой уже находится
            return false;
        }
        if (figure.toLowerCase().equals("pawn")) {
            // Важно проверить цвет фигуры и наличие других фигур рядом
            // по умолчанию может двигаться на 1-2 клетки только по вертикали
            return ((Math.abs(y1 - y2) <= 2) && (x1 == x2)) ? true : false;
        }
        if (figure.toLowerCase().equals("rook")) {
            return (x1 == x2 || y1 == y2) ? true : false;
        }
        if (figure.toLowerCase().equals("bishop")) {
            return (Math.abs(x2 - x1) == Math.abs(y2 - y1)) ? true : false;
        }
        if (figure.toLowerCase().equals("knight")) {
            return (((Math.abs(x1 - x2) == 1) && (Math.abs(y1 - y2) == 2)) ||
                    ((Math.abs(x1 - x2) == 2) && (Math.abs(y1 - y2) == 1))) ? true : false;
        }
        if (figure.toLowerCase().equals("queen")) {
            return (Math.abs(x2 - x1) == Math.abs(y2 - y1) || x1 == x2 || y1 == y2) ? true : false;
        }
        if (figure.toLowerCase().equals("king")) {
            return (Math.abs(x1 - x2) <= 1 && Math.abs(y1 - y2) <= 1) ? true : false;
        }
        return false;
    }

    public static boolean canComplete(String subStr, String str) {
        int cur = 0;
        boolean find = false;
        for (int i = 0; i < subStr.length(); i++) {
            subStr.charAt(i);
            find = false;
            for (int j = cur; j < str.length(); j++) {
                if (str.charAt(j) == subStr.charAt(i)) {
                    cur = j + 1;
                    find = true;
                    break;
                }
            }
            if (find == false) {
                return false;
            }
        }
        return true;
    }

    public static int sumDigProd(Integer[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        while (sum / 10 >= 1) {
            int ans = 1;
            int tmp = sum;
            while (tmp > 0) {
                ans *= tmp % 10;
                tmp /= 10;
            }
            if (ans / 10 == 0)
                return ans;
            else {
                sum = ans;
            }
        }
        return sum;
    }

    public static List<String> sameVowelGroup(String[] strArr) {
        List<String> arr = new ArrayList<String>();
        arr.add(strArr[0]);
        for (int i = 0; i < strArr.length; i++) {
            strArr[i].toLowerCase();
        }
        String strfind = "";
        for (int i = 0; i < strArr[0].length(); i++) {
            if (new StringBuilder().append(strArr[0].charAt(i)).toString().matches("[aoiue]")) {
                strfind += new StringBuilder().append(strArr[0].charAt(i));
            }
        }
        for (int i = 1; i < strArr.length; i++) {
            String tmpfind = "";
            for (int j = 0; j < strArr[i].length(); j++) {
                if (new StringBuilder().append(strArr[i].charAt(j)).toString().matches("[aoiue]")) {
                    tmpfind += new StringBuilder().append(strArr[i].charAt(j));
                }
            }

            char[] chararr = tmpfind.toCharArray();
            boolean flag = true;
            for (int k = 0; k < chararr.length; k++) {
                if (!strfind.matches(".*" + chararr[k] + ".*")) {
                    flag = false;
                }
            }
            if (flag) {
                arr.add(strArr[i]);
            }
        }
        return arr;
    }

    public static boolean validateCard(String card) {
        int control = card.charAt(card.length() - 1) - 48;
        card = card.substring(0, card.length() - 1);
        String cardReverse = "";
        int sum = 0;
        for (int i = card.length() - 1; i >= 0; i--) {
            int num = card.charAt(i) - 48;
            cardReverse += num;
        }
        for (int i = 0; i < cardReverse.length(); i++) {
            int num = cardReverse.charAt(i) - 48;
            if (i % 2 == 0) {
                if (num < 5) {
                    sum += num * 2;
                } else {
                    int tmp = (num * 2) % 10 + 1;
                    sum += tmp;
                }
            } else {
                sum += num;
            }
        }
        return 10 - (sum % 10) == control ? true : false;
    }

    public static String oneNumToStr(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.put(6, "six");
        map.put(7, "seven");
        map.put(8, "eight");
        map.put(9, "nine");
        map.put(0, "zero");
        return map.get(num);
    }

    public static String oneNumToStrRu(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(0, "ноль");
        return map.get(num);
    }

    private static String decadeToStr(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "twenty ");
        map.put(3, "thirty ");
        map.put(4, "forty ");
        map.put(5, "fifty ");
        map.put(6, "sixty ");
        map.put(7, "seventy ");
        map.put(8, "eighty ");
        map.put(9, "ninety ");
        map.put(0, "zero");
        map.put(11, "eleven");
        map.put(12, "twelve");
        map.put(13, "thirteen");
        map.put(14, "fourteen");
        map.put(15, "fifteen");
        map.put(16, "sixteen");
        map.put(17, "seventeen");
        map.put(18, "eighteen");
        map.put(19, "nineteen");
        map.put(10, "ten");
        String ans = "";
        if (num > 19) {
            ans += map.get(num / 10) + oneNumToStr(num % 10);
        } else if (num > 9) {
            ans += map.get(num);
        } else {
            ans += oneNumToStr(num % 10);
        }
        return ans;
    }

    private static String decadeToStrRu(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "двадцать ");
        map.put(3, "тридцать ");
        map.put(4, "сорок ");
        map.put(5, "пятьдесят ");
        map.put(6, "шестьдесят ");
        map.put(7, "семьдесят ");
        map.put(8, "восемьдесят ");
        map.put(9, "девяносто ");
        map.put(0, "ноль");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
        map.put(13, "тринаддцать");
        map.put(14, "четырнадцать");
        map.put(15, "пятнадцать");
        map.put(16, "шестнадцать");
        map.put(17, "семнадцать");
        map.put(18, "восемнадцать");
        map.put(19, "девятнадцать");
        map.put(10, "десять");
        String ans = "";
        if (num > 19) {
            ans += map.get(num / 10) + oneNumToStrRu(num % 10);
        } else if (num > 9) {
            ans += map.get(num);
        } else {
            ans += oneNumToStrRu(num % 10);
        }
        return ans;
    }

    private static String hundredToRu(int num) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "сто ");
        map.put(2, "двести ");
        map.put(3, "триста ");
        map.put(4, "четыреста ");
        map.put(5, "пятьсот ");
        map.put(6, "шестьсот ");
        map.put(7, "семьсот ");
        map.put(8, "восемьсот ");
        map.put(9, "девятьсот ");
        return map.get(num);
    }

    public static String numToEng(int num) {
        String ans = "";
        if (num > 99) {
            ans += oneNumToStr(num / 100) + " hundred ";
        }
        ans += decadeToStr(num % 100);
        return ans;
    }

    public static String numToRu(int num) {
        String ans = "";
        if (num > 99) {
            ans += hundredToRu(num / 100);
        }
        ans += decadeToStrRu(num % 100);
        return ans;
    }

    public static String getSha256Hash(String s) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(s.getBytes());
        byte[] hash = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }

    public static String correctTitle(String s) {
        String ans = "";
        String[] words = s.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].toLowerCase();
            if (words[i].equals("and") || words[i].equals("the") ||
                    words[i].equals("of") || words[i].equals("in")) {
                ans += words[i] + " ";
            } else {
                ans += (char) ((int) words[i].charAt(0) - 32);
                ans += words[i].substring(1, words[i].length()) + " ";
            }
        }
        return ans;

    }

    public static String hexLattice(int num) {
        int cur = 1;
        int i = 0;
        int step = 6;
        // Проверяем является ли число центрированным шестиугольным
        // находим количество итераций
        while (cur < num) {
            i++;
            cur += step * i;
        }
        if (cur == num) {
            String ans = "";
            int space = i;
            // Строки до центра включая
            for (int j = 0; j < i + 1; j++) {
                String line = "";
                line += " ".repeat(space - j);
                String middle = "";
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                ans += line;
            }
            // Строки от центра
            for (int j = i - 1; j >= 0; j--) {
                String line = "";
                line += " ".repeat(space - j);
                String middle = "";
                middle += "o ".repeat(i + 1 + j);
                middle = middle.strip();
                line += middle;
                line += " ".repeat(space - j) + "\n";
                ans += line;
            }
            return ans;
        } else {
            return "Invalid";
        }
    }

    public static void main(String[] args) throws Exception {
        print(1, "encrypt decrypt");
        System.out.println(Arrays.toString(encrypt("Hello"))); // ➞ [72, 29, 7, 0, 3]
        System.out.println(decrypt(new Integer[] { 72, 33, -73, 84, -12, -3, 13, -13, -68 })); // ➞ "Hi there!"
        System.out.println(Arrays.toString(encrypt("Sunshine"))); // ➞ [83, 34, -7, 5, -11, 1, 5, -9]
        print(2, "canMove");
        System.out.println(canMove("Rook", "A8", "H8")); // ➞ true
        System.out.println(canMove("Bishop", "A7", "G1")); // ➞ true
        System.out.println(canMove("Queen", "C4", "D6")); // ➞ false
        print(2, "canMove + My ex");
        System.out.println(canMove("Pawn", "B4", "B6")); // ➞ true
        System.out.println(canMove("Knight", "E4", "F6")); // ➞ true
        System.out.println(canMove("King", "C4", "D5")); // ➞ true
        print(3, "canComplete");
        System.out.println(canComplete("butl", "beautiful")); // ➞ true
        System.out.println(canComplete("butlz", "beautiful"));// ➞ false
        System.out.println(canComplete("tulb", "beautiful"));// ➞ false
        System.out.println(canComplete("bbutl", "beautiful"));// ➞ false
        print(4, "sumDigProd");
        System.out.println(sumDigProd(new Integer[] { 16, 28 })); // ➞ 6
        System.out.println(sumDigProd(new Integer[] { 0 })); // ➞ 0
        System.out.println(sumDigProd(new Integer[] { 1, 2, 3, 4, 5, 6 })); // ➞ 2
        print(5, "sameVowelGroup");
        System.out.println(sameVowelGroup(new String[] { "toe", "ocelot", "maniac" }));
        // ➞ ["toe", "ocelot"]
        System.out.println(sameVowelGroup(new String[] { "many", "carriage", "emit", "apricot", "animal" }));
        // ➞ ["many"]
        System.out.println(sameVowelGroup(new String[] { "hoops", "chuff", "bot", "bottom" }));
        // ➞ ["hoops", "bot", "bottom"]
        print(6, "validateCard");
        System.out.println(validateCard("1234567890123456")); // ➞ false
        System.out.println(validateCard("1234567890123452")); // ➞ true
        print(7, "numToEng");
        System.out.println(numToEng(0)); // ➞ "zero"
        System.out.println(numToEng(18)); // ➞ "eighteen"
        System.out.println(numToEng(126)); // ➞ "one hundred twenty six"
        System.out.println(numToEng(909)); // ➞ "nine hundred nine"
        print(7, "numToRu");
        System.out.println(numToRu(0)); // ноль
        System.out.println(numToRu(18)); // восемнадцать
        System.out.println(numToRu(126)); // сто двадцать шесть
        System.out.println(numToRu(909)); // девятьсот девять
        print(8, "getSha256Hash");
        System.out.println(getSha256Hash("password123")); // ➞
        // "ef92b778bafe771e89245b89ecbc08a44a4e166c06659911881f383d4473e94f"
        System.out.println(getSha256Hash("Fluffy@home")); // ➞
        // "dcc1ac3a7148a2d9f47b7dbe3d733040c335b2a3d8adc7984e0c483c5b2c1665"
        System.out.println(getSha256Hash("Hey dude!")); // ➞
        // "14f997f08b8ad032dcb274198684f995d34043f9da00acd904dc72836359ae0f"
        print(9, "correctTitle");
        System.out.println(correctTitle("jOn SnoW, kINg IN thE noRth."));
        // ➞ "Jon Snow, King in the North."
        System.out.println(correctTitle("sansa stark, lady of winterfell."));
        // ➞ "Sansa Stark, Lady of Winterfell."
        System.out.println(correctTitle("TYRION LANNISTER, HAND OF THE QUEEN."));
        // ➞ "Tyrion Lannister, Hand of the Queen."

        print(10, "hexLattice");
        System.out.println(hexLattice(1)); // ➞ " o "
        // // o
        System.out.println(hexLattice(7)); // ➞ " o o \n o o o \n o o "
        // // o o
        // // o o o
        // // o o
        System.out.println(hexLattice(19)); // ➞ " o o o \n o o o o \n o o o o o \n o o o o \n
        // o o o "
        // // o o o
        // // o o o o
        // // o o o o o
        // // o o o o
        // // o o o
        System.out.println(hexLattice(21)); // ➞ "Invalid"
    }

    private static void print(int task, String name) {
        System.out.println("============Task #" + task + "===" + name + "=".repeat(25 - name.length()));
    }
}
