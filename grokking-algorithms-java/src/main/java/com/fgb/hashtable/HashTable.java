package com.fgb.hashtable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Optional;
import java.util.function.ToIntFunction;

public class HashTable {

  private static final int NUM_BUCKETS = 100;

  private final ArrayList<LinkedList<Par>> hashTable = new ArrayList<>(Collections.nCopies(NUM_BUCKETS, new LinkedList<Par>()));

  private final ToIntFunction<String> hashFunction = (String t) -> {
    var hash = 0;
    for (char c : t.toCharArray()) {
      hash += c;
    }

    return hash % NUM_BUCKETS;
  };

  public void add(String key, String value) {
    var hash = hashFunction.applyAsInt(key);
    hashTable.get(hash).add(new Par(key, value));
  }

  public Optional<String> get(String key) {
    var hash = hashFunction.applyAsInt(key);
    return hashTable.get(hash).stream().filter(p -> p.key.equals(key)).map(p -> p.value).findFirst();
  }

  record Par(String key, String value) {

  }
}
