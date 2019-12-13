package com.mydocking.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Product implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/*
	 * @NotBlank private String productId;
	 */
	
 	@NotBlank
	private String itemName; 
 	private String description;
 	
	private String keyPointHeading;
	private String fileNameExtension;
	
	private String productLink;
	
	@ElementCollection
    private List<String> productImages;
	
	@ElementCollection
    private List<String> productPDFs;  //gfggh.pdf~gyughj uihiuh
	
	@ElementCollection
    private List<String> keyPoints;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} , fetch = FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;

	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

	@UpdateTimestamp
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getKeyPointHeading() {
		return keyPointHeading;
	}

	public void setKeyPointHeading(String keyPointHeading) {
		this.keyPointHeading = keyPointHeading;
	}

	public String getFileNameExtension() {
		return fileNameExtension;
	}

	public void setFileNameExtension(String fileNameExtension) {
		this.fileNameExtension = fileNameExtension;
	}

	public List<String> getProductImages() {
		return productImages;
	}

	public void setProductImages(List<String> productImages) {
		this.productImages = productImages;
	}

	public List<String> getProductPDFs() {
		return productPDFs;
	}

	public void setProductPDFs(List<String> productPDFs) {
		this.productPDFs = productPDFs;
	}

	public List<String> getKeyPoints() {
		return keyPoints;
	}

	public void setKeyPoints(List<String> keyPoints) {
		this.keyPoints = keyPoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@JsonIgnore
	public Date getCreatedAt() {
		return createdAt;
	}

	/*
	 * public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
	 */

	@JsonIgnore
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public String getProductLink() {
		return productLink;
	}

	public void setProductLink(String productLink) {
		this.productLink = productLink;
	}

	/*
	 * public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
	 */

	/*
	 * public String getProductId() { return productId; }
	 * 
	 * public void setProductId(String productId) { this.productId = productId; }
	 */
	
}