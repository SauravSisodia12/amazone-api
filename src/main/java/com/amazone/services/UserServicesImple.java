package com.amazone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.BrandNotFoundException;
import com.amazone.exception.CategoryNotFoundException;
import com.amazone.exception.ProductNotFoundException;
import com.amazone.exception.UserAlreadyExistException;
import com.amazone.exception.UserNotFoundException;
import com.amazone.model.Product;
import com.amazone.model.User;
import com.amazone.repository.ProductDAO;
import com.amazone.repository.UserDAO;

@Service
public class UserServicesImple implements UserServices {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	ProductDAO productDao;

	@Override
	public boolean validateUser(String userName, String password) throws UserNotFoundException{
		boolean result = userDAO.existsByUserIdAndPassword(userName, password);
		if(!result)
			throw new UserNotFoundException("User Not Found");
		return result;
	}

	@Override
	public void registerUser(User userdetails) throws UserAlreadyExistException {
		userDAO.save(userdetails);
	}

	@Override
	public int addMoney(int amount, String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int checkBalance(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateWalletBalance(String userid, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int generateBill(int... ProdIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Product> viewAllProducts() {
		return productDao.findAll(); 
	}

	@Override
	public List<Product> viewProductByCategory(String category) throws CategoryNotFoundException{
		List<Product> productList = productDao.findByCategory(category);
		if(productList.isEmpty())
			throw new CategoryNotFoundException("Category Not Found");
		return productList;
	}

	@Override
	public List<Product> ViewProductByPrice(int choice) throws ProductNotFoundException {
		List<Product> productList = productDao.findByPrice(choice);
		if(productList.isEmpty())
			throw new ProductNotFoundException("Products Not Available");
		return productList;
	}

	@Override
	public List<Product> ViewProductByBrand(String brand) throws BrandNotFoundException{
		List<Product> productList =  productDao.findByBrand(brand);
		if(productList.isEmpty())
			throw new BrandNotFoundException("Brand Not Found");
		return productList;
	}

	@Override
	public List<Product> ViewProductByNameOrBrandOrCategory(String choice) throws CategoryNotFoundException{
		System.out.println(choice);
		List<Product> productList = productDao.findByCategoryOrNameOrBrand("%"+choice+"%");
		if(productList.isEmpty())
			throw new CategoryNotFoundException("Category Not Found");
		return productList;
	}
	
}

	

//	public int addMoney(int amount, String userid) {
//		return userDAO.DAOaddMoney(amount,userid);
//		
//	}

//
//	public List<Product> ViewProductByPrice(int choice) {
//		if(choice == 1)
//			return  userDAO.findAllProducts()
//					.stream()
//					.sorted((b1,b2)->b1.getPrice().compareTo(b2.getPrice()))
//					.collect(Collectors.toList());
//		else
//			return  userDAO.findAllProducts()
//					.stream()
//					.sorted((b1,b2)->b2.getPrice().compareTo(b1.getPrice()))
//					.collect(Collectors.toList());
//	}

//
//	@Override
//	public int checkBalance(String userid) {
//		return userDAO.checkBalance(userid);
//	}
//
//	@Override
//	public int generateBill(int... ProdIds) {
//		return userDAO.generateBill(ProdIds);
//		
//	}
//
//	@Override
//	public int updateWalletBalance(String userid, int amount) {
//		return userDAO.updateWalletBalance(userid, amount);
//	}

