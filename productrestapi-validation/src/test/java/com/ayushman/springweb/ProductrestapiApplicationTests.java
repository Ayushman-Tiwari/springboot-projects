package com.ayushman.springweb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.ayushman.springweb.entity.Product;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Assert.*;

@SpringBootTest
class ProductrestapiApplicationTests {

	@Value("${productrestapi.services.url}")
	private String baseURL;
	
	@Test
	void testGetProduct() {
		System.out.println(baseURL);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "/1", Product.class);
		assertNotNull(product);
		assertEquals("iPad", product.getName());
	}

	/*
	 * @Test void testCreateProduct() { RestTemplate restTemplate = new
	 * RestTemplate(); Product product = new Product();
	 * product.setName("Samsung Note 20+");
	 * product.setDescription("Flagship phone by Samsung"); product.setPrice(1000);
	 * Product newProduct = restTemplate.postForObject(baseURL, product,
	 * Product.class); assertNotNull(newProduct); assertNotNull(newProduct.getId());
	 * assertEquals("Samsung Note 20+", newProduct.getName()); }
	 * 
	 * @Test void testUpdateProduct() { RestTemplate restTemplate = new
	 * RestTemplate(); Product product = restTemplate.getForObject(baseURL + "/5",
	 * Product.class); product.setPrice(1200); restTemplate.put(baseURL, product); }
	 * 
	 * @Test public void testDeleteProduct() { RestTemplate restTemplate = new
	 * RestTemplate(); restTemplate.delete(baseURL + "/5"); }
	 */
}
