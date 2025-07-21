import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {

    private static final Integer HOW_MANY_CHAR_IN_ENGLISH = 3 * 8 + 2;

    /**
     * Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> result = new TreeMap<>();

        for (int i = 0; i < HOW_MANY_CHAR_IN_ENGLISH; i++) {
            result.put((char) ('a' + i), i + 1);
        }

        return result;
    }

    /**
     * Returns a map from the integers in the list to their squares. For example, if the input list
     * is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> result = new TreeMap<>();

        for (Integer num : nums) {
            result.put(num, num * num);
        }

        return result;
    }

    /**
     * Returns a map of the counts of all words that appear in a list of words.
     */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> result = new TreeMap<>();

        for (String word : words) {

            if (result.containsKey(word)) {
                result.replace(word, result.get(word) + 1);
            } else {
                result.put(word, 1);
            }


        }

        return result;
    }
}
