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

    private static int maxDepth = WebScanner.pool.getMaxDepth();
    private static int currentDepth;
    private String findURL;
    private Thread thread;
    private static URLPool pool = WebScanner.pool;

    public Crawler(String url, int depth) {
        findURL = url;
        currentDepth = depth;
        pool.removeRemaindLink();
        URLPool.addProcUrl();
        // System.out.println(pool.getRemaindLink());
        thread = new Thread(this);
        thread.start();
        // System.out.println("Crawler Created!" + thread.getId());
    }

    private static boolean isVaildLink(String link) {
        if (link.matches("https?://.*\\..*")) {
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        crawl();
    }

    private Document request() {
        try {
            Connection con = Jsoup.connect(findURL);
            Document doc = con.get();

            if (con.response().statusCode() == 200) {
                // System.out.println("Connect Successful: " + doc.title());
                WebScanner.pool.addSeenLink(findURL, currentDepth); // myList.add(new URLDepthPair(url, currentDepth));
            }
            return doc;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void crawl() {
        // myListRemainder.add(newURLDepthPair(startURL, 0));
        // System.out.println(currentDepth + " " + maxDepth);
        if (currentDepth == maxDepth) {
            WebScanner.pool.addSeenLink(findURL, currentDepth);
            return;
            // myList.add(new URLDepthPair(myListRemainder.get(i).getUrl(), currentDepth));
        }
        try {
            Document document = request();
            Elements links = document.body().select("a");
            for (var el : links) {
                if (isVaildLink(el.attr("href"))) {
                    WebScanner.pool.addRemaindLink(el.attr("href"), currentDepth + 1);
                    // myListRemainder.add(new URLDepthPair(el.attr("href"),
                    // myListRemainder.get(i).getDepth() + 1));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally {
        // WebScanner.pool.removeRemaindLink(findURL, currentDepth);
        // // myListRemainder.remove(myListRemainder.get(i));
        // }
        if (currentDepth > maxDepth) {
            WebScanner.pool.removeAllRemaind();
        }
        URLPool.removeProcUrl();
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
