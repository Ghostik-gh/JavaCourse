import java.util.*;

public class URLPool {
    private static int MAX_DEPTH;
    private static int MAX_THREAD;
    private static LinkedList<URLDepthPair> myListRemainder;
    private static int countProcessedURLs;
    private static LinkedList<URLDepthPair> seenLinks;

    public URLPool() {
        myListRemainder = new LinkedList<URLDepthPair>();
        seenLinks = new LinkedList<URLDepthPair>();
        // processedURLs = new LinkedList<URLDepthPair>();
    }

    public static void addProcUrl() {
        countProcessedURLs++;
    }

    public static void removeProcUrl() {
        countProcessedURLs--;
    }

    public static int getProcUrl() {
        return countProcessedURLs;
    }

    public void setMaxDepth(int depth) {
        MAX_DEPTH = depth;
    }

    public int getMaxDepth() {
        return MAX_DEPTH;
    }

    public void setCountThread(int countThread) {
        MAX_THREAD = countThread;
    }

    public int getCountThread() {
        return MAX_THREAD;
    }

    public static void addRemaindLink(String url, int depth) {
        myListRemainder.add(new URLDepthPair(url, depth));
    }

    public synchronized void removeRemaindLink() {
        myListRemainder.remove(0);
    }

    public synchronized void removeAllRemaind() {
        for (int i = 0; i < myListRemainder.size(); ++i) {
            myListRemainder.remove(i);
        }
    }

    public synchronized void addSeenLink(String url, int depth) {
        seenLinks.add(new URLDepthPair(url, depth));
    }

    public synchronized URLDepthPair getRemaindLink() {
        return myListRemainder.isEmpty() ? null : myListRemainder.get(0);
    }

    public synchronized int size() {
        return myListRemainder.size();
    }

    public synchronized LinkedList<URLDepthPair> getSeenList() {
        return seenLinks;
    }

    public void printSeenList() {
        for (int i = 0; i < seenLinks.size(); i++) {
            System.out.println(seenLinks.get(i).toString());
        }
    }
}