import java.util.Hashtable;
import java.util.Random;

class Hashtable1 {
  public static void main(String args[]) {
    for (int i = 10; i < 30; i++) {
      Hashtable<Integer, Integer> hm = new Hashtable<Integer, Integer>();
      long insertStartTime = System.nanoTime();
      for (int j = 0; j < Math.pow(2, i); j++) {
          Random r = new Random();
          int upperBound = (int) Math.pow(2, 30);
          int ans = r.nextInt(upperBound);
          hm.put(ans, ans);
      }

      long insertEndTime = System.nanoTime();
      for (int j = 0; j < 100000; j++) {
          Random r = new Random();
          int upperBound = (int) Math.pow(2, 30);
          int ans = r.nextInt(upperBound);
          hm.get(ans);
      }
      long searchEndTime = System.nanoTime();
      System.out.printf("%d,%d\n",
              insertEndTime - insertStartTime,
              searchEndTime - insertEndTime);
      
    }
  }
}