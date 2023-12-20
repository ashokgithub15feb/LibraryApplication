package com.greenlearner.configs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {

	private static final Logger logger = LoggerFactory.getLogger(CacheConfig.class);
	
	//Customizer cache
	CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
		return new ConcurrentCustomizer();
	}
	
	class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

		@Override
		public void customize(ConcurrentMapCacheManager cacheManager) {
			cacheManager.setAllowNullValues(false);
			cacheManager.setStoreByValue(true);
			logger.info(String.format("Customizer Invoked!!"));
		}
		
	}
}
