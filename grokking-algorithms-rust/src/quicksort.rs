#[cfg(test)]
mod tests {
    use crate::quicksort::quick_sort;

    #[test]
    fn test_quick_sort() {
        let arr1 = &mut ['a', 'b', 'c'];
        quick_sort(arr1);
        assert_eq!(arr1, &['a', 'b', 'c']);

        let arr1 = &mut ['a', 'c', 'b', 'd', 'e'];
        quick_sort(arr1);
        assert_eq!(arr1, &['a', 'b', 'c', 'd', 'e' ]);
    }
}

pub fn quick_sort(arr: &mut [char]) {
    qsort(arr, 0, arr.len() - 1);
}

fn qsort(arr: &mut [char], index_low: usize, index_high: usize) {
    if index_low < index_high {
        let pivot_index = partition(arr, index_low, index_high);
        qsort(arr, index_low, pivot_index - 1);
        qsort(arr, pivot_index + 1, index_high);
    }
}

fn partition(arr: &mut [char], index_low: usize, index_high: usize) -> usize {
    let pivot_value = arr[index_high]; // choose the last index as the pivot
    let mut index_for_pivot = index_low as i32 - 1; // We'll search for the better new index for the pivot

    for i in index_low..index_high {
        if arr[i] < pivot_value {
            index_for_pivot += 1;

            arr.swap(i, index_for_pivot as usize);
        }
    }

    // At this point all elements up to index_for_pivot (included) are smaller than the pivot
    index_for_pivot += 1; // This is the right new index for the pivot

    arr.swap(index_high, index_for_pivot as usize);// Move the pivot to it's right index!
    index_for_pivot as usize
}
