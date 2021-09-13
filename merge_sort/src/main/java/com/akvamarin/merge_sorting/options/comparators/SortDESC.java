package com.akvamarin.merge_sorting.options.comparators;
import com.akvamarin.merge_sorting.merge.ReaderWithFile;
import java.util.Comparator;
import java.util.Map;

public class SortDESC<T extends Comparable<T>>  implements  Comparator<Map.Entry<T, ReaderWithFile>>{
    @Override
    public int compare(Map.Entry<T, ReaderWithFile> obj1, Map.Entry<T, ReaderWithFile> obj2) {
        return -obj1.getKey().compareTo(obj2.getKey());
    }
}
