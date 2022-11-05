
import java.util.Random;
public class GenerateArr {
  public static int[] generateRandomArray(int k) {
    int upperBound = (int) Math.pow(2, k);
    int[] randomArray = new int[upperBound];
    for (int i = 0; i < randomArray.length; i++) {
      Random r = new Random();
      int element = r.nextInt(1001);
      randomArray[i] = element;
    }
    return randomArray;
  }

}
