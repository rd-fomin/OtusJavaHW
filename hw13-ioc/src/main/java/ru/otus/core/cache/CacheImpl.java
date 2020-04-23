package ru.otus.core.cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author Roman
 */
@Component
public class CacheImpl<K, V> implements Cache<K, V> {
    //Надо реализовать эти методы
    private final WeakHashMap<K, V> cache = new WeakHashMap<>();
    private final List<CacheListener<K, V>> listeners = new ArrayList<>();

    @Override
    public void put(K key, V value) {
        if (!cache.containsKey(key)) {
            cache.put(key, value);
            listeners.forEach(listener -> listener.notify(key, value, "put"));
        }
    }

    @Override
    public void remove(K key) {
        V value = cache.remove(key);
        if (value != null) {
            listeners.forEach(listener -> listener.notify(key, value, "remove"));
        }
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void addListener(CacheListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(CacheListener<K, V> listener) {
        listeners.remove(listener);
    }
}
