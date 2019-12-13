package com.mydocking.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.SupportFile;
import com.mydocking.service.SupportFileService;

@Controller
@RequestMapping("/web")
public class SupportFileWebController {

    @Autowired
    SupportFileService supportFileService;

    // Get All Products
    @RequestMapping("/files")
    public String getAllFiles(Model model) {
        List<SupportFile> list = supportFileService.findAll();
        model.addAttribute("fileList", list);
        return "files";
    }
        
    @RequestMapping(value="/updateStatus/{fileId}/{status}", method = RequestMethod.GET)
	public String setApprovalRejection(@PathVariable(value = "fileId") Long fileId, @PathVariable(value="status") boolean status, Model model){
    	
    	SupportFile supportFile = null;
    	try {
			supportFile = supportFileService.setFileStatus(fileId, status);
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
    	
    	if(supportFile == null)
    		model.addAttribute("status", "Record Not Found.");
    		
    	List<SupportFile> list = supportFileService.findAll();
        model.addAttribute("fileList", list);
        return "files";
	}
    
	@RequestMapping(value = "/deleteFile")
	public @ResponseBody String deleteProduct(@RequestParam(value = "id") long id) {

		supportFileService.deleteVideoById(id);

		return "success";
	}
}