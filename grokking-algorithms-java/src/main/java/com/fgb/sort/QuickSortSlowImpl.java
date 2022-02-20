package com.fgb.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Slow implementation using high level abstractions.
 */
public class QuickSortSlowImpl {

  public static <A extends Comparable> List<A> quickSort(List<A> list) {
    if (list.size() < 2) {
      return list;
    } else {
      var pivotValue = list.get(0);
      var partitions = partitionByValue(list.subList(1, list.size()), pivotValue);
      List<A> newList = new ArrayList<A>();
      newList.addAll(quickSort(partitions.left));
      newList.add(pivotValue);
      newList.addAll(quickSort(partitions.rigt));
      return newList;
    }
  }

  private static <A extends Comparable> Partition<A> partitionByValue(List<A> list, A pivotValue) {
    return new Partition(
        list.stream().filter(v -> v.compareTo(pivotValue) < 0).toList(),
        list.stream().filter(v -> v.compareTo(pivotValue) > 0).toList());
  }

  record Partition<A>(List<A> left, List<A> rigt) {

  }
}
