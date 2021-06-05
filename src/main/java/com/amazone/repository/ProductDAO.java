package com.amazone.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.amazone.model.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(String category);
	List<Product> findByPrice(int price);
	List<Product> findByBrand(String brand);
	
	@Query("from Product p where lower(p.category) like :choice or lower(p.brand) like :choice or lower(p.name) like :choice")
	List<Product> findByCategoryOrNameOrBrand(String choice);

}
