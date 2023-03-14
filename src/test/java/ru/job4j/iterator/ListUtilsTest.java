package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {

    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        ListUtils.addBefore(input, 2, 3);
        assertThat(input).hasSize(4).containsSequence(1, 2, 3, 3);
    }

    @Test
    void whenAddBeforeWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addBefore(input, 3, 2))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        assertThat(input).hasSize(5).containsSequence(1, 2, 3, 4, 5);
    }

    @Test
    void whenAddAfterWithInvalidIndex() {
        assertThatThrownBy(() -> ListUtils.addAfter(input, 3, 4))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

    @Test
    void whenRemoveIfNotContains() {
        ListUtils.removeIf(input, s -> input.contains(5));
        assertThat(input).hasSize(2).containsSequence(1, 3);
    }

    @Test
    void whenRemoveIfEquals() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.removeIf(input, s -> s.equals(3));
        assertThat(input).hasSize(3).containsSequence(1, 2, 4);
    }

    @Test
    void whenRemoveIfContains() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.removeIf(input, input::contains);
        assertThat(input).isEmpty();
    }

    @Test
    void whenReplaceIfEquals() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.replaceIf(input, s -> s.equals(3), 8);
        assertThat(input).hasSize(4).containsSequence(1, 2, 8, 4);
    }

    @Test
    void whenReplaceIfAll() {
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.replaceIf(input, input::contains, 8);
        assertThat(input).hasSize(4).containsSequence(8, 8, 8, 8);
    }

    @Test
    void whenRemoveAll() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3, 5));
        ListUtils.removeAll(input, list);
        assertThat(input).isEmpty();
    }

    @Test
    void whenRemoveAll1And3() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(3).containsSequence(2, 4, 5);
    }

    @Test
    void whenRemoveAll123() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        ListUtils.addAfter(input, 0, 2);
        ListUtils.addAfter(input, 2, 4);
        ListUtils.addAfter(input, 3, 5);
        ListUtils.removeAll(input, list);
        assertThat(input).hasSize(2).containsSequence(4, 5);
    }
}