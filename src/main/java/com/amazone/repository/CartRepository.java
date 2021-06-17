package com.amazone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.amazone.model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("SELECT sum(c.price) from Cart c")
	int sumCartAmount();
	
}
