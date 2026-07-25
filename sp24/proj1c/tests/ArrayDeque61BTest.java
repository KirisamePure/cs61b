import deque.ArrayDeque61B;
import deque.Deque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class ArrayDeque61BTest {
    @Test
    public void iteratableTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        for (int x : ad1) {
            ad2.addLast(x);
        }
        assertThat(ad2.toList()).containsExactly(null, 1, 2, 3, null, null, null, null).inOrder();
    }

    @Test
    public void equalsTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        Deque61B<Integer> ad2 = new ArrayDeque61B<>();
        ad2.addLast(1);
        ad2.addLast(2);
        ad2.addLast(3);
        assertThat(ad1.equals(ad2)).isTrue();
    }

    @Test
    public void toStringTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        assertThat(ad1.toString()).containsMatch("[1, 2, 3]");
    }
}
