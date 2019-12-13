package com.mydocking.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

	public static int SUCCESS_CODE=200;
	public static int FAIL_CODE=400;
	public static int ENTITY_NOT_FOUND_CODE=404;
	
	public static String SUCCESS_MSG="Retrieval Succeeded.";
	public static String UPLOAD_SUCCESS_MSG="Upload Succeeded.";
	public static String FAIL_MSG="Retrieval Failed";
	public static String ENTITY_NOT_FOUND_MSG="Queried data not found.";
	public static String FAIL_TO_UPLOAD_FILE = "Failed to upload file.";
	
	public static String CategoriesNotAvailable = "Categories not available";
	public static String CategoryWithProvidedIdNotAvailable = "Category with provided ID not available";
	public static String SubCategoriesNotAvailableForThisCategory = "Sub categories not available for this category";
	
	public static String ProductsNotAvailable = "Products not available";
	public static String ProductWithProvidedIdNotAvailable = "Product with provided ID not available";
	public static String ProductsNotAvailableInThisCategory = "Products not available in this category";
	public static String ProductNotAvailableWithProvidedName = "Product not available with provided name";
	
	public static String FailedToUploadVideoFile = "Failed to upload video file";
	public static String CurrentlyThereAreNoVideosForTheCategory = "Currently there are no videos for the category";
	public static String VideosNotAvailable = "Videos not available.";

	
	@Value("${categories.not.available}")
	public void setCategoriesNotAvailable(String categoriesNotAvailable) {
		CategoriesNotAvailable = categoriesNotAvailable;
	}
	
	@Value("${category.with.provided.ID.not.available}")
	public void setCategoryWithProvidedIdNotAvailable(String categoryWithProvidedIdNotAvailable) {
		CategoryWithProvidedIdNotAvailable = categoryWithProvidedIdNotAvailable;
	}
	
	@Value("${sub.categories.not.available.for.this.category}")
	public void setSubCategoriesNotAvailableForThisCategory(String subCategoriesNotAvailableForThisCategory) {
		SubCategoriesNotAvailableForThisCategory = subCategoriesNotAvailableForThisCategory;
	}
	
	@Value("${products.not.available}")
	public void setProductsNotAvailable(String productsNotAvailable) {
		ProductsNotAvailable = productsNotAvailable;
	}
	
	@Value("${product.with.provided.ID.not.available}")
	public void setProductWithProvidedIdNotAvailable(String productWithProvidedIdNotAvailable) {
		ProductWithProvidedIdNotAvailable = productWithProvidedIdNotAvailable;
	}
	
	@Value("${products.not.available.in.this.category}")
	public void setProductsNotAvailableInThisCategory(String productsNotAvailableInThisCategory) {
		ProductsNotAvailableInThisCategory = productsNotAvailableInThisCategory;
	}
	
	@Value("${product.not.available.with.provided.name}")
	public void setProductNotAvailableWithProvidedName(String productNotAvailableWithProvidedName) {
		ProductNotAvailableWithProvidedName = productNotAvailableWithProvidedName;
	}
	
	@Value("${failed.to.upload.video.file}")
	public void setFailedToUploadVideoFile(String failedToUploadVideoFile) {
		FailedToUploadVideoFile = failedToUploadVideoFile;
	}
	
	@Value("${currently.there.are.no.videos.for.the.category}")
	public void setCurrentlyThereAreNoVideosForTheCategory(String currentlyThereAreNoVideosForTheCategory) {
		CurrentlyThereAreNoVideosForTheCategory = currentlyThereAreNoVideosForTheCategory;
	}
	
	@Value("${videos.not.available}")
	public void setVideosNotAvailable(String videosNotAvailable) {
		VideosNotAvailable = videosNotAvailable;
	}
	
}