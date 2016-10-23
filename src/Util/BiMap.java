package Util;

import java.util.HashMap;
import java.util.Set;

public class BiMap<K,V> {
    HashMap<K, V> map = new HashMap<>();
    HashMap<V,K> invMap = new HashMap<>();

    public void put(K key, V val){
        map.put(key,val);
        invMap.put(val,key);
    }

    public void putValue(V val, K key){
        map.put(key,val);
        invMap.put(val,key);
    }
    public V get(K key){
        return map.get(key);
    }
    public K getValue(V val){
        return invMap.get(val);
    }
    public int size(){return map.size();}
    public Set<K> keySet(){
        return map.keySet();
    }
    public Set<V> values(){
        return invMap.keySet();
    }
}
