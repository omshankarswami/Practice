package com.mydocking.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, 
        allowGetters = true)
public class Category implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	/* @NotBlank */
	private String description;	
	
	@NotBlank 
	private String name;	
	
	private String icon;	
	
	
	private String title;
	
	private boolean isForUploadOnly = false;
	
	// Named as items in provided JSON
	@JsonProperty(value = "items")
	@OneToMany(mappedBy="category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<Product> products = new HashSet<Product>();	

	@ManyToOne (cascade={CascadeType.ALL})
	@JoinColumn(name="category_id")
	private Category parentCategory;

	// Sub Categories.
	@OneToMany(mappedBy="parentCategory")
	private Set<Category> subCategories = new HashSet<Category>();
	
	// Named as items in provided JSON
	@OneToMany(mappedBy="category", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Set<SupportFile> supportFiles = new HashSet<SupportFile>();	

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Set<Category> getSubCategories() {
		return subCategories;
	}

	@JsonIgnore
	public void setSubCategories(Set<Category> subCategories) {
		this.subCategories = subCategories;
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
	
	/*
	 * public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
	 */

	public Set<SupportFile> getSupportFiles() {
		return supportFiles;
	}
	
	@JsonIgnore
	public void setSupportFiles(Set<SupportFile> supportFiles) {
		this.supportFiles = supportFiles;
	}

	public boolean isForUploadOnly() {
		return isForUploadOnly;
	}

	public void setForUploadOnly(boolean isForUploadOnly) {
		this.isForUploadOnly = isForUploadOnly;
	}
}
