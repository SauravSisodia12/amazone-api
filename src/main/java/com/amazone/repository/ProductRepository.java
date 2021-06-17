package com.amazone.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.amazone.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> findByCategory(String category);
	List<Product> findByBrand(String brand);
	
	@Query("from Product p where lower(p.category) like :choice or lower(p.brand) like :choice or lower(p.name) like :choice")
	List<Product> findByCategoryOrNameOrBrand(String choice);
	
	@Query("from Product p where p.price < 10000")
	List<Product> findByPriceLessThen10000();
	@Query("from Product p where p.price > 10000 and p.price <20000")
	List<Product> findByPrice10000to19999();
	@Query("from Product p where p.price > 19999 and p.price <30000")
	List<Product> findByPrice20000to29999();
	@Query("from Product p where p.price > 29999 and p.price <50000")
	List<Product> findByPrice30000to49999();
	@Query("from Product p where p.price >= 50000")
	List<Product> findByPrice50000AndMore();

}
