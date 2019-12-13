package com.mydocking.web.controller;

import java.util.ArrayList;
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
import com.mydocking.model.Product;
import com.mydocking.service.CategoryService;
import com.mydocking.service.ProductService;

@Controller
@RequestMapping("/web")
public class ProductWebController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	// Get All Products
	@RequestMapping({ "/products" })
	public String getAllProducts(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("proudctList", list);
		return "products";
	}

	// create new product
	@RequestMapping(value = "/createProduct")
	public String addProduct(Model model) {
		List<Category> categories = categoryService.findAll();
		System.out.println(categories + "categories fields data");
		model.addAttribute("categories", categories);
		return "addProduct";
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String insertProduct(@ModelAttribute(value = "product") Product product, Model model, BindingResult result,
			@RequestParam("productImages1") MultipartFile[] productImages,
			@RequestParam("productPDFs1") MultipartFile[] productPDFs) {

		List<String> imageList = new ArrayList<String>();
		List<String> productPDFlist = new ArrayList<String>();

		for (MultipartFile multipartFile : productImages) {
			if(multipartFile.getOriginalFilename().trim().length() > 0)
			{
				imageList.add(multipartFile.getOriginalFilename());
				productService.storeFile(multipartFile);
			}
		}
		for (MultipartFile multipartFile : productPDFs) {
			if(multipartFile.getOriginalFilename().trim().length() > 0)
			{
				productPDFlist.add(multipartFile.getOriginalFilename());
				productService.storeFile(multipartFile);
			}
		}

		product.setProductImages(imageList);
		product.setProductPDFs(productPDFlist);

		product = productService.save(product);

		return "redirect:/web/products";
	}

	@RequestMapping(value = "/update")
	public String getProductByProductId(@RequestParam(value = "pid") Long pid, Model model) {

		Product product = new Product();

		product = productService.findById(pid);
		List<Category> categories = categoryService.findAll();
		System.out.println(categories + "categories fields data");

		/* model.addAttribute("pId", product.getProductId()); */
		model.addAttribute("productId", product.getId());
		model.addAttribute("productName", product.getItemName());
		model.addAttribute("description", product.getDescription());
		model.addAttribute("categoryId", product.getCategory().getId());
		model.addAttribute("categories", categories);
		model.addAttribute("keyPointHeading", product.getKeyPointHeading());
		model.addAttribute("fileNameExtension", product.getFileNameExtension());
		model.addAttribute("productImages", product.getProductImages());
		model.addAttribute("productPDFs", product.getProductPDFs());
		model.addAttribute("keyPoints", product.getKeyPoints());
		model.addAttribute("createdAt", product.getCreatedAt());
		model.addAttribute("updatedAt", product.getUpdatedAt());
		model.addAttribute("productLink", product.getProductLink());

		return "update";
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute(value = "product") Product product, Model model, BindingResult result,
			@RequestParam("productImages1") MultipartFile[] productImages,
			@RequestParam("productPDFs1") MultipartFile[] productPDFs, HttpServletRequest request) {

		Product productFromDB = productService.findById(product.getId());
		List<String> images = productFromDB.getProductImages();

		for (MultipartFile multipartFile : productImages) {
			if(multipartFile.getOriginalFilename().trim().length() > 0)
			{
				images.add(multipartFile.getOriginalFilename());
				productService.storeFile(multipartFile);
			}
		}
		
		List<String> pdfs = productFromDB.getProductPDFs();
		
		for (MultipartFile multipartFile : productPDFs) {
			if(multipartFile.getOriginalFilename().trim().length() > 0)
			{
				pdfs.add(multipartFile.getOriginalFilename());
				productService.storeFile(multipartFile);
			}
		}

		product.setProductImages(images);
		product.setProductPDFs(pdfs);

		productService.save(product);

		return "redirect:/web/products";
	}

	@RequestMapping(value = "/delete")
	public String deleteProduct(@RequestParam(value = "pid") long pid, Model model) {

		Product product = new Product();
		product.setId(pid);

		productService.delete(pid);

		return "redirect:/web/products";
	}

	@RequestMapping(value = "/deleteFileAndFileName")
	public @ResponseBody String deleteFileName(@RequestParam(value = "pname") String pname, Model model,
			@RequestParam(value = "pid") long pid) {

		Product product = productService.findById(pid);
		List<String> imageNames = product.getProductImages();

		imageNames.remove(pname);

		product.setProductImages(imageNames);
		productService.save(product);
	

		productService.deleteFile(pname);
		return "success";
	}

	@RequestMapping(value = "/deletePdfFileAndFileName")
	public @ResponseBody String deletePdfFileName(@RequestParam(value = "pname") String pname, Model model,
			@RequestParam(value = "pid") long pid) {

		Product product = productService.findById(pid);
		List<String> pdfNames = product.getProductPDFs();

		pdfNames.remove(pname);

		product.setProductPDFs(pdfNames);
		productService.save(product);

		productService.deleteFile(pname);
		return "success";
	}
}