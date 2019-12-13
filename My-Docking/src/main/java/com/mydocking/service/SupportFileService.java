package com.mydocking.service;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mydocking.exception.FileStorageException;
import com.mydocking.exception.MyFileNotFoundException;
import com.mydocking.exception.ResourceNotFoundException;
import com.mydocking.model.Category;
import com.mydocking.model.FileStorageProperties;
import com.mydocking.model.SupportFile;
import com.mydocking.repository.SupportFileRepository;

@Service
public class SupportFileService {

    @Autowired
    SupportFileRepository supportFileRepository;
    
    @Autowired
    CategoryService categoryService;
    
    private final Path fileStorageLocation;

    @Autowired
    public SupportFileService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    private static String getFileExtension(String fileName) {
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf("."));
        else return "";
    }
    
    public SupportFile storeFile(MultipartFile video, MultipartFile thumbnail, String cname, String title) {
      
    	SupportFile supportFile = new SupportFile();
    	supportFile.setApproved(false);
    	Category category = categoryService.findByNameForUpload(cname);
    	supportFile.setCategory(category);
    	SupportFile savedSupportFile = supportFileRepository.save(supportFile);
    	
    	String videoFileName = StringUtils.cleanPath(video.getOriginalFilename());
    	String thumbnailFileName = StringUtils.cleanPath(thumbnail.getOriginalFilename());
    	
		/*
		 * String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
		 * .path("/api/downloadFile/") .path(savedSupportFile.getId() +
		 * getFileExtension(fileName)) .toUriString();
		 */
        
        savedSupportFile.setName(title);
        savedSupportFile.setPath("/api/downloadFile/" + savedSupportFile.getId() + getFileExtension(videoFileName));
        savedSupportFile.setThumbnail("/api/downloadFile/" + savedSupportFile.getId() + getFileExtension(thumbnailFileName));
        
        supportFileRepository.save(savedSupportFile);
        
        try {
            // Check if the file's name contains invalid characters
            if(videoFileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + videoFileName);
            }
            if(thumbnailFileName.contains("..")) {
            	throw new FileStorageException("Sorry! Filename contains invalid path sequence " + thumbnailFileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(savedSupportFile.getId() + getFileExtension(videoFileName));
            Files.copy(video.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            targetLocation = this.fileStorageLocation.resolve(savedSupportFile.getId() + getFileExtension(thumbnailFileName));
            Files.copy(thumbnail.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return savedSupportFile;
	    	
        } catch (IOException ex) {
            throw new FileStorageException("Could not store files. Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

	public List<SupportFile> findAll() {
		return supportFileRepository.findAll();
	}

	public SupportFile setFileStatus(Long fileId, boolean status) {
		SupportFile supportFile = supportFileRepository.findById(fileId).orElseThrow(() -> new ResourceNotFoundException("SupportFile", "id", fileId));
		supportFile.setApproved(status);
		return supportFileRepository.save(supportFile);
	}

	public List<SupportFile> getFilesByCategory(String cname) {
		return supportFileRepository.getFilesByCategory(cname);
	}

	public List<SupportFile> getAllApprovedFiles() {
		return supportFileRepository.getAllApprovedFiles();
	}
	
	public void deleteVideoById(Long id) {
		SupportFile supportFile = supportFileRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SupportFile", "id", id));
		Path targetLocation = this.fileStorageLocation.resolve(id + getFileExtension(supportFile.getPath()));
        try {
			Files.delete(targetLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		supportFileRepository.deleteById(id);
	}

}