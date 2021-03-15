package my.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class TraineeMap<K, V> implements Map<K, V>{

	private int capacity = 16;
	private final float LOAD_FACTOR = 0.75f;
	
	private TraineeEntry<K,V>[] buckets;
	private int size;
	
	public TraineeMap() {
		buckets = new TraineeEntry[capacity];
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	/*
	 * Returns {@code true} if this map contains a mapping for the specified
     * key.
	 */
	@Override
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	 * Returns {@code true} if this map maps one or more keys to the
     * specified value.
	 */
	@Override
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V get(Object key) {
		int index = getBucketIndex( getHash(key) );
		
		TraineeEntry<K, V> bucket = buckets[index];
		while(bucket != null) {
    		if(bucket.getKey().equals(key)) {
    			return bucket.getValue();
    		}
    		bucket = bucket.getNext();
    	}
		return null;
	}
	private int getHash(Object key) {
		int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
	private int getBucketIndex(int hash) {
		return hash % capacity;
	}
	private void resize() {
		if ((size + 1) >= (capacity * LOAD_FACTOR)){
        	//create new buckets x 2
        	//put etries to new bucket
        	//clear old
        	//redefine field to new
        }
	}
	@Override
	public V put(K key, V value) {
		TraineeEntry<K, V> newEntry = new TraineeEntry<K, V>(key, value, null);
		int index = getBucketIndex( getHash(newEntry.getKey()) );

		TraineeEntry<K, V> bucket = buckets[index];
        
        if (bucket == null) {
            buckets[index] = newEntry;
            size++;
        } else {
        	while(bucket.getNext() != null) {
        		if(bucket.getKey().equals(key)) {
        			V oldValue = bucket.getValue();
        			bucket.setValue(value);
        			return oldValue;
        		}
        		bucket = bucket.getNext();
        	}
        	if(bucket.getKey().equals(key)) {
    			V oldValue = bucket.getValue();
    			bucket.setValue(value);
    			return oldValue;
    		} else {
    			bucket.setNext(newEntry);
			 	size++;
    		}
        }
		return null;
	}

	@Override
	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
	}
	@Override
	public Set<K> keySet() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}
}
