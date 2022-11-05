public class InsertionSort {
  public long sort(int[] randomArray) {
    long startTime = System.nanoTime();

    for (int i = 0; i < randomArray.length; i++) {
      int j = i;
      while (j - 1 > -1 && randomArray[j] > randomArray[j - 1]) {
        int temp = randomArray[j];
        randomArray[j] = randomArray[j - 1];
        randomArray[j - 1] = temp;
        j--;
      }
    }
    long endTime = System.nanoTime();
    return endTime - startTime;

  }

  public static void main(String[] args) {
    GenerateArr generateArr = new GenerateArr();
    InsertionSort insertionSort = new InsertionSort();
    for (int i = 22; i < 28; i++) {
      int[] arr = generateArr.generateRandomArray(i);
      long startTime = System.nanoTime();
      insertionSort.sort(arr);
      long endTime = System.nanoTime();
      System.out.printf("%d\n", endTime - startTime);
    }

  }
}
