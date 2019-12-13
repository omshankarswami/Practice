package com.mydocking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mydocking.exception.FileStorageException;
import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.FileStorageProperties;
import com.mydocking.model.Product;
import com.mydocking.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    
    private final Path fileStorageLocation;

	@Autowired
	public ProductService(FileStorageProperties fileStorageProperties) {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
		}
	}

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    public Product findById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
    }
    
    public Product save(Long productId, Product productDetails) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        product.setItemName(productDetails.getItemName());
        product.setDescription(productDetails.getDescription());
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }
    
    public ResponseEntity<?> delete(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product", "id", productId));
        productRepository.delete(product);
        return ResponseEntity.ok().build();
    }

	public List<Product> findByCategoryId(Long categoryId) {
		
		/*
		 * List<Product> products = productRepository.findByCategoryId(categoryId);
		 * 
		 * for (Product product : products) { List<String> imageList = new
		 * ArrayList<String>(); List<String> pdfList = new ArrayList<String>();
		 * 
		 * for (String image : product.getProductImages())
		 * imageList.add("/api/downloadFile/" + image);
		 * product.setProductImages(imageList);
		 * 
		 * for (String pdf : product.getProductPDFs()) pdfList.add("/api/downloadFile/"
		 * + pdf); product.setProductPDFs(pdfList); }
		 */
		return productRepository.findByCategoryId(categoryId);
	}

	public List<Product> findByCategoryName(String cname) {
		return productRepository.findByCategoryName(cname);
	}

	/*
	 * public Product findByProductId(String productId) { return
	 * productRepository.findByProductId(productId); }
	 */
	
	public List<Product> findByProductName(String productName) {
		/*
		 * List<Product> products = productRepository.findByItemName(productName);
		 * 
		 * for (Product product : products) { List<String> imageList = new
		 * ArrayList<String>(); List<String> pdfList = new ArrayList<String>();
		 * 
		 * for (String image : product.getProductImages())
		 * imageList.add("/api/downloadFile/" + image);
		 * product.setProductImages(imageList);
		 * 
		 * for (String pdf : product.getProductPDFs()) pdfList.add("/api/downloadFile/"
		 * + pdf); product.setProductPDFs(pdfList); }
		 * 
		 * 
		 */
		return productRepository.findByItemName(productName);
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

	public Product findByIdWithURL(Long productId) {
		Product product = findById(productId);
		
		List<String> imageList = new ArrayList<String>(); 
		List<String> pdfList = new ArrayList<String>();

		for (String image : product.getProductImages()) 
			imageList.add("/api/downloadFile/" + image);
		product.setProductImages(imageList);
		
		for (String pdf : product.getProductPDFs()) 
			pdfList.add("/api/downloadFile/" + pdf);
		product.setProductPDFs(pdfList);
				
		return product;
	}

}