package com.amazone.services;

import java.util.List;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.Product;

public interface ProductServices {

	public List<Product> viewAllProduct();
	public List<Product> viewRandomlyTenProducts();
	public Product viewProductById(int id)throws IdNotFoundException;
	void addProduct(Product productDetails);
	void updateProduct(Product productDetails) throws IdNotFoundException;
	void deleteProduct(int ProductId) throws IdNotFoundException;
}
