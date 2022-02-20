package com.fgb.binarysearch;

public class ArraySlice {

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