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
    private int currentDepth;
    private String findURL;
    private Thread thread;
    private static URLPool pool = WebScanner.pool;

    public Crawler(String url, int depth) {
        findURL = url;
        currentDepth = depth;
        pool.removeRemaindLink();
        URLPool.addProcUrl();
        thread = new Thread(this);
        // System.out.println("Crawler Created!" + thread.getId());
    }

    public void updateCraw(String url, int n) {
        findURL = url;
        currentDepth = n;
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
            if (URLPool.findUrl(findURL)) {
                return null;
            }
            if (currentDepth == maxDepth) {
                WebScanner.pool.addSeenLink(findURL, currentDepth);
                return null;
            }
            Connection con = Jsoup.connect(findURL);
            Document doc = con.get();
            if (con.response().statusCode() == 200) {
                System.out.println("Connect Successful: " + findURL);
                WebScanner.pool.addSeenLink(findURL, currentDepth);

                // System.out.println(findURL + " " + currentDepth);
                return doc;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void crawl() {

        if (currentDepth > maxDepth) {
            WebScanner.pool.removeAllRemaind();
            return;
        }

        try {
            Document document = request();
            if (document == null) {
                URLPool.removeProcUrl();
                return;
            }
            Elements links = document.body().select("a");
            for (var el : links) {
                if (isVaildLink(el.attr("href"))) {
                    int nextDepth = currentDepth + 1;
                    WebScanner.pool.addRemaindLink(el.attr("href"), nextDepth);
                    // System.out.println("ADD: " + el.attr("href") + " " + nextDepth);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
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
