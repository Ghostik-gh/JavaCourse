// import java.net.*;

public class URLDepthPair {
    private String currentURL;
    private int currentDepth;

    public URLDepthPair(String url, int depth) {
        currentURL = url;
        currentDepth = depth;
    }

    public URLDepthPair(String url) {
        currentURL = url;
        currentDepth = 1;
    }

    public String getUrl() {
        return currentURL;
    }

    public int getDepth() {
        return currentDepth;
    }

    public String toString() {
        return "<" + currentURL + "\tdepth: " + currentDepth + ">\n";
    }
}