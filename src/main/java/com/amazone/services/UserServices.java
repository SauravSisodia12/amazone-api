package com.amazone.services;

import java.util.List;
import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Product;
import com.amazone.model.User;


public interface UserServices {

	public boolean validateUser(String userName,String password) throws UserNotFoundException;
	public void registerUser(User userdetails) throws UserAlreadyExistException;
	public int addMoney(int amount,String userid);
	public int checkBalance(String userid);
	public int updateWalletBalance(String userid,int amount);
	public int generateBill(int...ProdIds);
	
	List<Product> viewAllProducts();
	List<Product> viewProductByCategory(String category) throws CategoryNotFoundException;
	List<Product> ViewProductByPrice(int choice) throws ProductNotFoundException;
	List<Product> ViewProductByBrand(String brand) throws BrandNotFoundException;
	List<Product> ViewProductByNameOrBrandOrCategory(String choice) throws CategoryNotFoundException;
}