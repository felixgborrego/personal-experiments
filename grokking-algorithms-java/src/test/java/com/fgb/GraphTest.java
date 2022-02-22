package com.fgb;

import static org.assertj.core.api.Assertions.assertThat;

import com.fgb.bfs.Graph;
import java.util.List;
import org.junit.jupiter.api.Test;

public class GraphTest {

  @Test
  void testGraph() {
    final var graph = new Graph();
    graph.addArrow("a", "b");
    graph.addArrow("a", "c");
    graph.addArrow("a", "d");
    graph.addArrow("b", "c");
    assertThat(graph.listNodes()).isEqualTo(List.of("a", "b", "c", "d"));
    assertThat(graph.nodesFrom("a")).isEqualTo(List.of("b", "c", "d"));
    assertThat(graph.nodesFrom("b")).isEqualTo(List.of("c"));
    assertThat(graph.nodesFrom("c")).isEqualTo(List.of());
  }

}
