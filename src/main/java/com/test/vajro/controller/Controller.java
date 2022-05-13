package com.test.vajro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.test.vajro.abstraction.ResponseModel;
import com.test.vajro.bean.Response;
import com.test.vajro.controller.bean.ShopInfoResponse;
import com.test.vajro.controller.entity.ShopInfoTable;
import com.test.vajro.controller.repository.ShopInfoRepo;
import com.test.vajro.controller.services.ShopDataService;
import com.test.vajro.controller.util.StringUtil;

@RestController
public class Controller {

	@Autowired
	RestTemplate rest;

	@Autowired
	ShopDataService dataService;

	@Autowired
	ShopInfoRepo repo;

	@GetMapping("/shopinfo/{page}")
	public ResponseModel fetchdata(@PathVariable @NonNull int page) {
		ResponseModel resp = null;
		try {
			resp = dataService.processRequest(page);
		} catch (RestClientException e) {
			resp = new Response(StringUtil.Failure, StringUtil.RequestFailure);
		}
		return resp;
	}

	@PostMapping("/shopinfo/{page}")
	public ResponseModel saveShopInfo(@PathVariable @NonNull int page,
			@RequestBody @NonNull ShopInfoResponse shopInfo) {
		ResponseModel resp;
		try {
			ShopInfoTable infoTable = new ShopInfoTable();
			infoTable.setId(page);
			infoTable.setShopInfo(shopInfo);
			repo.save(infoTable);
			resp = new Response(StringUtil.Success, StringUtil.DBSuccess);
		} catch (IllegalArgumentException e) {
			resp = new Response(StringUtil.Failure, StringUtil.DBFailure);
		}
		return resp;
	}
	
	@GetMapping("/getpage/{page}")
	public ResponseModel getShopInfo(@PathVariable @NonNull int page) {
		ResponseModel resp;
		try {
			ShopInfoTable infoTable=repo.findById(page);
			if(infoTable!=null) resp = infoTable.getShopInfo();
			else resp= new Response(StringUtil.Failure, StringUtil.EmptyRecord);	
		} catch (IllegalArgumentException e) {
			resp = new Response(StringUtil.Failure, StringUtil.DBFailure);
		}
		return resp;
	}
}
