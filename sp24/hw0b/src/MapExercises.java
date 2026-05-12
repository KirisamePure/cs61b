import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.lang.Math.pow;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character, Integer> map = new TreeMap<>();
        for (int i = 1, j = (int)'a' ;i <= 26 && j <= (int)'z'; i++, j++) {
            map.put((char)j, i);
        }
        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.size(); i++) {
            map.put(nums.get(i), (int)Math.pow(nums.get(i), 2));
        }
        return map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> map = new TreeMap<>();

        int ctn = 0;
        int tempctn = ctn;

        for (int i = tempctn; i < words.size(); i++) {
            if (i < tempctn) {
                continue;
            }

            ctn = 0;

            for (int j = tempctn; j < words.size(); j++) {
                if (words.get(i).equals(words.get(j))) {
                   ctn++;
                } else {
                    break;
                }
            }

            tempctn += ctn;
            map.put(words.get(i), ctn);
        }
        return map;
    }
}
