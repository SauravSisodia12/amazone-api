package com.amazone.services;

import java.util.List;

import com.amazone.exception.IdNotFoundException;
import com.amazone.model.Cart;

public interface CartService {

	void addProductToCart(Cart cartItem);
	void deleteProductFromCart(int ProductId) throws IdNotFoundException;
	List<Cart> allCartItems();
}
