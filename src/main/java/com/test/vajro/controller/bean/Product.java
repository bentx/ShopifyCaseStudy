package com.test.vajro.controller.bean;

import java.util.List;

public class Product{

	   private long id;
	   private String title;
	   private String created_at;
	   private String status;
	   private List<varient> varients;
	   private List<image> images;
	   
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<varient> getVarients() {
		return varients;
	}
	public void setVarients(List<varient> varients) {
		this.varients = varients;
	}
	public List<image> getImages() {
		return images;
	}
	public void setImages(List<image> images) {
		this.images = images;
	}
	   
	   

}
