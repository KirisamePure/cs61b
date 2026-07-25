import net.sf.saxon.functions.Minimax;
import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    private static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    public static Comparator<Integer> getIntegerComparator() {
        return new IntegerComparator();
    }

    @Test
    public void IntegerTest() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>(getIntegerComparator());
        mad.addFirst(1);
        mad.addFirst(2);
        mad.addFirst(4);
        assertThat(mad.max()).isEqualTo(4);
    }
}
