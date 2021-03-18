package my.streams.testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

import my.streams.Employee;

public class AppTest {

	private static List<Employee> group = Arrays.asList(
			new Employee("Stefan", 100.500f),
			new Employee("Barabara", 300.00f),
			new Employee("Banana", 777.777f),
			new Employee("Zef", 123.456f),
			new Employee("Genadiy", 66.11f),
			new Employee("Poligraf", 321.00f),
			new Employee("Alf", 421.00f),
			new Employee("Raf", 765.00f),
			new Employee("Gendalf", 876.00f),
			new Employee("Bob", 222.00f),
			new Employee("Hop", 567.00f),
			new Employee("Gop", 125.00f)
		);

		/*
		 * Have a sorted collection, you should display 
		 * the data multiples of 2m using a map increase 
		 * the value and display a new value.
		 */
		@Test
		public void task1() {
			System.out.println("\n * Task 1 * ");
			Predicate<Float> isMod2 = (v)->v % 2 == 0;
			Comparator<Employee> comparator = Comparator.comparing(Employee::getName).thenComparing(Employee::getSalary);
			group.stream()
					.sorted(comparator)
					.filter(e -> isMod2.test(e.getSalary()))
					.peek(System.out::println)
					.map(e -> {
						e.setSalary(e.getSalary() * 2);
						return e.getSalary();
					})
					.peek(s -> System.out.println("\tincreased salary : " + s))
					.collect(Collectors.toList());
		}
		/*
		 * execute the parallel process and increase the 
		 * value, then compare the foreach outcome and 
		 * foreachOrdered.
		 */
		@Test
		public void task2_forEach() {
			System.out.println("\n * Task 2 task2_forEach (have to be unordered in parallel) * ");
			group.stream()
					.parallel()
					.forEach(e -> {
						e.setSalary(e.getSalary() * 2);
						System.out.println(e);
					});
		}
		@Test
		public void task2_forEachOrdered() {
			System.out.println("\n * Task 2 task2_forEachOrdered (have to be ordered in parallel) * ");
			group.stream()
					.parallel()
					.forEachOrdered(e -> {
						e.setSalary(e.getSalary() * 2);
						System.out.println(e);
					});
		}
		public static<T> void addToList(List<T> target, Stream<T> source)
	    {
	        source.collect(Collectors.toCollection(() -> target));
	    }
		/*
		 * We have some collection with data for example 1, 2, 3. 
		 * Take its stream(), then add the new values for example 4, 5, 6 
		 * and then, what will be the result of system.out.println from 
		 * the stream values.
		 */
		@Test
		public void task3() {
			System.out.println("\n * Task 3  * ");
			 List<Integer> source = new ArrayList<>(Arrays.asList(1, 2, 3));
			 List<Integer> target = new ArrayList<>(Arrays.asList(4, 5, 6));
			 Stream<Integer> sourceStream = source.stream();
			 target.forEach(source::add);
			 sourceStream.forEach(System.out::println);
		}
}
