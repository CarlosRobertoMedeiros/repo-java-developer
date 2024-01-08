package br.com.roberto.advancedtechniquesjava8.c.collectionsandgenerics.a;

public class CommonMap {
    /*
    * Map - maps keys to values; keys are unique; each key can
    * map to at most one value.
    *
    *HashMap
    *   - unsorted, unordered Map
    *   - uses the hashCode of the object being inserted; the
    * more efficient your hashCode() implementation, the better access performance you will get
    *   - use this class when you want to Map and you don't care about order when you
    * iterate through it.
    *   - allow one null key and multiple null values
    *
    * Map - (unique) keys maps to values; each key can map to at most one value
    *   LinkedHashMap
    *      - maintains insertion order
    *   TreeMap
    *      - a sorted Map; sorted by natural order of it's keys or
    *   by a custom order (via comparator)
    *   HashTable
    *      - similar to HashMap except HashTable is thread-safe (slower) and nulls are not allowed
    *
    * Popular Methods
    *
    *  void                   clear()                                         removes all keys and values from the map
    *  boolean                containsKey(Object key)                         is the key in the map
    *  boolean                containsValue(Object value)                     is the value in the map
    *  Set<Map.Entry<K,V>>    entrySet()                                      returns a Set view of the key/values pairs
    *  void                   forEach(BiConsumer(key,value))                  perform the given BiConsumer on each entry in the map                                                             elements of the given collection
    *  V                      get(Object key)                                 returns the value for the specified key or
    *                                                                         null if no mapping exists
    *  boolean                isEmpty()                                       is the map entry
    *
    * */

}
