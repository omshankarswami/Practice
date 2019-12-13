package com.mydocking.controller;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mydocking.model.SupportFile;
import com.mydocking.payload.ResponcePayload;
import com.mydocking.service.SupportFileService;
import com.mydocking.util.Constants;

@CrossOrigin
@RequestMapping("/api")
@RestController
public class SupportFileController {

    private static final Logger logger = LoggerFactory.getLogger(SupportFileController.class);

    @Autowired
    private SupportFileService supportFileService;
    
	@PostMapping("/uploadFile") 
	public ResponcePayload uploadFile(@RequestParam("video") MultipartFile video, @RequestParam("thumbnail") MultipartFile thumbnail, HttpServletRequest request) {
		
		String cname = request.getParameter("cname");
		String title = request.getParameter("title");
		
    	SupportFile supportFile = supportFileService.storeFile(video, thumbnail, cname, title);

    	ResponcePayload payload = new ResponcePayload();
    	if(supportFile != null)
    	{
	    	payload.setCode(Constants.SUCCESS_CODE);
	    	payload.setMessage(Constants.UPLOAD_SUCCESS_MSG);
    	}
    	else
    	{
    		payload.setCode(Constants.FAIL_CODE);
	    	payload.setMessage(Constants.FailedToUploadVideoFile);
    	}
    	
	    payload.setData(supportFile);
    	
        return payload;
        
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = supportFileService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    // Get All Videos based on category General
    @GetMapping("/files-cat")
    public ResponcePayload getFilesByCategory(HttpServletRequest request) {
		String cname = request.getParameter("cname");
		System.out.println("cname=" + cname);
    	ResponcePayload payload = new ResponcePayload();
		List<SupportFile> supportFiles = supportFileService.getFilesByCategory(cname);

    	if(supportFiles.size() > 0)
    	{
    		payload.setCode(Constants.SUCCESS_CODE);
    		payload.setMessage(Constants.SUCCESS_MSG);
    	}
    	else
    	{
    		payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
    		payload.setMessage(Constants.CurrentlyThereAreNoVideosForTheCategory);
    	}
    	
    	payload.setData(supportFiles);
		return payload;
    } 
     
	/*
	 * // Get All Videos based on category for ios
	 * 
	 * @GetMapping("/files-cat-ios") public ResponcePayload
	 * getFilesByCategoryIOS(HttpServletRequest request) { String cname =
	 * request.getHeader("cname"); ResponcePayload payload =
	 * getFilesByCategoryUtil(cname); return payload; }
	 * 
	 * // Get All Videos based on category for android
	 * 
	 * @GetMapping("/files-cat-android") public ResponcePayload
	 * getFilesByCategoryAndroid(HttpServletRequest request) { String cname =
	 * request.getParameter("cname"); ResponcePayload payload =
	 * getFilesByCategoryUtil(cname); return payload; }
	 */

    // Get All Videos
    @GetMapping("/files-all")
    public ResponcePayload getAllFiles() {
        
    	System.out.println(Constants.CurrentlyThereAreNoVideosForTheCategory);
    	
    	List<SupportFile> supportFiles = supportFileService.getAllApprovedFiles();

    	ResponcePayload payload = new ResponcePayload();
    	if(supportFiles.size() > 0)
    	{
	    	payload.setCode(Constants.SUCCESS_CODE);
	    	payload.setMessage(Constants.SUCCESS_MSG);
    	}
    	else
    	{
    		payload.setCode(Constants.ENTITY_NOT_FOUND_CODE);
	    	payload.setMessage(Constants.VideosNotAvailable);
    	}
    	
	    payload.setData(supportFiles);
    	
        return payload;
        
    }
}