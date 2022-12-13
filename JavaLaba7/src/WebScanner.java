import java.util.LinkedList;

public class WebScanner {
     private static LinkedList<URLDepthPair> myList = new LinkedList<URLDepthPair>();
     private static LinkedList<URLDepthPair> myListRemainder = new LinkedList<URLDepthPair>();

     public static void start(String url) {
          Crawler craw = new Crawler(url, 1);
          try {
               craw.getThread().join();
          } catch (InterruptedException e) {
               e.printStackTrace();
          }
     }

}
