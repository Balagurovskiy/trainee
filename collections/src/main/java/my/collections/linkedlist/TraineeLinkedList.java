package my.collections.linkedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class TraineeLinkedList<E> implements List<E>{
	
	private TraineeNode<E> first;
	private TraineeNode<E> last;
	private int size;
	
 
    public TraineeLinkedList() {
    }
 
    public TraineeLinkedList(Collection<? extends E> collection) {
    	addAll(collection);
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
		return (indexOf(o) >= 0);
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	/*
	 * put list data into object array
	 */
	@Override
	public Object[] toArray() {
		int i = 0;
		
		Object[] objects = new Object[size];
		
		TraineeNode<E> node = first;
		
		while(node != null) {
			objects[i++] = node.getData();
			node = node.getNext();
		}
		return objects;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	/*
	 *  Appends the specified element to the end of this list.
	 */
	@Override
	public boolean add(E data) {
		TraineeNode<E> node = new TraineeNode<>(last, data, null);
		if(Objects.isNull(last)) {
			first = node;
		} else {
			last.setNext(node);			
		}
		last = node;
		size++;
		return true;
	}
	/*
	 * Inserts the specified element at the specified position in this list.
	 */
	@Override
	public void add(int index, E data) {
		if (index == size || size == 0) {
			add(data);
		} else {
			TraineeNode<E> nodeToMove = getNode(index);
			TraineeNode<E> prevNode = nodeToMove.getPrev();
			TraineeNode<E> node = new TraineeNode<>(prevNode, data, nodeToMove);
			if(Objects.isNull(prevNode)) {
				first = node;
			} else {
				prevNode.setNext(node);
			}
			nodeToMove.setPrev(node);
			size++;
		}
	}
	
	private void extractNode(TraineeNode<E> node) {
		TraineeNode<E> prev = node.getPrev();
		TraineeNode<E> next = node.getNext();
		if (Objects.isNull(next)) {
			last = prev;
		} else {
			next.setPrev(prev);
		}
		if (Objects.isNull(prev)) {
			first = prev;
		} else {
			prev.setNext(next);
		}
		node.setData(null);
		node.setPrev(null);
		node.setNext(null);
		size--;
	}
	@Override
	public boolean remove(Object o) {
		TraineeNode<E> node = first;
		while(node != null) {
			if (node.getData().equals(o)) {
				extractNode(node);
				return true;
			}
			node = node.getNext();					
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> data) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> data) {
		addAll(size, data);
		return true;
	}
	/*
	 *  Inserts all of the elements in the specified collection into this
     * list, starting at the specified position.
     * 
     *  !!! add() contains getNode() that loops list from first every time
     *  !!! add() contains getNode() that already calls checkIndex()
	 */
	@Override
	public boolean addAll(int index, Collection<? extends E> data) {
		checkIndex(index);
		if (Objects.isNull(data)) {
    		throw new NullPointerException();
    	}
		Object[] objects = data.toArray();
		for (int i = 0; i < objects.length; i++) {
			add(index + i, (E) objects[i]);
		}
		return true;
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
		TraineeNode<E> node = first;
		while(node != null) {
			TraineeNode<E> next = node.getNext();
			node.setData(null);
			node.setPrev(null);
			node.setNext(null);
			node = next;
		}
        first = last = null;
        size = 0;
	}

	private boolean checkIndex(int index) {
		if(index >= 0 && index <= size) {
			return true;
		}
		throw new IndexOutOfBoundsException();
	}
	private TraineeNode<E> getNode(int index) {
		checkIndex(index);
		TraineeNode<E> node = first;
		int currentIndex = 0;
		while(currentIndex != index) {
			node = node.getNext();					
			currentIndex++;
		}
		return node;
	}
	@Override
	public E get(int index) {
		if(size > 0) {
			return (getNode(index).getData());
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public E set(int index, E data) {
		if(size > 0) {
			TraineeNode<E> node = getNode(index);
			E oldData = node.getData();
			node.setData(data);
			return (oldData);
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public E remove(int index) {
		if(size > 0) {
			TraineeNode<E> node = getNode(index);
			E reomovedData = node.getData();
			extractNode(node);
			return (reomovedData);
		}
		throw new IndexOutOfBoundsException();
	}

	@Override
	public int indexOf(Object o) {
		TraineeNode<E> node = first;
		int index = 0;
		while(node != null) {
			E data = node.getData();
			boolean eqNull = Objects.isNull(data) && data == null;
			boolean eqNotNUll = !eqNull && data.equals(o); 
			if(eqNull || eqNotNUll) {
				return index;
			}
			index++;
			node = node.getNext();					
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		TraineeNode<E> node = first;
		int index = size;
		while(node != null) {
			E data = node.getData();
			boolean eqNull = Objects.isNull(data) && data == null;
			boolean eqNotNUll = !eqNull && data.equals(o); 
			if(eqNull || eqNotNUll) {
				return index;
			}
			index--;
			node = node.getNext();					
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
