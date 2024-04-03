import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class GenerateArray {

    public static void main(final String[] args) {
        long start = System.currentTimeMillis();

        final long arraySize = getTestArraySize(args);
        final long maxNumber = getMaxNumberSize(args, arraySize);

        final String path = "resources/array_to_sort_" + arraySize + ".txt";
        final File file = new File(path);
        try {
            file.createNewFile();
        } catch (final IOException e) {
            System.out.println( "Cannot create file: " + path + ", because: " + e.getMessage());
            return;
        }

        final Path filePath = Paths.get(path);
        final Random r = new Random();
        try (BufferedWriter bw = Files.newBufferedWriter(filePath)) {
            bw.write(Long.toString(arraySize));
            bw.newLine();
            for (long i = 0; i < arraySize; i++) {
                bw.write(Long.toString(r.nextLong(maxNumber)));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Cannot write to file: " + path + ", because: " + e.getMessage());
            return;
        }


        long finish = System.currentTimeMillis();
        System.out.println("Created file: " + path);
        printLog(start, finish, "File creation took: " + path);

    }

    private static long getMaxNumberSize(final String[] args, final long arraySize) {
        long maxNumSize = arraySize/2;
        if (args.length > 1) {
            try {
                maxNumSize = Long.parseLong(args[1]);
            } catch (final NumberFormatException ignore) {}
        }
        if (maxNumSize < 2) {
            maxNumSize = 2;
        }
        if (maxNumSize > Integer.MAX_VALUE) {
            maxNumSize = Integer.MAX_VALUE;
        }
        return maxNumSize;
    }

    private static long getTestArraySize(final String[] args) {
        long arraySize = 1_000_000;
        if (args.length > 0) {
            try {
                arraySize = Long.parseLong(args[0]);
            } catch (final NumberFormatException ignore) {}
        }
        if (arraySize < 1) {
             arraySize = 1_000_000;
        }
        return arraySize;
    }

    static void printLog(final long start, final long finish, final String message) {
        final long testTime = finish - start;
        System.out.println( message + " took: "+testTime+" milis, "+testTime/1000+" sec " + System.lineSeparator());
    }


}
