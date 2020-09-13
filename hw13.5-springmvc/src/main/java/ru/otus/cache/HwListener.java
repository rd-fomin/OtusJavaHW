package ru.otus.cache;

/**
 * @author Roman
 */
public interface HwListener<K, V> {
  void notify(K key, V value, String action);
}
