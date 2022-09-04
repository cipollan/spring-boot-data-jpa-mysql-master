package com.mymagic.interceptor;


import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;


@Service
public class HttpInterceptor implements HandlerInterceptor 
{
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	public HttpInterceptor()
	{
		log.info("+------------------- HttpInterceptor ----------------------------+");
	}
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- HttpInterceptor.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() {
		 log.info("+------------------- HttpInterceptor.preDestroy ----------------------------+"); 
	    }

	public ClientHttpResponse intercept(
        HttpRequest request, byte[] body,
        ClientHttpRequestExecution execution) throws IOException 
	{
		log.info("+------------------- HttpInterceptor.intercept ----------------------------+");
		HttpHeaders headers = request.getHeaders();
		 
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		traceResponse(response);
		return response;
	}

private void traceRequest(HttpRequest request, byte[] body) throws IOException {
	log.info("===========================Request begin======================================");
	log.info("URI         : {}", request.getURI());
	log.info("Method      : {}", request.getMethod());
	log.info("Headers     : {}", request.getHeaders() );
	log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
	log.info("==========================Request end=========================================");
}

private void traceResponse(ClientHttpResponse response) throws IOException {
	log.info("============================Response begin====================================");
	log.info("Status code  : {}", response.getStatusCode());
	log.info("Status text  : {}", response.getStatusText());
	log.info("Headers      : {}", response.getHeaders());
	log.info("=======================Response end===========================================");
}

 }
