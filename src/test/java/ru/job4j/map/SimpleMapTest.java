package ru.job4j.map;

import org.junit.Assert;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class SimpleMapTest {

    @Test
    public void whenPut() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        Assert.assertTrue(map.put("Ivan", 10));
    }

    @Test
    public void whenPutAndNotPlace() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Igor", 20);
        map.put("Oleg", 30);
        map.put("Maria", 40);
        map.put("Diana", 50);
        Assert.assertFalse(map.put("Ivan", 20));

    }

    @Test
    public void whenGet() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Mike", 30);
        Assert.assertEquals(Integer.valueOf(30), map.get("Mike"));
    }

    @Test
    public void whenGetNotExist() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Igor", 20);
        Assert.assertEquals(null, map.get("Oleg"));
    }

    @Test
    public void whenRemove() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Mike", 30);
        Assert.assertTrue(map.remove("Mike"));
    }

    @Test
    public void whenRemoveNotExist() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Igor", 20);
        Assert.assertFalse(map.remove("Oleg"));
    }

    @Test
    public void whenGetIteratorTwiceThenStartAlwaysFromBeginning() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Igor", 20);
        Assert.assertEquals("Ivan", map.iterator().next());
        Assert.assertEquals("Ivan", map.iterator().next());
    }

    @Test
    public void whenCheckIterator() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
      map.put("Ivan", 10);
        map.put("Igor", 20);
        map.put("Maria", 30);
        map.put("Elena", 40);
        map.put("Mike", 30);
        System.out.println(map);
        Iterator<String> iterator = map.iterator();
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("Mike", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("Igor", iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals("Ivan", iterator.next());
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddAfterGetIteratorThenMustBeException() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        map.put("Ivan", 10);
        map.put("Igor", 20);
        Iterator<String> iterator = map.iterator();
        map.put("Oleg", 30);
        iterator.next();
    }

}