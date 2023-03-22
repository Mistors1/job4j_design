package ru.job4j.question;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analyze {


    private Analyze() {
    }

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        Map<Integer, User> map = new HashMap<>();
        for (User user : previous) {
            map.put(user.getId(), user);
        }

        for (User user : current) {
            User prevUser = map.putIfAbsent(user.getId(), user);
            if (prevUser != null) {
                if (!user.equals(prevUser)) {
                    changed++;
                }
            } else {
                added++;
            }
        }
        int deleted = previous.size() - current.size() + added;
        return new Info(added, changed, deleted);
    }

}