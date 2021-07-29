package aed.hashtable;

import aed.linkedlist.LinkedList;


public class HashTable<K, V> {
  
  private final int SIZE_HASH_TABLE_ARRAY = 101;
  private LinkedList[] table =  new LinkedList[SIZE_HASH_TABLE_ARRAY];

  public HashTable() {}

	public V put(K key, V value) {

    var index = calculateIndex(key);
    Entry<K, V> entry = new Entry<K, V>(key, value);

    if(table[index] == null) {
      table[index] = new LinkedList<Entry<K, V>>();
    }

    table[index].add(entry);
    
    return value;
  }

  public boolean containsKey(Object key) {
  
    var list = collection(calculateIndex(key));

    if(list == null) return false;

    for(var entry : list) {
      if(key.equals(entry.getKey())) {
        return true;
      }
    }
    
    return false;
  }

  public V get(K key) {
    
    var list = collection(calculateIndex(key));

    if(list == null) return null;

    for(var entry : list) {
        if(key.equals(entry.getKey())) {
        return (V) entry.getValue();
      }
    }

    return null;
  }

  public V remove(Object key) {

    var list = collection(calculateIndex(key));

    if(list == null) return null;

    for(var i = 0; i < list.length(); i++) {
    
      var entry = (Entry<K, V>) list.get(i);

      if(key.equals(entry.getKey())) {
        list.remove(i);
        return (V) entry.getValue();
      }
    }

    return null;
  }

  public V replace(K key, V value) {
    
    var list = collection(calculateIndex(key));

    if(list == null) return null;

    for(var entry : list) {
      if(key.equals(entry.getKey())) {
        entry.setValue(value);
        return (V) entry.getValue();
      }
    }

    return null;
  }

  public int hash(Object key) {
    
    final double a = 0.6180339887;
    final int h = 64;
    int k = key.hashCode();

    return (int)(h * (k * a % 1));
  }

  private int calculateIndex(Object key) {
    return (int)(Math.sqrt(Math.pow(hash(key), 2))) % SIZE_HASH_TABLE_ARRAY;
  }

  private LinkedList<Entry<K, V>> collection(int index) {
    return (LinkedList<Entry<K, V>>)table[index];
  }


  @Override
  public String toString() {
    var template = "{";
    for(int i = 0; i < SIZE_HASH_TABLE_ARRAY; i++) {
      
      if(table[i] == null) continue;

      for(int j = 0; j < table[i].length(); j++) {
        template += table[i].get(j);
        template += ", ";
      }      
    }
    
    template += "}";

    return template;
  }

  private class Entry<K, V> {
    
    private K key;
    private V value;
    
    public Entry(){}
  
    public Entry(K key, V value) {
      this.key = key;
      this.value = value;
    }
  
    public K getKey() {
      return key;
    }
  
    public V getValue() {
      return value;
    }

    public void setValue(V value) {
      this.value = value;
    }
  
    @Override
    public String toString() {
      return key + ":" + value;
    }
  }
}
