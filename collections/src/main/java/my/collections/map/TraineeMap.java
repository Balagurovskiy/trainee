package my.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
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
		return !Objects.isNull(get(key));
	}
	/*
	 * Returns {@code true} if this map maps one or more keys to the
     * specified value.
	 */
	@Override
	public boolean containsValue(Object value) {
		for (int i = 0; i < capacity; i++) {
			TraineeEntry<K, V> bucket = this.buckets[ i ];
			while(bucket != null) {
				if (Objects.isNull(bucket.getValue())) {
					if(Objects.isNull(value)) {
						return true;
					}
				}else if (bucket.getValue().equals(value)) {
					return true;
				}
	    		bucket = bucket.getNext();
	    	}
		}
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
	
	/*
	 * Create hash from key Object
	 */
	private int getHash(Object key) {
		int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}
	
	/*
	 * Calculate bucket id 
	 */
	private int getBucketIndex(int hash) {
		return hash % capacity;
	}
	
	/*
	 * Resize when reaching the threshold
	 */
	private void resize() {
		if ((size + 1) >= (capacity * LOAD_FACTOR)){
			int oldCapasity = capacity;
			capacity *= 2;
			TraineeEntry<K,V>[] resizedBuckets = new TraineeEntry[capacity];

			for (int i = 0; i < oldCapasity; i++) {
				TraineeEntry<K, V> bucket = this.buckets[ i ];
				while(bucket != null) {
					put(resizedBuckets, bucket.getKey(), bucket.getValue(), false);
		    		bucket = bucket.getNext();
		    	}
			}
			this.buckets = resizedBuckets;
		}
	}
	/*
	 * create new bucket or add to existing as linked list.
	 * @param isNewEntry switch between new entry and rehashing old entries
	 */
	private V put(TraineeEntry<K,V>[] b, K key, V value, boolean isNewEntry) {
		TraineeEntry<K, V> newEntry = new TraineeEntry<K, V>(key, value, null);
		
		int index = getBucketIndex( getHash(newEntry.getKey()) );

		TraineeEntry<K, V> bucket = b[index];
        
        if (bucket == null) {
            b[index] = newEntry;
            if (isNewEntry) {
            	size++;
            }
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
    			if (isNewEntry) {
                	size++;
                }
    		}
        }
		return null;
	}
	@Override
	public V put(K key, V value) {
		resize();
		return put(this.buckets , key, value, true);
	}

	@Override
	public V remove(Object key) {
		int index = getBucketIndex( getHash(key) );
		
		TraineeEntry<K, V> bucket = buckets[index];
		
		if ( Objects.isNull(bucket) ){
			return null;
		}
		if(bucket.getKey().equals(key)) {
			V value = bucket.getValue();
			if ( Objects.isNull(bucket.getNext()) ){
				buckets[index] = null;
			} else {
				buckets[index] = bucket.getNext();
			}
			size--;
			return value;
		} else {
			while(bucket.getNext() != null) {
	    		if(bucket.getNext().getKey().equals(key)) {
	    			V value = bucket.getValue();
	    			bucket.setNext(bucket.getNext().getNext());
	    			size--;
	    			return value;
	    		}
	    		bucket = bucket.getNext();
    		}
		}

		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		for (int i = 0; i < buckets.length; i++) {
			TraineeEntry<K, V> bucket = this.buckets[i];
			while(bucket != null) {
				buckets[i] = null;
	    		bucket = bucket.getNext();
	    	}
			
		}
		size = 0;
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
