package com.fgb.binarysearch;

import static org.assertj.core.api.Assertions.assertThat;

import com.fgb.sort.QuickSortInPlace;
import com.fgb.sort.QuickSortSlowImpl;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class QuickSortTest {

  @ParameterizedTest
  @EnumSource(SortSetTest.class)
  void testSlowQuickSort(SortSetTest test) {
    var result = QuickSortSlowImpl.quickSort(test.getContentList());
    assertThat(result).isEqualTo(test.getExpectedList());
  }

  @ParameterizedTest
  @EnumSource(SortSetTest.class)
  void testFastQuickSort(SortSetTest test) {
    var array = test.getContent();
    QuickSortInPlace.quickSort(array);
    assertThat(array).isEqualTo(test.getExpected());
  }

  private enum SortSetTest {
    Example0("abc", "abc"),
    Example1("acb", "abc"),
    Example2("cba", "abc"),
    Example3("efabcghid", "abcdefghi");

    public final String content;

    public final String expected;

    SortSetTest(String content, String expected) {
      this.content = content;
      this.expected = expected;
    }

    public List<Character> getContentList() {
      return content.chars().mapToObj(c -> (char) c).toList();
    }

    public char[] getContent() {
      return content.toCharArray();
    }

    public char[] getExpected() {
      return expected.toCharArray();
    }

    public List<Character> getExpectedList() {
      return expected.chars().mapToObj(c -> (char) c).toList();
    }
  }
}
