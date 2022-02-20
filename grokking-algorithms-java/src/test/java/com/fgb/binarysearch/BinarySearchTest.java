package com.fgb.binarysearch;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class BinarySearchTest {

  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testRecursive(SearchSetTest test) {
    var result = RecursiveBinarySearch.search(test.content, test.itemToSearch);
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(test.expectedIndex);
  }

  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testRecursiveSlice(SearchSetTest test) {
    var result = RecursiveBinarySearch.searchRecursiveWithSlice(test.content, test.itemToSearch);
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(test.expectedIndex);
  }

  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testSearchLoopWithSlice(SearchSetTest test) {
    var result = SearchWithSlice.searchWithSlice(test.content, test.itemToSearch);
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(test.expectedIndex);
  }

  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testSearchWithSlice(SearchSetTest test) {
    var result = SearchWithSlice.searchWithSlice(test.content, test.itemToSearch);
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(test.expectedIndex);
  }

  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testSearchWithSubList(SearchSetTest test) {
    ArrayList<Character> list = new ArrayList<>(new String(test.content).chars().mapToObj(c -> (char) c).toList());
    var result = SearchWithSlice.searchWithSubList(list, test.itemToSearch);
    assertThat(result).isPresent();
    assertThat(result.get()).isEqualTo(list.get(test.expectedIndex));
  }

  // Just an example using the native implementation
  @ParameterizedTest
  @EnumSource(SearchSetTest.class)
  void testNative(SearchSetTest test) {
    var result = Arrays.binarySearch(test.content, test.itemToSearch);
    assertThat(result).isEqualTo(test.expectedIndex);
  }

  private enum SearchSetTest {
    Example1("abc", 'a', 0),
    Example2("abc", 'c', 2),
    Example3("abcde", 'c', 2);

    public final char[] content;

    public final char itemToSearch;

    public final int expectedIndex;

    SearchSetTest(String content, char itemToSearch, int expectedIndex) {
      this.content = content.toCharArray();
      this.itemToSearch = itemToSearch;
      this.expectedIndex = expectedIndex;
    }
  }

}
