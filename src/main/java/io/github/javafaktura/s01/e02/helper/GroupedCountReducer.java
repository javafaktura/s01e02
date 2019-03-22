package io.github.javafaktura.s01.e02.helper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Adam Król 2019-03-21
 */
public class GroupedCountReducer<T> {

    private Map<T, Integer> countingMap = new HashMap<>();

    public Map<T, Integer> getCountingMap() {
        return countingMap;
    }

    public GroupedCountReducer<T> add(T key) {
        countingMap.put(key, countingMap.getOrDefault(key, 0) + 1);
        return this;
    }

    public GroupedCountReducer<T> add(GroupedCountReducer<T> another) {
        another.countingMap.forEach((key, value) -> {
            countingMap.put(key, countingMap.getOrDefault(key, 0) + value);
        });
        return this;
    }
}
