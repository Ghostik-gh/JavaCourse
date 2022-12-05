// import java.net.*;

public class URLDepth {
    private String currentURL;
    private int currentDepth;

    public URLDepth(String url, int depth) {
        currentURL = url;
        currentDepth = depth;
    }

    public URLDepth(String url) {
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
        return "<" + currentURL + "\tdepth: " + currentDepth + ">";
    }
}