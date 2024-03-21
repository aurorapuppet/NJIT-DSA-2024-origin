package oy.tol.tra;

public class Algorithms {


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
    }


