package org.example.calc;

import java.util.*;

public class SomeCalc {
    public void countOccurrence(List<String> list, String target) {
        int count = 0;
        for (String str : list) {
            if (str.equals(target)) {
                count++;
            }
        }

        System.out.printf("%s: %d times\n", target, count);
    }

    public <T> List<T> toList(T... item) {
        return new ArrayList<>(List.of(item));
    }

    public <T> List<T> findUnique(List<T> list) {
        LinkedList<T> result = new LinkedList<>();
        for (T el : list) {
            if (!result.contains(el)) {
                result.add(el);
            }
        }
        return result;
    }

    public <T> Set<T> findUniqueWithSet(List<T> list) {
        return new HashSet<>(list);
    }

    public <T> Map<T, Integer> getOccurrence(List<T> list) {
        Map<T, Integer> result = new HashMap<>();
        for (T el: list){
            if(result.containsKey(el)){
                result.replace(el, result.get(el) +1);
            } else{
                result.put(el, 1);
            }
        }
        return result;
    }

    public <T> List<Item<T, Integer>> findOccurrence(List<T> list) {
        Map<T, Integer> occurrenceMap =  getOccurrence(list);
        List<Item<T, Integer>> result = new LinkedList<>();
        occurrenceMap.forEach((k,v) -> result.add(new Item<>(k,v)));

        return result;
    }
}
