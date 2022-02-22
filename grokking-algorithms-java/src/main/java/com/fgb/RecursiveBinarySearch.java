package com.fgb;

import java.util.Optional;

/**
 * Binary search implemented as recursive.
 */
public class RecursiveBinarySearch {

  public static Optional<Integer> search(char[] array, char item) {
    return searchRecursive(array, item, 0, array.length - 1);
  }

  private static Optional<Integer> searchRecursive(char[] array, char item, int minIndex, int maxIndex) {
    if (minIndex > maxIndex) {
      return Optional.empty();
    } else {
      var mid = minIndex + (maxIndex - minIndex) / 2;
      var guess = array[mid];
      if (guess < item) {
        return searchRecursive(array, item, mid + 1, maxIndex);
      } else if (guess > item) {
        return searchRecursive(array, item, minIndex, mid);
      } else {
        return Optional.of(mid);
      }
    }
  }

  public static Optional<Integer> searchRecursiveWithSlice(char[] array, char value) {
    return searchRecursiveWithSlice(new ArraySlice(array, 0, array.length), value);
  }

  private static Optional<Integer> searchRecursiveWithSlice(ArraySlice view, char value) {
    if (view.isEmpty()) {
      return Optional.empty();
    } else {
      var midValue = view.midValue();
      if (midValue == value) {
        return Optional.of(view.midIndex());
      } else if (midValue < value) {
        return searchRecursiveWithSlice(view.subViewBiggerThanMidValue(), value);
      } else {
        return searchRecursiveWithSlice(view.subViewSmallerThanMidValue(), value);
      }
    }
  }

}
