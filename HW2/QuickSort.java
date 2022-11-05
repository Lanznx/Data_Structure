
class QuickSort {
  static void Swap(int[] array, int position1, int position2) {
    int temp = array[position1];
    array[position1] = array[position2];
    array[position2] = temp;
  }

  static int lomutoPartition(int[] array, int min, int max) {
    int pivot = array[max];
    int mark = min;
    for (int i = min; i < max; i++) {
      if (array[i] < pivot) {
        Swap(array, mark, i);
        mark++;
      }
    }
    Swap(array, mark, max);
    return mark;
  }

  static void lomutoQuickSort(int[] array, int min, int max) {
    if (min < max) {
      int pivot = lomutoPartition(array, min, max);
      lomutoQuickSort(array, min, pivot - 1);
      lomutoQuickSort(array, pivot + 1, max);
    }
  }

  static int hoarePartition(int[] arr, int min, int max) {
    int pivot = arr[min];
    int left = min - 1;
    int right = max + 1;

    while (true) {
      do {
        left++;
      } while (arr[left] < pivot);
      do {
        right--;
      } while (arr[right] > pivot);

      if (left >= right)
        return right;
      Swap(arr, left, right);
    }
  }

  static void hoareQuickSort(int[] array, int min, int max) {
    if (min < max) {
      int pivot = hoarePartition(array, min, max);
      hoareQuickSort(array, min, pivot);
      hoareQuickSort(array, pivot + 1, max);
    }
  }

  static void threeWayPartition(int[] array, int min, int max, int[] pivot) {
    int pivotValue = array[pivot[0]];
    int i = min;
    int j = min;
    int k = max;

    while (j <= k) {
      if (array[j] < pivotValue) {
        Swap(array, i, j);
        i++;
        j++;
      } else if (array[j] > pivotValue) {
        Swap(array, j, k);
        k--;
      } else {
        j++;
      }
    }
    pivot[0] = i;
    pivot[1] = k;
  }

  static void threeWayQuickSort(int[] array, int min, int max) {
    if (min < max) {
      int[] pivot = new int[2];
      pivot[0] = min;
      pivot[1] = max;
      threeWayPartition(array, min, max, pivot);
      threeWayQuickSort(array, min, pivot[0] - 1);
      threeWayQuickSort(array, pivot[1] + 1, max);
    }
  }

  public static void main(String[] args) {
    GenerateArr generateArr = new GenerateArr();
    QuickSort quickSort = new QuickSort();
    // for (int i = 23; i < 28; i++) {
    // int[] arr = generateArr.generateRandomArray(i);
    // long startTime = System.nanoTime();
    // quickSort.lomutoQuickSort(arr, 0, arr.length - 1);
    // long endTime = System.nanoTime();
    // System.out.printf("%d\n", endTime - startTime);
    // }

    // System.out.printf("===================\n");
    
    // for (int i = 15; i < 28; i++) {
    //   int[] arr = generateArr.generateRandomArray(i);
    //   long startTime = System.nanoTime();
    //   quickSort.hoareQuickSort(arr, 0, arr.length - 1);
    //   long endTime = System.nanoTime();
    //   System.out.printf("%d\n", endTime - startTime);
    //   for (int j = 0; j < arr.length; j++) {
    //     System.out.println(arr[j]);
    //   }
    // }

    // System.out.printf("===================\n");

    for (int i = 15; i < 28; i++) {
      int[] arr = generateArr.generateRandomArray(i);
      long startTime = System.nanoTime();
      quickSort.threeWayQuickSort(arr, 0, arr.length - 1);
      long endTime = System.nanoTime();
      System.out.printf("%d\n", endTime - startTime);
    }

  }

}
