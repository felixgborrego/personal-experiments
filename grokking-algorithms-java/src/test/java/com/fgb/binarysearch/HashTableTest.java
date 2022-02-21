package com.fgb.binarysearch;

import static org.assertj.core.api.Assertions.assertThat;

import com.fgb.hashtable.HashTable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class HashTableTest {

  HashTable hashTable = new HashTable();

  @ParameterizedTest
  @EnumSource(HashTableSetTest.class)
  void testRecursive(HashTableSetTest test) {
    hashTable.add(test.name, test.number);
    assertThat(hashTable.get(test.name)).isPresent();
    assertThat(hashTable.get(test.name).get()).isEqualTo(test.number);
  }

  private enum HashTableSetTest {
    Example0("felix", "1234"),
    Example1("juan", "5678"),
    Example2("David", "98765"),
    Example3("Pedro", "3332");

    public final String name;

    public final String number;

    HashTableSetTest(String name, String number) {
      this.name = name;
      this.number = number;
    }
  }
}