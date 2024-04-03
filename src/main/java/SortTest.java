import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SortTest {
    public static void main(final String[] args) {
        long start = System.currentTimeMillis();
        final int[] arrayToSort = readInputArray();
        long finish = System.currentTimeMillis();
        printLog(start, finish, "Reading input array of size: " + arrayToSort.length);


        final int[] cloneArrayToSystemSort = Arrays.copyOf(arrayToSort, arrayToSort.length);

        start = System.currentTimeMillis();
        Arrays.sort(cloneArrayToSystemSort);
        finish = System.currentTimeMillis();
        printLog(start, finish, "Arrays.sort of size: " + arrayToSort.length);

        final int[] cloneArrayToInsertionSort = Arrays.copyOf(arrayToSort, arrayToSort.length);
        start = System.currentTimeMillis();
        insertionSort(cloneArrayToInsertionSort);
        finish = System.currentTimeMillis();
        printLog(start, finish, "insertionSort of size: " + arrayToSort.length);

        testIfSame(cloneArrayToSystemSort, cloneArrayToInsertionSort);
    }

    private static int[] readInputArray() {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int[] inputArray;
        try {
            final int size = Integer.parseInt(br.readLine());
            inputArray = new int[size];

            for (int i = 0; i < size; i++) {
                inputArray[i] = Integer.parseInt(br.readLine());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return inputArray;
    }

    static void printLog(final long start, final long finish, final String message) {
        final long testTime = finish - start;
        System.out.println( message + " took: "+testTime+" milis, "+testTime/1000+" sec " + System.lineSeparator());
    }

    static void insertionSort(final int[] arrayToSort) {
        int min, min_index;
        for (int i = 0; i < arrayToSort.length -1; i++) {
            min = arrayToSort[i];
            min_index = i;
            for (int j = i + 1; j < arrayToSort.length; j++) {
                if (arrayToSort[j] < min) {
                    min = arrayToSort[j];
                    min_index = j;
                }
            }
            swap(arrayToSort, i, min_index);
        }
    }

    static void swap(final int[] array, final int firstIndex, final int secondIndex) {
        final int tmp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = tmp;
    }


    static void testIfSame(final int[] array1, final int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i]) {
                System.out.println("Arrays are not the same on index: " + i);
                System.out.println("Array1[" + i + "] = " + array1[i]);
                System.out.println("Array2[" + i + "] = " + array2[i]);
                return;
            }
        }
    }

}