class MergeSort {

  public void sort(int[] arr, int left, int right) {
    if (left < right) {
      int middle = left + (right - left) / 2;
      sort(arr, left, middle);
      sort(arr, middle + 1, right);

      merge(arr, left, middle, right);
    }
  }

  public void merge(int[] arr, int left, int middle, int right) {
    int n1 = middle - left + 1;
    int n2 = right - middle;

    int[] leftArr = new int[n1];
    int[] rightArr = new int[n2];

    for (int i = 0; i < n1; i++) {
      leftArr[i] = arr[left + i];
    }
    for (int j = 0; j < n2; j++) {
      rightArr[j] = arr[middle + 1 + j];
    }
    int i = 0, j = 0;
    int sorted = left;

    while (i < n1 && j < n2) {
      if (leftArr[i] < rightArr[j]) {
        arr[sorted] = leftArr[i];
        i++;
      } else {
        arr[sorted] = rightArr[j];
        j++;
      }
      sorted++;
    }
    while (i < n1) {
      arr[sorted] = leftArr[i];
      i++;
      sorted++;
    }
    while (j < n2) {
      arr[sorted] = rightArr[j];
      j++;
      sorted++;
    }
  }

  public static void main(String[] args) {
    GenerateArr generateArr = new GenerateArr();
    MergeSort mergeSort = new MergeSort();
    for (int i = 15; i < 28; i++) {
      int[] arr = generateArr.generateRandomArray(i);
      long startTime = System.nanoTime();
      mergeSort.sort(arr, 0, arr.length - 1);
      long endTime = System.nanoTime();
      System.out.printf("%d\n", endTime - startTime);
    }

  }
}
