package oy.tol.tira.books;

import java.util.Objects;

/**
 * Class Pair defines a key-value -pair of objects where each key has an
 * associated value.
 * 
 * Keys must implement the Comparable interface. Equality is determined by
 * comparing keys.
 */
public class Pair<K,V extends Comparable<V>> implements Comparable<Pair<K, V>> {
   private K key;
   private V value;

   public Pair(K key, V value) {
      this.key = key;
      this.value = value;
   }

   public K getKey() {
      return key;
   }

   public void setKey(K key) {
      this.key = key;
   }

   public V getValue() {
      return value;
   }

   public void setValue(V value) {
      this.value = value;
   }

   @Override
   public int compareTo(Pair<K, V> o) {
      return this.getValue().compareTo(o.getValue());
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Pair<?, ?> other = (Pair<?, ?>) obj;
      if (!Objects.equals(key, other.key)) return false;
      return Objects.equals(value, other.value);
   }
   @Override
   public int hashCode() {
      int result = key != null ? key.hashCode() : 0;
      result = 31 * result + (value != null ? value.hashCode() : 0);
      return result;
   }

   @Override
   public String toString() {
      return "Pair{" + "key=" + key + ", value=" + value + '}';
   }
}
