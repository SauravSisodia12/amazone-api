package com.amazone.services;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.Product;
import com.amazone.repository.ProductRepository;

@Service
public class ProductServicesImple implements ProductServices {
	
	@Autowired
	ProductRepository productdao;

	@Override
	public void addProduct(Product productDetails) {
		productdao.save(productDetails);	
	}

	@Override
	public void updateProduct(Product product) throws IdNotFoundException {
		try {		
		productdao.save(product);
		} catch (Exception e) {
			throw new IdNotFoundException("Invalid Id/Database Error....");
		}
		
	}

	@Override
	public void deleteProduct(int ProductId) throws IdNotFoundException {
		try {		
			productdao.deleteById(ProductId);
			} catch (Exception e) {
				throw new IdNotFoundException("Invalid Id/Database Error....");
			}
		
	}
	
	@Override
	public List<Product> viewAllProduct() {
		return productdao.findAll();
	}

	@Override
	public Product viewProductById(int id) throws IdNotFoundException {
		Product product =  productdao.findById(id).get();
		if(product==null)
			throw new IdNotFoundException("Id Not Found");
		else
			return product;
	}

	@Override
	public List<Product> viewRandomlyTenProducts() {
		List<Product> productList = productdao.findAll();
		Collections.shuffle(productList);
		return productList.subList(0, 10);
	}
	
}
