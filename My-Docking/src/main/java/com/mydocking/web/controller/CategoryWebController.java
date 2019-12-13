package com.mydocking.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.mydocking.model.Category;
import com.mydocking.service.CategoryService;

@Controller
@RequestMapping("/web")
public class CategoryWebController {
	
    @Autowired
	CategoryService categoryService;

    // Get All Products
    @RequestMapping({"/category"})
    public String getAllCategories(Model model) {
        List<Category> list =  categoryService.findAll();
        model.addAttribute("categoryList", list);
        return "category";
    }
    
  //create new Category
    @RequestMapping(value="/createCategory")
	public String addProduct(Model model){
    	List<Category> categories = categoryService.findAll();
    	model.addAttribute("categories", categories);
		return "addCategory";
	}
    
    @RequestMapping(value="/addCategory", method=RequestMethod.POST)
	public String insertProduct(@ModelAttribute("category") Category category, Model model, 
			@RequestParam(value = "icon1", required = false) MultipartFile iconFile, BindingResult result) throws IOException{
		
    	if(iconFile.getSize() != 0)
    	{
    		categoryService.storeFile(iconFile);
    		category.setIcon(iconFile.getOriginalFilename());
    	}
		category = categoryService.save(category);
		
   		return "redirect:/web/category";
   	}
    
    @RequestMapping(value="/editCategory")
	public String getCategoryByCategoryId(@RequestParam(value="cid") Long cid, Model model){
		
		Category category = new Category();
		
		category =categoryService.findById(cid);
		
		List<Category> categories = categoryService.findAllWOSubCategories(cid);
		
		model.addAttribute("id", category.getId());
		model.addAttribute("name", category.getName());
		model.addAttribute("description", category.getDescription()); 
		model.addAttribute("title", category.getTitle());
		model.addAttribute("categories", categories);
		model.addAttribute("parentCategory", category.getParentCategory() != null ? category.getParentCategory().getId() : 0);
		model.addAttribute("icon", category.getIcon());
		
		return "updateCategory";
	}
    
    @RequestMapping(value="/updateCategory", method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute(value="category") Category category, Model model, BindingResult result, 
			@RequestParam(value = "icon1", required = false) MultipartFile iconFile,
			@RequestParam(value = "iconName", required = false) String iconName, HttpServletRequest request){
    	
    	if(iconFile.getSize() != 0){
    		categoryService.storeFile(iconFile);
    		category.setIcon(iconFile.getOriginalFilename());
    	}
    	else if(iconName !=null && iconName.trim().length()>0) {
    		category.setIcon(iconName);
    	}
    	else {
    		category.setIcon(null);
    	}
		categoryService.save(category);
		
		return "redirect:/web/category";
	}
       
	@RequestMapping(value = "/deleteCategory")
	public String deleteCategories(@RequestParam(value = "cid") long cid, Model model) {
		/*
		 * Category category = new Category(); category.setId(cid);
		 */
		categoryService.delete(cid);

		return "redirect:/web/category";
	}
    
	@RequestMapping(value = "/deleteCategoryIcon")
	public @ResponseBody String deletePdfFileName(@RequestParam(value = "fileName") String fileName, Model model,
			@RequestParam(value = "cid") long cid) {

		Category category = categoryService.findById(cid);
		category.setIcon(null);
		categoryService.save(category);

		categoryService.deleteFile(fileName);
		return "success";
	}
}
