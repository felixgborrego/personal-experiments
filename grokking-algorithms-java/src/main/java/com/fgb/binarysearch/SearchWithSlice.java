package com.fgb.binarysearch;

import java.util.List;
import java.util.Optional;

public class SearchWithSlice {

  public static Optional<Integer> searchWithSlice(char[] array, char value) {
    return searchWithSlice(new ArraySlice(array, 0, array.length), value);
  }

  private static Optional<Integer> searchWithSlice(ArraySlice view, char value) {
    while (!view.isEmpty()) {
      var midValue = view.midValue();
      if (midValue == value) {
        return Optional.of(view.midIndex());
      } else if (midValue < value) {
        view.subViewBiggerThanMidValue();
      } else {
        view.subViewSmallerThanMidValue();
      }
    }
    return Optional.empty();
  }

  public static <T extends Comparable> Optional<T> searchWithSubList(List<T> view, Comparable<T> value) {
    while (!view.isEmpty()) {
      var midIndex = view.size() / 2;
      var midValue = view.get(midIndex);
      var comparation = midValue.compareTo(value);
      if (comparation == 0) {
        return Optional.of(midValue);
      } else if (comparation < 0) {
        view = view.subList(midIndex, view.size());
      } else {
        view = view.subList(0, midIndex);
      }
    }
    return Optional.empty();
  }


  private static class ArraySlice {

    private final char[] array;

    private int minIndex;

    private int maxIndex; // Note index max is exclusive.

    public ArraySlice(char[] array) {
      this(array, 0, array.length);
    }

    public ArraySlice(char[] array, int minIndex, int maxIndex) {
      this.array = array;
      this.minIndex = minIndex;
      this.maxIndex = maxIndex;
    }

    int midIndex() {
      return minIndex + (maxIndex - minIndex) / 2;
    }

    char midValue() {
      return array[midIndex()];
    }

    boolean isEmpty() {
      return minIndex == maxIndex;
    }

    ArraySlice subViewSmallerThanMidValue() {
      maxIndex = midIndex();
      return this;
    }

    ArraySlice subViewBiggerThanMidValue() {
      this.minIndex = midIndex();
      return this;
    }
  }
}
