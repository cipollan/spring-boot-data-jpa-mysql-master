package com.mymagic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mymagic.configuration.CachingConfig;


@EnableCaching
@EnableJpaRepositories
@SpringBootApplication
public class SpringBootMyMagicApplication 
{
	private static  Logger log = LoggerFactory.getLogger(SpringBootMyMagicApplication.class);
	public static void main(String[] args) 
	{
		log.info("+----------------------------------------------------------------+" );
		log.info("+------ <CacheManager studentInfo Begin Container> --------------+" );
		log.info("+----------------------------------------------------------------+" );
		
		SpringApplication.run(SpringBootMyMagicApplication.class, args);
	}
 
}
