package com.test.vajro.controller.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.test.vajro.controller.bean.ShopInfoResponse;

@Document("shop_info")
public class ShopInfoTable {
	@Id
	int id;
	
	ShopInfoResponse shopInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ShopInfoResponse getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(ShopInfoResponse shopInfo) {
		this.shopInfo = shopInfo;
	}

}
