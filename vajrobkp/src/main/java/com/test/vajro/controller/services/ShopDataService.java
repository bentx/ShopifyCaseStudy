package com.test.vajro.controller.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.test.vajro.controller.bean.ProductCount;
import com.test.vajro.controller.bean.Products;
import com.test.vajro.controller.bean.ShopInfoData;
import com.test.vajro.controller.bean.ShopInfoResponse;
import com.test.vajro.controller.util.StringUtil;
import com.test.vajro.util.Util;

@Service
public class ShopDataService {

	@Autowired
	RestTemplate rest;

	@Value("${pagecount}")
	private String pageCount;

	@Value("${url.shop}")
	private String shopURL;

	@Value("${url.product}")
	private String productURL;

	@Value("${url.count}")
	private String countURL;

	@Value("${url.accessToken}")
	private String accessToken;

	
	@Cacheable(value = "fetchShopInfo")
	public ShopInfoResponse processRequest(int page) throws RestClientException {
		ShopInfoData shopData = fetchShopInfo();
		ProductCount productCount = fetchProductCount();
		Products productIds = fetchProductId(productCount);
		// fetch ids based on pageNo
		String ids = Util.pagination(page, 10, productIds);
		Products products = fetchProducts(ids);
		ShopInfoResponse response = new ShopInfoResponse();
		response.setShop_info(shopData.getShop());
		response.setProduct_count(productCount.getCount());
		response.setProducts(products.getProducts());
		return response;
	}

	private ShopInfoData fetchShopInfo() throws RestClientException {
		HttpEntity<String> entity = getHeaders();
		return rest.exchange(shopURL, HttpMethod.GET, entity, ShopInfoData.class).getBody();
	}

	private ProductCount fetchProductCount() throws RestClientException {
		Map<String, String> params = new HashMap<String, String>();
		HttpEntity<String> entity =getHeaders();
		return rest.exchange(countURL, HttpMethod.GET, entity, ProductCount.class, params).getBody();

	}



	private Products fetchProductId(ProductCount productCount) throws RestClientException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("fields", "id");
		params.put("limit", String.valueOf(productCount.getCount()));
		HttpEntity<String> entity = getHeaders();
		return rest
				.exchange(productURL + "?limit={limit}&fields={fields}", HttpMethod.GET, entity, Products.class, params)
				.getBody();

	}

	private Products fetchProducts(String ids) throws RestClientException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ids", ids);
		HttpEntity<String> entity = getHeaders();
		return rest.exchange(productURL + "?ids={ids}", HttpMethod.GET, entity, Products.class, params).getBody();

	}
	
	private HttpEntity<String> getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(StringUtil.XshopifyToken, accessToken);
		return new HttpEntity<String>(headers);
		
	}

}
