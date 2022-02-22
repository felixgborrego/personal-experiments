use std::collections::HashMap;

#[cfg(test)]
mod tests {
    use std::collections::HashSet;
    use std::hash::Hash;

    use crate::graph::Graph;

    #[test]
    fn test_graph() {
        let mut graph = Graph::new();
        graph.add_arrow("a", "b")
            .add_arrow("a", "c")
            .add_arrow("a", "d")
            .add_arrow("b", "c");

        assert!(unordered_eq(graph.nodes().as_slice(), &["a", "b", "c", "d"]));
        assert!(unordered_eq(graph.arrows_from("a").as_slice(), &["b", "c", "d"]));
        assert!(unordered_eq(graph.arrows_from("b").as_slice(), &["c"]));
        assert!(unordered_eq(graph.arrows_from("c").as_slice(), &[]));
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