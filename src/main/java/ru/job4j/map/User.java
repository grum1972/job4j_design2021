package ru.job4j.map;

import java.util.*;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User user1 = new User("Ivan", 5, new GregorianCalendar(2022, 2, 9));
        User user2 = new User("Ivan", 5, new GregorianCalendar(2022, 2, 9));

        HashMap<User, Object> clients = new HashMap<>();
        clients.put(user1, new Object());
    }
}
