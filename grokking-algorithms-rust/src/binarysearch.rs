use std::cmp::Ordering;

#[cfg(test)]
mod tests {
    use crate::binarysearch::binary_search;

    #[test]
    fn test_binary_search() {
        assert_eq!(binary_search(&['a', 'b', 'c'], 'a').unwrap(), 0);
        assert_eq!(binary_search(&['a', 'b', 'c'], 'b').unwrap(), 1);
        assert_eq!(binary_search(&['a', 'b', 'c'], 'c').unwrap(), 2);
        assert_eq!(binary_search(&['a', 'b', 'c', 'd'], 'c').unwrap(), 2);
        assert!(binary_search(&['a', 'b', 'c', 'd'], 'f').is_none());
    }
}

type Index = usize;

struct Slice<'e> {
    elements: &'e [char],
    min_index: Index,
    max_index: Index,
}

fn binary_search(elements: &[char], element: char) -> Option<Index> {
    let slice = &mut Slice { elements, min_index: 0, max_index: elements.len() };
    slice.binary_search(element)
}

impl<'a> Slice<'a> {
    fn mid_index(&self) -> Index {
        self.min_index + (self.max_index - self.min_index) / 2
    }

    fn binary_search(&mut self, element: char) -> Option<Index> {
        if self.min_index == self.max_index {
            Option::None
        } else {
            let mid_index = self.mid_index();
            let mid_value = self.elements[mid_index];
            match mid_value.cmp(&element) {
                Ordering::Equal => Option::Some(mid_index),
                Ordering::Less => {
                    self.min_index = mid_index + 1;
                    self.binary_search(element)
                }
                Ordering::Greater => {
                    self.max_index = mid_index;
                    self.binary_search(element)
                }
            }
        }
    }
}