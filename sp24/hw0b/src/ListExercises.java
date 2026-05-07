import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        int Sum = 0;

        if (L.size() == 0) {
            return 0;
        }

        for (int i = 0; i < L.size(); i++) {
            Sum += L.get(i);
        }

        return Sum;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> evenL = new ArrayList<>();

        for (int i = 0; i < L.size(); i++) {
            if (i % 2 != 0) {
                evenL.add(L.get(i));
            }
        }
        return evenL;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> Common = new ArrayList<>();

        for (int i = 0; i < L2.size(); i++) {
            if (L1.contains(L2.get(i))) {
               Common.add(L2.get(i));
            }
        }
        return Common;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int occur = 0;
        for (int i = 0; i < words.size(); i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
               if (words.get(i).charAt(j) == c) {
                   occur++;
               }
            }
        }
        return occur;
    }
}
