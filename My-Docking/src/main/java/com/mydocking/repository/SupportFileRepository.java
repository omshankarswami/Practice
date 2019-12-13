package com.mydocking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.mydocking.model.SupportFile;

@RepositoryRestResource
public interface SupportFileRepository extends JpaRepository<SupportFile, Long> {

	@Query("SELECT s FROM SupportFile s WHERE s.category.name =:cname AND s.approved = TRUE AND s.category.isForUploadOnly = TRUE")
	List<SupportFile> getFilesByCategory(@Param("cname") String cname);
	
	@Query("SELECT s FROM SupportFile s WHERE s.approved = TRUE")
	List<SupportFile> getAllApprovedFiles();

}