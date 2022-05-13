package com.test.vajro.controller.bean;

import java.util.List;

import com.test.vajro.abstraction.ResponseModel;

public class ShopInfoResponse extends ResponseModel{
	private ShopInfo shop_info;
	private int product_count;
	private List<Product> products;
	
	
	public ShopInfo getShop_info() {
		return shop_info;
	}
	public void setShop_info(ShopInfo shop_info) {
		this.shop_info = shop_info;
	}
	public int getProduct_count() {
		return product_count;
	}
	public void setProduct_count(int product_count) {
		this.product_count = product_count;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
