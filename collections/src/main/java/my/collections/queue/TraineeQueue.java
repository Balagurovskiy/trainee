package my.collections.queue;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import my.collections.linkedlist.TraineeLinkedList;

public class TraineeQueue<E> implements Queue<E> {
	
	private TraineeLinkedList<E> storage;
	
	public TraineeQueue() {
		storage = new TraineeLinkedList<>();
	}	
	
	@Override
	public int size() {
		return storage.size();
	}

	@Override
	public boolean isEmpty() {
		return storage.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return storage.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		return storage.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public boolean remove(Object o) {
		return storage.remove(o);
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
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		storage.clear();
	}

	@Override
	public boolean add(E e) {
		return storage.add(e);
	}

	@Override
	public boolean offer(E e) {
		throw new UnsupportedOperationException();
	}
	/*
	 * Retrieves and removes the head of this queue.  This method differs
     * from {@link #poll() poll()} only in that it throws an exception if
     * this queue is empty.
     */
	@Override
	public E remove() {
		if (storage.isEmpty()) {
			throw new NoSuchElementException();
		}
		return storage.remove(0);
	}
	/*
	 * Retrieves and removes the head of this queue,
     * or returns {@code null} if this queue is empty.
     */
	@Override
	public E poll() {
		return storage.remove(0);
	}
	/*
	 * Retrieves, but does not remove, the head of this queue.This method
     * differs from {@link #peek peek} only in that it throws an exception
     * if this queue is empty.
	 */
	@Override
	public E element() {		
		if (storage.isEmpty()) {
			throw new NoSuchElementException();
		}
		return storage.get(0);
	}
	/*
	 * Retrieves, but does not remove, the head of this queue,
     * or returns {@code null} if this queue is empty.
	 */
	@Override
	public E peek() {
		return storage.get(0);
	}
	 
}
