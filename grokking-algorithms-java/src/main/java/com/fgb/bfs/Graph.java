package com.fgb.bfs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

  private final Map<String, LinkedList<String>> nodes = new HashMap<>();

  public List<String> listNodes() {
    return nodes.keySet().stream().toList();
  }

  public List<String> nodesFrom(String node) {
    return nodes.getOrDefault(node, new LinkedList<>());
  }

  public void addArrow(String fromNode, String toNode) {
    final var arrowsFrom = nodes.computeIfAbsent(fromNode, key -> new LinkedList<>());
    arrowsFrom.add(toNode);
    nodes.computeIfAbsent(toNode, key -> new LinkedList<>());
  }
}

