package com.test.vajro.controller.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.test.vajro.controller.bean.ShopInfoResponse;
import com.test.vajro.controller.entity.ShopInfoTable;

@Repository
public interface ShopInfoRepo extends MongoRepository<ShopInfoTable,Integer>{
	
	public ShopInfoTable findById(int Id);

}
