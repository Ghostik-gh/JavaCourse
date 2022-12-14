
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
               int size = pool.size();
               System.out.println(size);
               for (int i = 0; i < Math.min(size, pool.getCountThread()); i++) {
                    if (pool.size() == 0) {
                         return;
                    }
                    Crawler craw = new Crawler(pool.getRemaindLink().getUrl(), pool.getRemaindLink().getDepth());
                    try {
                         craw.getThread().join();
                    } catch (InterruptedException e) {
                         e.printStackTrace();
                    }
               }
          }

          return;
     }

     public static void printResult() {
          pool.printSeenList();
          System.out.println(pool.getSeenList().size());

     }

}
