import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        AtomicInteger sum = new AtomicInteger();
        L.forEach(sum::addAndGet);
        return sum.get();
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        ArrayList<Integer> result = new ArrayList<>();

        L.forEach(i -> {
            if (i % 2 == 0) {
                result.add(i);
            }
        });

        return result;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer i : L1) {
            if (L2.contains(i)) {
                result.add(i);
            }
        }

        return result;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;

        for (String word : words) {
            byte[] bytes = word.getBytes(StandardCharsets.UTF_8);
            for (byte b : bytes) {
                if (c == (char) b) {
                    count++;
                }
            }
        }

        return count;
    }
}
