import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {
    @Test
    public void iteratableTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        for (int x : lld1) {
            lld2.addFirst(x);
        }
        assertThat(lld2.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    public void equalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(1);
        lld1.addFirst(2);
        lld1.addFirst(3);
        Deque61B<Integer> lld2 = new LinkedListDeque61B<>();
        lld2.addFirst(1);
        lld2.addFirst(2);
        lld2.addFirst(3);
        assertThat(lld1.equals(lld2)).isTrue();
    }

    @Test
    public void toStringTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        assertThat(lld1.toString()).containsMatch("[1, 2, 3, 4]");
    }
}
