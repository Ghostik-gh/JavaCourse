
public class WebScanner {

     static URLPool pool;

     public WebScanner(String url, int depth) {
          pool = new URLPool();
          pool.setMaxDepth(depth);
          pool.setCountThread(10);
          pool.addRemaindLink(url, 0);
     }

     public void run() throws InterruptedException {
          while (pool.size() != 0) {
               int poolSize = pool.size();
               int size = Math.min(poolSize, pool.getCountThread());
               // System.out.println(poolSize);
               Crawler[] crawlers = new Crawler[size];
               // for (int i = 0; i < crawlers.length; i++) {
               // Crawler craw = new Crawler("null", -1);
               // }
               for (int i = 0; i < size; i++) {
                    if (pool.size() == 0) {
                         return;
                    }
                    Crawler craw = new Crawler(pool.getRemaindLink().getUrl(), pool.getRemaindLink().getDepth());
                    crawlers[i] = craw;
                    // crawlers[i].updateCraw(pool.getRemaindLink().getUrl(),
                    // pool.getRemaindLink().getDepth()); // = craw;
                    crawlers[i].getThread().run();
               }
               for (int i = 0; i < size; i++) {

               }
          }

          return;
     }

     public static void printResult() {
          pool.printSeenList();
          System.out.println(pool.getSeenList().size());

     }

}
