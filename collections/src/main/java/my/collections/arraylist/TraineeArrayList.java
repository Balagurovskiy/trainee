package my.collections.arraylist;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class TraineeArrayList<E> implements List<E>{

	private Object data[];
	private int size;

    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;
    public TraineeArrayList() {
    	data = new Object[DEFAULT_CAPACITY];
	}
    public TraineeArrayList(int initialCapacity) {
    	if (initialCapacity >= 0) {
    		data = new Object[initialCapacity];
    	} else {
    		 throw new IllegalArgumentException();
    	}
    }
    public TraineeArrayList(Collection<? extends E> c) {
    	Object[] original = c.toArray();
		size = original.length;
    	data = Arrays.copyOf(original, original.length);
    }
	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) >= 0;
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(data, size);
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	private void expand(int newSize) {
		data = Arrays.copyOf(data, newSize);
	}
	/*
     * Appends the specified element to the end of this list.
     * !!! integer.max overflow not handled
     */
	@Override
	public boolean add(E e) {
		if ((size) >= data.length) {
			expand(size + (size >> 1));
		}
		data[size] = e;
		size++;
		return true;
	}

	@Override
	public boolean remove(Object o) {
		int index;
		if ((index = indexOf(o)) >= 0) {
			remove(index);
			return true;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		for (int i = 0; i < data.length; i++) {
			data[i] = null;
		}
		size = 0;		
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		Objects.checkIndex(index, size);
		return (E) data[index];
	}

	@Override
	public E set(int index, E element) {
		E oldData = get(index);
		this.data[index] = element;
		return oldData;
	}

	@Override
	public void add(int index, E e) {
		if ((size) >= data.length) {
			expand(size + (size >> 1));
		}
        System.arraycopy(
        				data, index,
        				data, index + 1,
			            size - index
			            );
		data[index] = e;
		size++;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		Objects.checkIndex(index, size);
		size--;
		E oldValue = (E) data[index];
		System.arraycopy(
				data, index + 1,
				data, index,
				size - index
	            );
		data[size] = null;
		return oldValue;
	}

	@Override
	public int indexOf(Object o) {
		for (int i = 0; i < data.length; i++) {
			boolean eqNull = Objects.isNull(o) && Objects.isNull(data[i]);
			boolean eqNotNUll = !Objects.isNull(data[i]) && data[i].equals(o); 
			if(eqNull || eqNotNUll) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		for (int i = data.length - 1; i >= 0; --i) {
			boolean eqNull = Objects.isNull(o) && Objects.isNull(data[i]);
			boolean eqNotNUll = !Objects.isNull(data[i]) && data[i].equals(o); 
			if(eqNull || eqNotNUll) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}
	
}
