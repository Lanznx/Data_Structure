
public class CountingSort {
  static void countingSort(int[] array, int min, int max) {
    int[] count = new int[max - min + 1];
    for (int i = 0; i < array.length; i++) {
      count[array[i] - min]++;
    }
    int z = 0;
    for (int i = min; i <= max; i++) {
      while (count[i - min] > 0) {
        array[z++] = i;
        count[i - min]--;
      }
    }
  }

  public static void main(String[] args) {
    GenerateArr generateArr = new GenerateArr();
    for (int i = 15; i < 31; i++) {
      int[] array = generateArr.generateRandomArray(i);
      long startTime = System.nanoTime();
      countingSort(array, 0, 1000);
      long endTime = System.nanoTime();
      System.out.println(endTime - startTime);
    }
  }
}
