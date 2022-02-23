use std::collections::{HashMap, HashSet, VecDeque};

#[cfg(test)]
mod tests {
    use std::collections::HashSet;
    use std::hash::Hash;

    use crate::graph::{breadth_first_search, Graph};

    #[test]
    fn test_graph() {
        let graph = new_grath();

        assert!(unordered_eq(graph.nodes().as_slice(), &["a", "b", "c", "d"]));
        assert!(unordered_eq(graph.arrows_from("a").as_slice(), &["b", "c", "d"]));
        assert!(unordered_eq(graph.arrows_from("b").as_slice(), &["a", "c"]));
        assert!(unordered_eq(graph.arrows_from("c").as_slice(), &[]));
    }

    #[test]
    fn test_bfs() {
        let graph = new_grath();
        assert_eq!(breadth_first_search(&graph, "a", |node| node.eq("c")).unwrap(), "c");
        assert_eq!(breadth_first_search(&graph, "a", |node| node.eq("d")).unwrap(), "d");
    }

    fn new_grath() -> Graph {
        let mut graph = Graph::new();
        graph.add_arrow("a", "b")
            .add_arrow("a", "c")
            .add_arrow("a", "d")
            .add_arrow("b", "c")
            .add_arrow("b", "a");
        return graph;
    }

    fn unordered_eq<T>(a: &[T], b: &[T]) -> bool
        where
            T: Eq + Hash,
    {
        let a: HashSet<_> = a.iter().collect();
        let b: HashSet<_> = b.iter().collect();

        a == b
    }
}


struct Graph {
    nodes: HashMap<String, Vec<String>>,
}

impl Graph {
    fn new() -> Self {
        let nodes: HashMap<String, Vec<String>> = HashMap::new();
        Self { nodes }
    }

    fn add_arrow(&mut self, key: &str, to: &str) -> &mut Graph {
        let list_from = self.nodes.entry(key.to_string()).or_insert(Vec::new());
        list_from.push(to.to_string());
        self.nodes.entry(to.to_string()).or_insert(Vec::new());
        return self;
    }

    fn arrows_from(&self, key: &str) -> Vec<&str> {
        self.nodes.get(key)
            .expect("there must be a node")
            .iter()
            .map(|s| s.as_str()).collect::<Vec<_>>()
    }

    fn nodes(&self) -> Vec<&str> {
        self.nodes.keys().map(|s| s.as_str()).collect::<Vec<_>>()
    }
}

// BFS search the closed connected node to from that satisfied the condition.
fn breadth_first_search<'a>(graph: &'a Graph, node_from: &'a str, condition: fn(&str) -> bool) -> Option<&'a str> {
    let mut visited = HashSet::new();
    let mut queue = VecDeque::new();
    queue.push_back(node_from);
    while !queue.is_empty() {
        let node = queue.pop_front().expect("There must be an element");
        visited.insert(node);

        if condition(node) {
            return Option::Some(node);
        } else {
            for node_connected in graph.arrows_from(node) {
                queue.push_back(node_connected);
            }
        }
    }

    return Option::None;
}