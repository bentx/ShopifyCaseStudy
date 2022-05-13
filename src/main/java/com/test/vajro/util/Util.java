package com.test.vajro.util;

import java.util.List;

import com.test.vajro.controller.bean.Product;
import com.test.vajro.controller.bean.Products;

public class Util {
	
	public static String pagination(int page, int pageCount, Products productIds)
	{
		List<Product> list = productIds.getProducts();
		String filtered = "";
		int end = page * pageCount;
		int start = end - pageCount;
		for (int i = 0; i < list.size(); i++) {
			if (i > start && i < end) {
				filtered = filtered + (String.valueOf(list.get(i).getId())) + ",";
			}
		}
		return filtered.substring(0, filtered.length() - 2);
	}

}
