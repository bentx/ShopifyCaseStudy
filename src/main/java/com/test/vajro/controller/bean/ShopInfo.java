package com.test.vajro.controller.bean;

import com.fasterxml.jackson.annotation.JsonRootName;


public class ShopInfo {
	
	private String name;
	private String domain;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	
	

}
