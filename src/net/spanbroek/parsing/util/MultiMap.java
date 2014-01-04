package net.spanbroek.parsing.util;

import java.util.*;

public class MultiMap<K,V> extends HashMap<K,Set<V>> {

    @Override
    @SuppressWarnings("unchecked")
    public Set<V> get(Object key) {
        if (!containsKey(key)) {
            put((K)key, new LinkedHashSet<V>());
        }
        return super.get(key);
    }

    public void add(K key, V value) {
        get(key).add(value);
    }
}
