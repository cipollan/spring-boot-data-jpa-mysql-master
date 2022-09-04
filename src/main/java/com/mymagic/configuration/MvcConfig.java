package com.mymagic.configuration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

 
import com.mymagic.interceptor.HttpInterceptor;
import com.mymagic.interceptor.MyInterceptor;
 

@EnableCaching   
@EnableWebMvc
@Configuration
@ComponentScans(value = { @ComponentScan("com.mymagic.repository"),
							@ComponentScan("com.mymagic.interceptor"),
							@ComponentScan("com.mymagic.controller"),
							@ComponentScan("com.mymagic.model") })
@EnableJpaRepositories
public class MvcConfig implements WebMvcConfigurer {
	
	private  Logger log = LoggerFactory.getLogger(MvcConfig.class);
	
	@Autowired 
	MyInterceptor myInterceptor;
	
	@Autowired 
	HttpInterceptor myHttpInterceptor;

	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- MvcConfig.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() {
		 log.info("+------------------- MvcConfig.preDestroy ----------------------------+"); 
	 }
	
	 
    public MvcConfig() 
    {
        super();
        log.info("+-------------- <MvcConfig Constructor> -----------------------+" );
    }

  
    @Override
    public void addInterceptors(final InterceptorRegistry registry) 
    {
    	log.info("+-------------- <MvcConfig addInterceptors> --------------------+" );
        registry.addInterceptor(myInterceptor);
        registry.addInterceptor(myHttpInterceptor);
    }
    
}

