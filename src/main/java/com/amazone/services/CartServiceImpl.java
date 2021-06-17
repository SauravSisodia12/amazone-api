package com.amazone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.Cart;
import com.amazone.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartDao;

	@Override
	public void addProductToCart(Cart cartItem) {
		cartDao.save(cartItem);
		
	}

	@Override
	public void deleteProductFromCart(int ProductId) throws IdNotFoundException {
		cartDao.deleteById(ProductId);
	}

	@Override
	public List<Cart> allCartItems() {
		return cartDao.findAll();
	}

	@Override
	public int cartTotal() {
		return cartDao.sumCartAmount();
	}

	@Override
	public void deleteAll() {
		cartDao.deleteAll();
	}
	

}
