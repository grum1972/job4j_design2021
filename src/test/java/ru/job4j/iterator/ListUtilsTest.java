package ru.job4j.iterator;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {
    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 0, 2);
        assertThat(input, Is.is(Arrays.asList(2, 1, 3)));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addAfter(input, 0, 2);
        assertThat(input, Is.is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.removeIf(input, x -> x <= 3);
        assertThat(input, Is.is(Arrays.asList(4, 5)));
    }

    @Test
    public void whenReplaceIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        ListUtils.replaceIf(input, x -> x < 3, 3);
        assertThat(input, Is.is(Arrays.asList(3, 3, 3, 3, 4, 5)));
    }

    @Test
    public void whenReplaceAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> filter = new ArrayList<>(Arrays.asList(2, 3));
        ListUtils.removeAll(input, filter);
        assertThat(input, Is.is(Arrays.asList(0, 1, 4, 5)));
    }

    @Test
    public void whenReplaceAllAndElementsEmpty() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5));
        List<Integer> filter = new ArrayList<>();
        ListUtils.removeAll(input, filter);
        assertThat(input, Is.is(Arrays.asList(0, 1, 2, 3, 4, 5)));
    }
}