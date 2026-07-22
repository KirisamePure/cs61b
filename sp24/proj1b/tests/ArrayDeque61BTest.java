import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
    public void addFirstAndSizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.size() == 3).isTrue();
        assertThat(ad1.get(6) == 3).isTrue();
    }

    @Test
    public void addLastAndSizeTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.addLast(5);
        ad1.addLast(6);
        ad1.addLast(7);
        ad1.addLast(8);
        ad1.addLast(9);
        assertThat(ad1.get(8) == 9).isTrue();
        assertThat(ad1.get(0) == 8).isTrue();
        assertThat(ad1.size() == 9).isTrue();
        assertThat(ad1.toList()).containsExactly(8, 1, 2, 3, 4, 5, 6, 7, 9, null, null, null, null, null, null, null).inOrder();
    }

    @Test
    public void getTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addLast(3);
        assertThat(ad1.get(0) == 1).isTrue();
        assertThat(ad1.get(7) == 2).isTrue();
        assertThat(ad1.get(1) == 3).isTrue();
    }

    @Test
    public void isEmptyTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        assertThat(ad1.isEmpty()).isTrue();
        ad1.addLast(1);
        ad1.addFirst(2);
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    public void toListTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        assertThat(ad1.toList()).containsExactly(1, null, null, null, null, null, 3, 2).inOrder();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addFirst(3);
        ad1.removeFirst();
        assertThat(ad1.size() == 2).isTrue();
        assertThat(ad1.get(7) == 2).isTrue();
        assertThat(ad1.toList()).containsExactly(1, null, null, null, null, null,null, 2).inOrder();
        ad1.addFirst(4);
        assertThat(ad1.get(6) == 4).isTrue();
        assertThat(ad1.toList()).containsExactly(1, null, null, null, null, null, 4, 2).inOrder();
        assertThat(ad1.removeFirst() == 4).isTrue();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> ad1 = new ArrayDeque61B<>();
        ad1.addFirst(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);
        assertThat(ad1.removeLast() == 4).isTrue();
        assertThat(ad1.size() == 3).isTrue();
        assertThat(ad1.toList()).containsExactly(1, 2, 3, null, null, null, null, null).inOrder();
        ad1.addFirst(5);
        ad1.addFirst(6);
        ad1.addFirst(7);
        ad1.addFirst(8);
        ad1.addFirst(9);
        ad1.addFirst(1);
        ad1.addFirst(2);
        ad1.addLast(3);
        ad1.addLast(4);
        ad1.removeLast();
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 9, 8, 7, 6, 5, 3, null, null, null, null, null, 2, 1).inOrder();
        ad1.removeFirst();
        assertThat(ad1.toList()).containsExactly(1, 2, 3, 9, 8, 7, 6, 5, 3, null, null, null, null, null, null, 1).inOrder();
    }
}
