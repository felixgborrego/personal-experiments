package com.fgb.sort;

/**
 * Quick in-place quicksort implementation.
 */
public class QuickSortInPlace {

  public static void quickSort(char[] arr) {
    quickSort(arr, 0, arr.length - 1);
  }

  private static void quickSort(char[] arr, int lowIndex, int highIndex) {
    if (lowIndex < highIndex) {
      var pivotIndex = partition(arr, lowIndex, highIndex);
      quickSort(arr, lowIndex, pivotIndex - 1);
      quickSort(arr, pivotIndex + 1, highIndex);
    }
  }

  private static int partition(char[] arr, int lowIndex, int highIndex) {
    var pivot = arr[highIndex];
    // All elements from lowIndex to indexSorted are checked to be smaller than pivot
    // This is the best index for the pivot found so far
    var indexWithAllSmallerThanPivot = lowIndex - 1; // So no one is sorted yet.

    for (int currentIndex = lowIndex; currentIndex < highIndex; currentIndex++) {
      if (arr[currentIndex] < pivot) {
        indexWithAllSmallerThanPivot++;
        swap(arr, indexWithAllSmallerThanPivot, currentIndex);
      }
    }

    // After this step, all elements under indexWithAllSmallerThanPivot are actually smaller!
    // So the next is the right index for the pivot
    indexWithAllSmallerThanPivot ++;
    swap(arr, indexWithAllSmallerThanPivot, highIndex);
    return indexWithAllSmallerThanPivot;
  }

  private static void swap(char[] arr, int indexWithAllSmallerThanPivot, int currentIndex) {
    var tmp = arr[indexWithAllSmallerThanPivot];
    arr[indexWithAllSmallerThanPivot] = arr[currentIndex];
    arr[currentIndex] = tmp;
  }
}
