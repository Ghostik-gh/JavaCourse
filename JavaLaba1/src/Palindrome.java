public class Palindrome {
    // Возвращает для каждого переданного слова является ли оно палиндромом
    public static void main(String[] args) throws Exception {
        System.out.println("P = Palindrome, not P = not Palindrome");
        for (int i = 0; i < args.length; i++) {
            String input = args[i];
            // P = Palindrome
            // not P = not Palindrome
            System.out.println(input + (isPalindrome(input) ? " -> P" : " -> not P"));
        }
    }

    // Метод разворачивает переданное слово или строку и возвращает новую
    private static String reverseString(String str) {
        String output = "";
        for (int i = 0; i < str.length(); i++) {
            output += str.charAt(str.length() - i - 1);
        }
        return output;
    }

    // Метод возвращает true, если строка или слово является палиндромом
    // иначе возвращает false
    public static boolean isPalindrome(String str) {
        return str.equals(reverseString(str));
    }
}
