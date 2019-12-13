package com.mydocking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.Category;
import com.mydocking.payload.ResponcePayload;
import com.mydocking.service.CategoryService;
import com.mydocking.util.Constants;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService service;

    // Get All Categories
    @GetMapping("/categories-main")
    public ResponcePayload getAllCategories() {
    	
    	List<Category> categories = service.findAllMainCategories();
    	
    	ResponcePayload payload = new ResponcePayload();
    	if(categories != null)
    	{
	    	payload.setCode(Constants.SUCCESS_CODE);
	    	payload.setMessage(Constants.SUCCESS_MSG);
    	}
    	else
    	{
    		payload.setCode(Constants.FAIL_CODE);
	    	payload.setMessage(Constants.CategoriesNotAvailable);
    	}
    	
	    payload.setData(categories);
    	
        return payload;
    }
    
	 // Get a Single Category	 
	 @GetMapping("/categories-id") 
	 public ResponcePayload getCategoryById(HttpServletRequest request) {
	    	
	    	Long categoryId = 0L;
			try {
				categoryId = Long.parseLong(request.getParameter("id"));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
	    	
	    	ResponcePayload payload = new ResponcePayload();
	    	
	    	Category category = null;
			try {
				category = service.findById(categoryId);
				//category.setSubCategories(service.findSubCategories(categoryId));				
			} catch (ResourceNotFoundException e) {
				payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
		    	payload.setMessage(Constants.CategoryWithProvidedIdNotAvailable);
			}
	    			
	    	if(category != null)
	    	{
		    	payload.setCode(Constants.SUCCESS_CODE);
		    	payload.setMessage(Constants.SUCCESS_MSG);
	    	}
		    payload.setData(category);
	        return payload;
	 }
	
	 @GetMapping("/categories-sub") 
	 public ResponcePayload getSubCategories(HttpServletRequest request) {
	    	
	    	Long parentCategoryId = 0L;
			try {
				parentCategoryId = Long.parseLong(request.getParameter("id"));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			}
	    	
	    	ResponcePayload payload = new ResponcePayload();
	    	
	    	List<Category> categories = null;
			try {
				categories = service.findSubCategories(parentCategoryId);
				
			} catch (ResourceNotFoundException e) {
				payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
		    	payload.setMessage(Constants.SubCategoriesNotAvailableForThisCategory);
			}
	    			
	    	if(categories != null)
	    	{
		    	payload.setCode(Constants.SUCCESS_CODE);
		    	payload.setMessage(Constants.SUCCESS_MSG);
	    	}
		    payload.setData(categories);
	        return payload;
	 }
}