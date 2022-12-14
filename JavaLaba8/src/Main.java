
public class Main {
    public static void main(String[] args) throws Exception {
        WebScanner scanner = new WebScanner("https://yandex.ru/maps/213/moscow/?ll=37.617700%2C55.755863&z=10", 4);
        scanner.run();

        // startURL =
        // "https://yandex.ru/maps/213/moscow/?ll=37.617700%2C55.755863&z=10";
        // startURL = "https://www.lesswrong.com";
        // startURL = "https://hpmor.ru/book/1/71/";
        // startURL = "https://jsonplaceholder.typicode.com/users";

        // Crawler craw = new Crawler("https://www.lesswrong.com", 2);
        // if (args.length > 2 || args.length < 1) {
        // System.out.println("usage: java Crawler <URL> <?depth>");
        // // System.exit(1);
        // }
        // if (args.length == 2) {
        // if (isVaildLink(args[0])) {
        // startURL = args[0];
        // } else {
        // System.out.println("Write correct URL");
        // // System.exit(1);
        // }
        // if (args[1].matches("\\d*")) {
        // depth = Integer.parseInt(args[1]);
        // } else {
        // System.out.println("Write Integer Depth");
        // // System.exit(1);
        // }
        // }
    }
}
