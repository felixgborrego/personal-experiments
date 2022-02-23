package com.fgb;

import static org.assertj.core.api.Assertions.assertThat;

import com.fgb.bfs.BreadthFirstSearch;
import com.fgb.bfs.Graph;
import java.util.List;
import org.junit.jupiter.api.Test;

public class BfsTest {

  @Test
  void testGraph() {
    final var graph = newGraph();
    assertThat(graph.listNodes()).isEqualTo(List.of("a", "b", "c", "d"));
    assertThat(graph.nodesFrom("a")).isEqualTo(List.of("b", "c", "d"));
    assertThat(graph.nodesFrom("b")).isEqualTo(List.of("a", "c"));
    assertThat(graph.nodesFrom("c")).isEqualTo(List.of());
  }

  @Test
  void testBfs() {
    final var graph = newGraph();
    var result = BreadthFirstSearch.findNode(graph, "a", n -> n.equals("c"));
    assertThat(result.get()).isEqualTo("c");
  }

  private Graph newGraph() {
    final var graph = new Graph();
    graph.addArrow("a", "b");
    graph.addArrow("b", "a");
    graph.addArrow("a", "c");
    graph.addArrow("a", "d");
    graph.addArrow("b", "c");
    return graph;
  }


}
