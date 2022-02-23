package com.fgb.bfs;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Optional;
import java.util.function.Predicate;

public class BreadthFirstSearch {

  /**
   * Find the first node with a path from nodeFrom that satisfied the condition.
   */
  public static Optional<String> findNode(Graph graph, String nodeFrom, Predicate<String> condition) {
    var alreadyVisited = new HashSet<String>();
    var queue = new ArrayDeque<String>();
    queue.add(nodeFrom);
    while (!queue.isEmpty()) {
      var node = queue.pop();
      alreadyVisited.add(node);
      if (condition.test(node)) {
        return Optional.of(node);
      } else {
        var nodesFrom = graph.nodesFrom(node);
        queue.addAll(nodesFrom);
      }
    }
    return Optional.empty();
  }
}
