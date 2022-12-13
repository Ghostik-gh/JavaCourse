// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.InputStream;
// import java.net.URL;
// import javax.net.ssl.HttpsURLConnection;

import java.io.IOException;
import java.util.LinkedList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Crawler implements Runnable {

    private static String startURL;
    private static int maxDepth;
    private static int currentDepth = 0;
    private Thread thread;

    private static LinkedList<URLDepthPair> myList = new LinkedList<URLDepthPair>();
    private static LinkedList<URLDepthPair> myListRemainder = new LinkedList<URLDepthPair>();

    public Crawler(String url, int depth) {
        System.out.println("Crawler Created!");
        startURL = url;
        maxDepth = depth;

        thread = new Thread(this);
        thread.start();
    }

    // startURL =
    // "https://yandex.ru/maps/213/moscow/?ll=37.617700%2C55.755863&z=10";
    // startURL = "https://www.lesswrong.com";
    // startURL = "https://hpmor.ru/book/1/71/";
    // startURL = "https://jsonplaceholder.typicode.com/users";

    private static boolean isVaildLink(String link) {
        if (link.matches("https?://.*\\..*")) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        crawl(startURL, 1);
    }

    private static Document request(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                System.out.println("Connect Successful: " + doc.title());
                myList.add(new URLDepthPair(url, currentDepth));
            }
            return doc;
        } catch (IOException e) {
            return null;
        }
    }

    public static void crawl(String url, int depth) {

        myListRemainder.add(new URLDepthPair(startURL, 0));
        while (currentDepth != maxDepth) {
            currentDepth++;
            int remainderSize = myListRemainder.size();
            // System.out.println("========================================");
            for (int i = 0; i < remainderSize; i++) {
                // System.out.println(myListRemainder.get(i).getUrl() + " <==> " +
                // currentDepth);
                if (currentDepth == maxDepth) {
                    myList.add(new URLDepthPair(myListRemainder.get(i).getUrl(), currentDepth));
                    continue;
                }
                try {
                    Document document = request(myListRemainder.get(i).getUrl());
                    Elements links = document.body().select("a");
                    for (var el : links) {
                        if (isVaildLink(el.attr("href"))) {
                            myListRemainder.add(new URLDepthPair(el.attr("href"),
                                    myListRemainder.get(i).getDepth() + 1));
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    myListRemainder.remove(myListRemainder.get(i));
                }
            }
            if (currentDepth == maxDepth) {
                myListRemainder.removeAll(myListRemainder);
            }
        }
        showResult(myList);
    }

    public static void showResult(LinkedList<URLDepthPair> viewedLink) {
        for (URLDepthPair c : viewedLink)
            System.out.println("Depth : " + c.getDepth() + "\tLink : " + c.getUrl());
    }

    public Thread getThread() {
        return thread;
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
