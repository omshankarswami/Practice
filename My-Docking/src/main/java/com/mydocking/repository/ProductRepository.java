package com.mydocking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mydocking.model.Product;

@RepositoryRestResource
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	@Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

	@Query("SELECT p FROM Product p WHERE p.category.name like %:cname%")
	List<Product> findByCategoryName(@Param("cname") String cname);

	/*
	 * @Query(value = "SELECT * FROM Products WHERE product_Id = :productId", nativeQuery = true) 
	 * Product findByProductId(@Param("productId") String productId);	
	 *
	 * @Query("SELECT p FROM Product p WHERE p.productId = :productId")
	 * Product findByProductId(@Param("productId") String productId);
	 */
	
	/* Product findByProductId(String productId); */
	
	@Query("SELECT p FROM Product p WHERE p.itemName like %:itemName%")
	List<Product> findByItemName(@Param("itemName") String itemName);

}