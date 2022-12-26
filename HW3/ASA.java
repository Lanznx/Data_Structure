import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class ASA {

  // 初始化
  private static int[][] arrays = new int[][] { new int[1], new int[2] };
  private static int[][] tempArrays = new int[][] { new int[1], new int[2] };

  public static void insert(int value) {

    for (int i = 0;; i++) {

      // 只會插入數字進去最小的 tempArray
      if (i == 0) {
        tempArrays[0][0] = value;
      }

      if (arrays[i][0] == 0) {
        // if array 為空, 將資料複製進去 array
        arrays[i] = tempArrays[i];
        // 清除 temp array
        tempArrays[i] = new int[tempArrays[i].length];

        // close the loop
        return;

      } else {

        // 若 array 不為空，則需要將 temp array 與 array 進行 merge
        // 並將結果（較大的 array）放去 i+1 層的 temp array
        try {
          tempArrays[i + 1] = merge(arrays[i], tempArrays[i]);
        } catch (Exception IndexOutOfBoundsException) {
          // 若 array 空間不足，則延伸
          arrays = extend(arrays);
          tempArrays = extend(tempArrays);
          tempArrays[i + 1] = merge(arrays[i], tempArrays[i]);
        }
        // 清理原先的 array
        arrays[i] = new int[arrays[i].length];
        tempArrays[i] = new int[tempArrays[i].length];
      }
    }
  }

  public static void search(int value) {

    for (int i = 0; i < arrays.length; i++) {
      if (Arrays.binarySearch(arrays[i], value) > 0) {
        // Java Library 內建函示，若沒搜尋到，則回傳 -1
        return;
      }
    }
  }

  private static int[] merge(int[] array1, int[] array2) {
    int[] output = new int[array1.length + array2.length];

    int index1 = 0; // 第一個 array 對應的 index
    int index2 = 0; // 第二個 array 對應的 index
    int indexOutput = 0; // output array 對應的 index

    while (index1 < array1.length || index2 < array2.length) {
      // 任一 index 大於 array 長度時，結束迴圈
      if (index1 >= array1.length) {
        output[indexOutput] = array2[index2];
        indexOutput++;
        index2++;
      } else if (index2 >= array2.length) {
        output[indexOutput] = array1[index1];
        indexOutput++;
        index1++;
      } else if (array1[index1] > array2[index2]) {
        output[indexOutput] = array2[index2];
        indexOutput++;
        index2++;
      } else if (array1[index1] < array2[index2]) {
        output[indexOutput] = array1[index1];
        indexOutput++;
        index1++;
      } else if (array1[index1] == array2[index2]) {
        output[indexOutput] = array1[index1];
        indexOutput++;
        index1++;
        output[indexOutput] = array2[index2];
        indexOutput++;
        index2++;
      }
    }
    return output;
  }


  private static int[][] extend(int[][] array) {

    int[][] output = new int[array.length + 1][0];
    for (int i = 0; i < array.length; i++) {
      output[i] = array[i];
    }
    output[array.length] = new int[(int) Math.pow(2, array.length)];
    return output;
  }

  public static void main(String[] args) {
    for (int i = 10; i <= 26; i++) {
      arrays = new int[][] { new int[1], new int[2] };
      tempArrays = new int[][] { new int[1], new int[2] };

      long insertStartTime = System.nanoTime();
      for (int j = 0; j < Math.pow(2, i); j++) {
        insert(ThreadLocalRandom.current().nextInt(1, (int) Math.pow(2, 30) + 1));
      }
      long insertEndTime = System.nanoTime();
      for (int r = 0; r < Math.pow(10, 5); r++) {
        search(ThreadLocalRandom.current().nextInt(1, (int) Math.pow(2, 30) + 1));
      }
      long searchEndTime = System.nanoTime();
      System.out.printf("%d,%d\n", (insertEndTime - insertStartTime), (searchEndTime - insertEndTime));
      arrays = null;
      tempArrays = null;
    }
  }
}