package main

import (
    "fmt"
    "time"
    "bufio"
    "os"
    "strconv"
    "sort"
)

func main() {
    var start int64 = time.Now().UnixMilli()
    var inputArray []int = readInputArray()
    var stop int64 = time.Now().UnixMilli()
    printLog(start, stop, len(inputArray), "Reading input array of size: ")

    var cloneArrayToSystemSort []int = make([]int, len(inputArray))
    copy(cloneArrayToSystemSort, inputArray)

    start = time.Now().UnixMilli()
    systemSortArray(cloneArrayToSystemSort)
    stop = time.Now().UnixMilli()
    printLog(start, stop, len(cloneArrayToSystemSort), "systemSortArray of size: ")

    var cloneArrayToInsertionSort []int = make([]int, len(inputArray))
    copy(cloneArrayToInsertionSort, inputArray)

    start = time.Now().UnixMilli()
    insertionSort(cloneArrayToInsertionSort)
    stop = time.Now().UnixMilli()
    printLog(start, stop, len(cloneArrayToInsertionSort), "insertionSort of size: ")
}


func systemSortArray(array []int) {
    sort.Ints(array)
}

func insertionSort(array []int) {
    emptyprintArraySize(array, 0)//wtf is working faster?
    var min int
    var min_index int
    for i := 0; i < len(array)-1; i++ {
        min = array[i]
        min_index = i
        for j := i; j < len(array); j++ {
            if (array[j] < min) {
                min = array[j]
                min_index = j
            }
        }
        swap(array, i, min_index)
    }
}

func swap(array []int, firstIndex int, secondIndex int) {
    var tmp int = array[firstIndex]
    array[firstIndex] = array[secondIndex]
    array[secondIndex] = tmp
}

func readInteger(scanner *bufio.Scanner) int {
    scanner.Scan()
    integer, _ := strconv.Atoi(scanner.Text())
    return integer
}

func readInputArray() []int {
    scanner := bufio.NewScanner(os.Stdin)

    var arraySize int = readInteger(scanner)
    var inputArray []int = make([]int, arraySize)

    for i := 0; i < len(inputArray); i++ {
        inputArray[i] = readInteger(scanner)
    }

    //printArraySize(inputArray, 13)
    return inputArray
}

func printArray(array []int) {
    printArraySize(array, len(array))
}

func printArraySize(array []int, printElements int) {
    fmt.Printf("Array of size: %d\n", len(array))
    for i := 0; i < printElements; i++ {
        fmt.Printf("%d, ", array[i])
    }
    fmt.Printf("..\n")
}

func emptyprintArraySize(array []int, printElements int) {
    for i := 0; i < printElements; i++ {
        fmt.Printf("%d, ", array[i])
    }
}

func printLog(start int64, finish int64, size int, message string) {
    var testTime = finish - start
    fmt.Printf(message)
    fmt.Printf("%d took: %d milis, %d sec \n", size, testTime, testTime/1000)
}
