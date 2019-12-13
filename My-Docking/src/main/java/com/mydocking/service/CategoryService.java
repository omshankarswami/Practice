package com.mydocking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mydocking.exception.FileStorageException;
import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.Category;
import com.mydocking.model.FileStorageProperties;
import com.mydocking.repository.CategoryRepository;

@Service
public class CategoryService {

	 @Autowired
	 CategoryRepository repository;
    
	private final Path fileStorageLocation;

	@Autowired
	public CategoryService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}
		
    public List<Category> findAll() {
        return repository.findAllByOrderByNameAsc();
    }
    
    public Category findById(Long categoryId) {
        return repository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
    }
    
    public Category findByName(String categoryName) {
        return repository.findByName(categoryName).orElseThrow(() -> new ResourceNotFoundException("Category", "name", categoryName));
    }

	public List<Category> findAllMainCategories() {
		return repository.findAllMainCategories();
	}

	public List<Category> findSubCategories(Long categoryId) {
		return repository.findSubCategories(categoryId);
	}
	
	 public Category save(Category category) {
		 return repository.save(category);
    }
    
   
    public ResponseEntity<?> delete(Long categoryId) {
        Category category = repository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
       repository.delete(category);
        return ResponseEntity.ok().build();
    }
    
	
     public void storeFile(MultipartFile file) {
    	 String fileName = StringUtils.cleanPath(file.getOriginalFilename());

 		try {
 			// Check if the file's name contains invalid characters
 			if(fileName.contains("..")) {
 				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
 			}

 			// Copy file to the target location (Replacing existing file with the same name)
 			Path targetLocation = this.fileStorageLocation.resolve(file.getOriginalFilename());
 			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

 		} catch (IOException ex) {
 			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
 		}
     }

	public List<Category> findAllWOSubCategories(Long categoryId) {
		return repository.findAllWOSubCategories(categoryId);
	}

	public void deleteFile(String fileName) {
		try {
			// Check if the file's name contains invalid characters
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.delete(targetLocation);
		}catch (IOException ex) {
			throw new FileStorageException("Could not delete file " + fileName + ". Please try again!", ex);
		}
	}

	public Category findByNameForUpload(String cname) {
		return repository.findByNameForUpload(cname);
	}
}