package my.collections.map;

import java.util.Map;
import java.util.Objects;

public class TraineeEntry<K,V> implements Map.Entry<K,V> {
	
    private final K key;
    private V value;
    private TraineeEntry<K,V> next;
    
    public TraineeEntry(K key, V value, TraineeEntry<K,V> next) {
        this.key = key;
        this.value = value;
        this.next = next;
    }
    
	@Override
	public K getKey() {
		return key;
	}

	@Override
	public V getValue() {
		return value;
	}

	@Override
	public V setValue(V value) {
		V old = this.value;
		this.value = value;
		return old;
	}

	public TraineeEntry<K, V> getNext() {
		return next;
	}

	public void setNext(TraineeEntry<K, V> next) {
		this.next = next;
	} 
}
