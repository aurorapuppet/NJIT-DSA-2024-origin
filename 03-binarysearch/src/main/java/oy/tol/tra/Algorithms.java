package oy.tol.tra;

public class Algorithms {
    private Algorithms() {
        // Empty
    }
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public static <T> void reverse(T[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            T temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
    public static int binarySearch(int target, Integer[] array, int fromIndex, int toIndex) {
        int left = fromIndex;
        int right = toIndex;
        while (left <= right) {
            int mid = (right + left) / 2;
            if(array[mid] == target){
                return mid;
            }else if(array[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }

        }

        return -1;
    }
    public static int binarySearch(String value, String[] array, int fromIndex, int toIndex) {
        int left = fromIndex;
        int right = toIndex;

        while (left <= right) {
            int mid = (right + left) / 2;
            int cmp = value.compareTo(array[mid]);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


    public static <T extends Comparable<T>> void fastSort(Integer[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(Integer[] array, int start, int end){
        if(start < end){
            int pivotIndex = partition(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(Integer[] array, int start, int end){
        int pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(array[j] < pivot){
                i++;
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        int tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }



    public static <T extends Comparable<T>> void fastSort(String[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static <T extends Comparable<T>> void quickSort(String[] array, int start, int end){
        if(start < end){
            int pivotIndex = partition(array, start, end);
            quickSort(array, start, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, end);
        }
    }

    private static <T extends Comparable<T>> int partition(String[] array, int start, int end){
        String pivot = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++){
            if(array[j].compareTo(pivot) < 0){
                i++;
                String tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }
        String tmp = array[i + 1];
        array[i + 1] = array[end];
        array[end] = tmp;
        return i + 1;
    }
}
