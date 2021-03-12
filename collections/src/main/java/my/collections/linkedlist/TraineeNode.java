package my.collections.linkedlist;


public class TraineeNode<E> {
    private E data;
    private TraineeNode<E> next;
    private TraineeNode<E> prev;

    TraineeNode(TraineeNode<E> prev, E data, TraineeNode<E> next) {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public TraineeNode<E> getNext() {
		return next;
	}

	public void setNext(TraineeNode<E> next) {
		this.next = next;
	}

	public TraineeNode<E> getPrev() {
		return prev;
	}

	public void setPrev(TraineeNode<E> prev) {
		this.prev = prev;
	}
    
    
}
