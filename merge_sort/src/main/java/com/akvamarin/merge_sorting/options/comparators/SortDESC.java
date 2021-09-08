package com.akvamarin.merge_sorting.options.comparators;
import java.io.BufferedReader;
import java.util.Comparator;
import java.util.Map;

public class SortDESC<T extends Comparable<T>>  implements  Comparator<Map.Entry<T, BufferedReader>>{
    @Override
    public int compare(Map.Entry<T, BufferedReader> obj1, Map.Entry<T, BufferedReader> obj2) {
        return -obj1.getKey().compareTo(obj2.getKey());
    }
}
