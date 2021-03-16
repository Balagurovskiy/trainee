package my.sort;

public interface Sortable {

	default <T extends Comparable<T>> void swap(T[] src, int a, int b) {
		T tmp = src[a];
		src[a] = src[b];
		src[b] = tmp;
	}
	
	default <T extends Comparable<T>> boolean compareAsc(T a, T b) {
		return a.compareTo(b) > 0;
	}
	default <T extends Comparable<T>> boolean compareDesc(T a, T b) {
		return a.compareTo(b) < 0;
	}
	public <T extends Comparable<T>> T[] sort(T[] input);
}
