import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SortTest {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        long start = System.currentTimeMillis();
        final int[] arrayToSort = readInputArray();
        long finish = System.currentTimeMillis();
        printLog(start, finish, "Reading input array of size: " +arrayToSort.length);

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


}