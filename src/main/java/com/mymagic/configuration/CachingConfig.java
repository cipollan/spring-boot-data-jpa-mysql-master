package com.mymagic.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CachingConfig {

	private  Logger log = LoggerFactory.getLogger(CachingConfig.class);
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- CachingConfig.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() {
		 log.info("+------------------- CachingConfig.preDestroy ----------------------------+"); 
	 }
	
	 
    public CachingConfig() 
    {
        super();
        log.info("+-------------- <CachingConfig Constructor> -----------------------+" );
    }
	
	
    @Bean
    public CacheManager cacheManager() 
    {
    	log.info("+-------------- <CacheManager studentInfo> -----------------------+" );
        return new ConcurrentMapCacheManager("tutorials");
    }
    
   

     
    
    
}
