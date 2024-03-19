package oy.tol.tra;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;


public class Algorithms {

    public static <T extends Comparable<T>> void fastSort(T[] array){
        if(array == null || array.length == 0){
            return;
        }
        fastSort(array, 0, array.length - 1);
    }
    private static <T extends Comparable<T>> void fastSort(T[] array, int start, int end){
        if(start < end){
            int pivotIndex = partition(array, start, end);
            fastSort(array, start, pivotIndex - 1);
            fastSort(array, pivotIndex + 1, end);
         }
    }
    private static <T extends Comparable<T>> int partition(T[] array, int start, int end){
        T pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                swap(array, i, j);
            }
        } 
        swap(array, i + 1, end);
        return i + 1;
    }
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    public static <T> int partitionByRule(T[] array, int count, Predicate<T> rule) {
        // Find first element rules applies to.
        // Index of that element will be in variable index.
        int index = 0;
        for (; index < count; index++) {
            if (rule.test(array[index])) {
                break;
            }
        }
        // If went to the end, nothing was selected so quit here.
        if (index >= count) {
            return count;
        }
        // Then start finding not selected elements starting from next from index.
        // If the element is not selected, swap it with the selected one.
        int nextIndex = index + 1;
        // Until end of array reached.
        while (nextIndex != count) {
            if (!rule.test(array[nextIndex])) {
                swap(array, index, nextIndex);
                // If swapping was done, add to index since now it has non-selected element.
                index++;
            }
            nextIndex++;
        }
        return index;
    }
    public static <T> void sortWithComparator(T[] array, Comparator<T> comparator) {
        fastSortWithComparator(array, comparator, 0, array.length - 1);
    }

    private static <T> void fastSortWithComparator(T[] array,  Comparator<T> comparator, int start, int end) {

        if(start < end){
            int pivotIndex = partitionWithComparator(array, comparator, start, end);
            fastSortWithComparator(array, comparator, start, pivotIndex - 1);
            fastSortWithComparator(array, comparator, pivotIndex + 1, end);
        }
    }

    private static <T> int partitionWithComparator(T[] array, Comparator<T> comparator, int start, int end) {
        T pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(comparator.compare(array[j], pivot) <= 0){
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, end);
        return i + 1;
    }


}
