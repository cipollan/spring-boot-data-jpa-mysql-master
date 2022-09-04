package com.mymagic.configuration;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {
	
	
	private  Logger log = LoggerFactory.getLogger(SwaggerConfiguration.class);
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- SwaggerConfiguration.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() 
	 {
		 log.info("+---------------  SwaggerConfiguration.preDestroy ----------------------------+"); 
	 }
	
	 
    public SwaggerConfiguration() 
    {
        super();
        log.info("+-------------- <SwaggerConfiguration Constructor> -----------------------+" );
    }

    

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.emmecilab.players.controllers"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Players REST API",
                "Players REST API Documentation",
                "1.0",
                "Terms of service",
                "mcicolella@libero.it",
                "GPL v2 Licence",
                "http://www.gnu.org/licenses/old-licenses/gpl-2.0.html");
        return apiInfo;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}
