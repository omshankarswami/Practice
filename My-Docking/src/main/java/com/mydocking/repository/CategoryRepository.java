package com.mydocking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mydocking.model.Category;

@RepositoryRestResource
public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query("SELECT c FROM Category c WHERE c.name =:cname")
	Optional<Category> findByName(@Param("cname") String cname);
	
	@Query("SELECT c FROM Category c WHERE c.parentCategory IS NULL AND UPPER(c.name) IN ('EQUIPMENT', 'SUPPORT', 'VERLADETECHNIK')")
	List<Category> findAllMainCategories();
	
	@Query("SELECT c FROM Category c WHERE c.parentCategory.id  = :categoryId ORDER BY c.name")
	List<Category> findSubCategories(@Param("categoryId") Long categoryId);

	@Query("SELECT c FROM Category c WHERE (c.parentCategory.id  <> :categoryId or c.parentCategory.id is null) and c.id <> :categoryId ORDER BY c.name")
	List<Category> findAllWOSubCategories(@Param("categoryId") Long categoryId);

	List<Category> findAllByOrderByNameAsc();

	@Query("SELECT c FROM Category c WHERE c.name =:cname AND c.isForUploadOnly = TRUE")
	Category findByNameForUpload(@Param("cname") String cname);

}
