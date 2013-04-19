package net.spanbroek.parsing.util;

import java.util.*;

public class MultiMap<K,V> extends HashMap<K,List<V>> {

    @Override
    @SuppressWarnings("unchecked")
    public List<V> get(Object key) {
        if (!containsKey(key)) {
            put((K)key, new ArrayList<V>());
        }
        return super.get(key);
    }

    public void add(K key, V value) {
        get(key).add(value);
    }
}
