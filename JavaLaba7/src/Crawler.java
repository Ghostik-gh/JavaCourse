// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.net.URL;
// import javax.net.ssl.HttpsURLConnection;

import java.util.LinkedList;

import org.jsoup.Jsoup;

public class Crawler {

    private static boolean isVaildLink(String link) {
        if (link.matches("https?://.*\\..*")) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws Exception {

        long m = System.currentTimeMillis();
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
        String startURL = "";
        int depth = 3;
        int currentDepth = 0;
        startURL = "https://www.lesswrong.com";
        // startURL =
        // "https://yandex.ru/maps/213/moscow/?ll=37.617700%2C55.755863&z=10";
        // startURL = "https://hpmor.ru/book/1/71/";
        // startURL = "https://jsonplaceholder.typicode.com/users";
        LinkedList<URLDepth> myList = new LinkedList<URLDepth>();
        LinkedList<URLDepth> myListRemainder = new LinkedList<URLDepth>();
        myListRemainder.add(new URLDepth(startURL, 0));
        while (currentDepth != depth) {
            currentDepth++;
            int remainderSize = myListRemainder.size();
            // System.out.println("========================================");
            for (int i = 0; i < remainderSize; i++) {
                // System.out.println(myListRemainder.get(i).getUrl() + " <==> " +
                // currentDepth);
                if (currentDepth == depth) {
                    myList.add(new URLDepth(myListRemainder.get(i).getUrl(), currentDepth));
                    continue;
                }
                try {
                    var document = Jsoup.connect(myListRemainder.get(i).getUrl()).get();
                    var links = document.body().select("a");
                    for (var el : links) {
                        if (isVaildLink(el.attr("href"))) {
                            myListRemainder.add(new URLDepth(el.attr("href"),
                                    myListRemainder.get(i).getDepth() + 1));
                        }
                    }
                    myList.add(new URLDepth(myListRemainder.get(i).getUrl(), currentDepth));
                } catch (Exception e) {
                    // e.printStackTrace();
                } finally {
                    myListRemainder.remove(myListRemainder.get(i));
                }
            }
            if (currentDepth == depth) {
                myListRemainder.removeAll(myListRemainder);
            }
        }

        System.out.println(myList.toString());
        System.out.println(myList.size());
        System.out.println((double) (System.currentTimeMillis() - m) / 1000);
    }
    // InputStream inputStream = null;
    // FileOutputStream outputStream = null;
    // try {
    // URL url = new URL(startURL);
    // HttpsURLConnection httpsURLConnection = (HttpsURLConnection)
    // url.openConnection();

    // int responseCode = httpsURLConnection.getResponseCode();
    // if (responseCode == HttpsURLConnection.HTTP_OK) {
    // inputStream = httpsURLConnection.getInputStream();
    // File file = new File("test1.json");
    // outputStream = new FileOutputStream(file);

    // int byteRead = -1;
    // byte[] buffer = new byte[1024];
    // while ((byteRead = inputStream.read(buffer)) != -1) {
    // outputStream.write(buffer, 0, byteRead);
    // }
    // }
    // } catch (IOException e) {
    // System.out.println("Internet connection error " + e.toString());
    // } finally {
    // inputStream.close();
    // outputStream.close();
    // }
}
