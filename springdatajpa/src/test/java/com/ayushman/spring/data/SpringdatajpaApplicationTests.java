package com.ayushman.spring.data;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.ayushman.spring.data.entity.Product;
import com.ayushman.spring.data.repo.ProductRepository;

@SpringBootTest
class SpringdatajpaApplicationTests {

	@Autowired
	ApplicationContext applicationContext;
	
	@Test
	void saveProduct() {
		
		ProductRepository repository = applicationContext.getBean(ProductRepository.class);
		/*
		 * Product product = new Product(); product.setId(5L);
		 * product.setName("Macbook Air");
		 * product.setDescription("Entry level Macbook"); product.setPrice(900.0);
		 * repository.save(product);
		 */
		/*
		 * Optional<Product> result = repository.findById(1L); if(result.isPresent()) {
		 * Product product = result.get(); System.out.println(product);
		 * 
		 * product.setPrice(800.0); repository.save(product);
		 * 
		 * }
		 * 
		 * repository.findAll().forEach(p -> System.out.println(p));
		 */
		
		
		/*
		 * List<Product> names = repository.findByName("iPhone");
		 * System.out.println(names);
		 */
		/*
		 * Optional<List<Product>> products = repository.findByPrice(900.0);
		 * if(products.isPresent()) { List<Product> list = products.get();
		 * list.forEach(p -> System.out.println(p)); }
		 * 
		 */
		Optional<List<Product>> products = repository.findByNameAndPrice("Macbook Pro", 900.0);
		if(products.isPresent()) {
			List<Product> list = products.get();
			list.forEach(p -> System.out.println(p));
		}
	}

}
