package com.test.vajro;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.google.common.cache.CacheBuilder;

@SpringBootApplication
@EnableCaching
public class VajroApplication {

	public static void main(String[] args) {
		SpringApplication.run(VajroApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

	@Bean
	public CacheManager cacheManager() {
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager() {

			@Override
			protected Cache createConcurrentMapCache(final String name) {
				return new ConcurrentMapCache(name, CacheBuilder.newBuilder()
						.expireAfterWrite(60 * 60, TimeUnit.SECONDS).maximumSize(100).build().asMap(), false);
			}
		};

		cacheManager.setCacheNames(Arrays.asList("fetchShopInfo"));
		return cacheManager;
	}

}
