package com.test.vajro.bean;

import com.test.vajro.abstraction.ResponseModel;

public class Response extends ResponseModel{
 
	public String status;
	public String description;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Response(String status, String description) {
		super();
		this.status = status;
		this.description = description;
	}
	
	

}
