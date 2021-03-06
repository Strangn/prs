package com.prs;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import com.prs.business.product.Product;
import com.prs.business.product.ProductRepository;
import com.prs.business.vendor.Vendor;
import com.prs.business.vendor.VendorRepository;

@RunWith(SpringRunner.class)
public class PrsProductTest extends PrsWebApplicationTests  {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private VendorRepository vendorRepository;
	
	@Test
	public void testProductCrudFunctions()  {
		//get all products
		Iterable<Product> products = productRepository.findAll();
		assertNotNull(products);
		
		//add a product
		Optional <Vendor> v = vendorRepository.findById(3);
		Product p1 = new Product(v.get(), "partNumber", "name", 100.00, "unit", "photoPath");
		assertNotNull(productRepository.save(p1));
		int id = p1.getId();

		//get user and validate product name is correct
		Optional<Product> p2 = productRepository.findById(id);
		assertEquals(p2.get().getName(),"ProductName");

		//update the product
		p2.get().setName("newProduct");
		assertNotNull(productRepository.save(p2.get()));
		
		// remove the product
		productRepository.delete(p2.get());
		assertThat(!(productRepository.findById(id)).isPresent());
	}
	

	
}
