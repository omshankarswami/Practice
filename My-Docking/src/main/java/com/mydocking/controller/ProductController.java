package com.mydocking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.Product;
import com.mydocking.payload.ResponcePayload;
import com.mydocking.service.ProductService;
import com.mydocking.util.Constants;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService productService;
	
	// Get All Products
	@GetMapping("/products-all") 
	public ResponcePayload getAllProducts() {
		List<Product> products = productService.findAll();

		ResponcePayload payload = new ResponcePayload();

		if (products.size() > 0) {
			payload.setCode(Constants.SUCCESS_CODE);
			payload.setMessage(Constants.SUCCESS_MSG);
		} else {
			payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
			payload.setMessage(Constants.ProductsNotAvailable);
		}
		payload.setData(products);

		return payload;
	}
	 
    
    // Get a Single Product using id
    @GetMapping("/products-id")
    public ResponcePayload getProductById(HttpServletRequest request) {
    	
    	Long productId = 0L;
		try {
			productId = Long.parseLong(request.getParameter("id"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
    	
    	ResponcePayload payload = new ResponcePayload();
    	
    	Product product = null;
		try {
			product = productService.findById(productId);
		} catch (ResourceNotFoundException e) {
			payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
	    	payload.setMessage(Constants.ProductWithProvidedIdNotAvailable);
		}
    			
    	if(product != null)
    	{
	    	payload.setCode(Constants.SUCCESS_CODE);
	    	payload.setMessage(Constants.SUCCESS_MSG);
    	}
	    payload.setData(product);
        return payload;
    }
    
    // Get All Products by category id
    @GetMapping("/products-cid")
    public ResponcePayload getAllProducts(HttpServletRequest request) {
    	
    	Long categoryId = 0L;
		try {
			categoryId = Long.parseLong(request.getParameter("cid"));
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		}
    	
    	List<Product> products = productService.findByCategoryId(categoryId);
    	
    	ResponcePayload payload = new ResponcePayload();
    	if(products.size()>0)
    	{
	    	payload.setCode(Constants.SUCCESS_CODE);
	    	payload.setMessage(Constants.SUCCESS_MSG);
    	}
    	else
    	{
    		payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
	    	payload.setMessage(Constants.ProductsNotAvailableInThisCategory);
    	}
	    payload.setData(products);
    	
        return payload;
    }
    
	/*
	 * // Get All Products by category name
	 * 
	 * @GetMapping("/products-cat") public ResponcePayload
	 * getAllProductsByCName(HttpServletRequest request) {
	 * 
	 * String cname = request.getParameter("cname");
	 * 
	 * List<Product> products = productService.findByCategoryName(cname);
	 * 
	 * ResponcePayload payload = new ResponcePayload(); if(products.size()>0) {
	 * payload.setCode(Constants.SUCCESS_CODE);
	 * payload.setMessage(Constants.SUCCESS_MSG); } else {
	 * payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
	 * payload.setMessage(Constants.ENTITY_NOT_FOUND_MSG); }
	 * payload.setData(products);
	 * 
	 * return payload; }
	 */
    
	/*
	 * // Get a Single Product using productId
	 * 
	 * @GetMapping("/products-pid") public ResponcePayload
	 * getProductByProductId(HttpServletRequest request) { ResponcePayload payload =
	 * new ResponcePayload();
	 * 
	 * String productId = request.getParameter("pid");
	 * 
	 * Product product = null; try { product =
	 * productService.findByProductId(productId); } catch (ResourceNotFoundException
	 * e) { payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
	 * payload.setMessage(Constants.ENTITY_NOT_FOUND_MSG); }
	 * 
	 * if(product != null) { payload.setCode(Constants.SUCCESS_CODE);
	 * payload.setMessage(Constants.SUCCESS_MSG); } payload.setData(product); return
	 * payload; }
	 */
    
    // Get a Products using product name
    @GetMapping("/products-pname")
    public ResponcePayload getProductByProductName(HttpServletRequest request) {
    	ResponcePayload payload = new ResponcePayload();
    	
    	String pname = request.getHeader("pname");
    	
    	List<Product> products = null;	
    	try {	
    		products = productService.findByProductName(pname);
    	} catch (ResourceNotFoundException e) {
    		payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
    		payload.setMessage(Constants.ProductNotAvailableWithProvidedName);
    	}
    	
    	if(products != null)
    	{
    		payload.setCode(Constants.SUCCESS_CODE);
    		payload.setMessage(Constants.SUCCESS_MSG);
    	}
    	payload.setData(products);
    	return payload;
    }
}