package main

import (
    "testing"
)

func TestHelloName(t *testing.T) {
    array := []int{1, 2, 3, 4, 5, 6}
    arrayToSort := []int{6, 2, 4, 5, 3, 1}
    insertionSort(arrayToSort)
    for i := 0; i < len(array); i++ {
        if (array[i] != arrayToSort[i]) {
            t.Errorf("array[%d] = %d != arrayToSort[%d] = %d", i, array[i], i, arrayToSort[i])
        }
    }
}