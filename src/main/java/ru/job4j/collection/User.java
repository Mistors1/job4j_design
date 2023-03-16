package ru.job4j.collection;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User {
    private final String name;
    private final int children;
    private final Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        User user1 = new User("name", 1, birthday);
        User user2 = new User("name", 1, birthday);
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode1 >>> 16);
        int bucket2 = hash2 & 15;
        System.out.printf("user1 - hashCode: %s, hash: %s, bucket: %s  ", hashCode1, hash1, bucket1);
        System.out.printf("user2 - hashCode: %s, hash: %s, bucket: %s  ", hashCode2, hash2, bucket2);
        System.out.println(map.size());
    }

    @Override
    public boolean equals(Object o) {
        if
        (this == o) {
            return true;
        }
        if
        (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return children == user.children && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

   @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }
}
