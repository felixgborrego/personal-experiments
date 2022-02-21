#[cfg(test)]
mod tests {
    use crate::hashtable::HashTable;

    #[test]
    fn test_hash_table() {
        let mut hash_table = HashTable::new();
        hash_table.add("felix", "1234");
        hash_table.add("juan", "6432");

        assert_eq!(hash_table.get("felix").unwrap(), "1234");
        assert_eq!(hash_table.get("juan").unwrap(), "6432");
        assert_eq!(hash_table.get("pedro"), Option::None);
    }
}

const NUM_BUCKETS: usize = 100;

pub struct HashTable {
    buckets: Vec<Vec<(String, String)>>,
}

impl HashTable {
    pub fn new() -> Self {
        let mut buckets: Vec<Vec<(String, String)>> = Vec::with_capacity(NUM_BUCKETS);
        // Init all buckets
        for _ in 0..NUM_BUCKETS {
            let v = Vec::new();
            buckets.push(v);
        }

        Self { buckets }
    }

    pub fn add(&mut self, key: &str, value: &str) {
        let hash = to_hash(&key);
        let list = &mut self.buckets[hash];
        list.push((key.to_string(), value.to_string()));
    }

    pub fn get(&mut self, key: &str) -> Option<String> {
        let hash = to_hash(&key);
        let list = &mut self.buckets[hash];
        list.iter().find(|(key, _)| key.eq(key))
            .map(|(_, value)| value.clone())
    }
}

fn to_hash(key: &str) -> usize {
    key.chars().map(|c| c as usize).sum::<usize>() % NUM_BUCKETS
}
