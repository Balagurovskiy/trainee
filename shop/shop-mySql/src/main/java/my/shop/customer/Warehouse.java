package my.shop.customer;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import my.shop.products.CurrentDataStamp;
import my.shop.products.Expirable;
import my.shop.products.Product;

public class Warehouse implements Serializable{

	private static final long serialVersionUID = 8895041395297386105L;
	private Bucket bucket;
	
	public Warehouse() {
		bucket = new Bucket();
	}
	
	public Warehouse(Bucket bucket) {
		this.bucket = bucket;
	}
	
	public void store(Product product) {
		if(product instanceof Expirable) {
			List<Field> annotatedField = Arrays.asList(product.getClass().getDeclaredFields())
					.stream()
					.filter(a-> Objects.nonNull(a.getAnnotation(CurrentDataStamp.class)))
					.collect(Collectors.toList());
			for (Field field : annotatedField) {
				field.setAccessible(true);
				try {
					field.set(product, LocalDate.now());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		this.bucket.put(product);
	}
	
	public void remove(String productName) {
		Optional<Product> toDelProduct = bucket.getStash().keySet().stream()
														.filter(prod->prod.getName().equals(productName))
														.findAny();
		if(toDelProduct.isPresent()) {
			bucket.poll(toDelProduct.get());
		}
	}
	
	public void clear() {
		bucket.getStash().clear();
	}
	
	public boolean isEmpty() {
		return bucket.getStash().isEmpty();
	}
	
	public Map<Product, Integer> copy(){
		return new HashMap<Product, Integer>(bucket.getStash());
	}
	
	public String stats() {
		StringBuilder builder = new StringBuilder();
		bucket.getStash().forEach((prod, count)->{
			builder.append(" > ");
			builder.append(prod.getName());
			builder.append(" ");
			builder.append(prod.price().getAmount());
			builder.append(" ");
			builder.append(prod.price().getCourse().getName());
			builder.append(" x (");
			builder.append(count);
			builder.append(") \n");
		});
		return builder.toString();
	}
 
}
