package com.mymagic.interceptor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Service
public class MyInterceptor implements HandlerInterceptor  
{
	private  Logger log = LoggerFactory.getLogger(this.getClass());

	public MyInterceptor() 
	{
		log.info("+-------------- MyInterceptor Constructor  -----------------+ ");
	}
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- MyInterceptor.postConstruct ----------------------------+");
    }
	
	@PreDestroy
	public void preDestroy() {
		 log.info("+------------------- MyInterceptor.preDestroy ----------------------------+"); 
	}
	
	@Override
	public boolean preHandle(
	  HttpServletRequest request,
	  HttpServletResponse response, 
	  Object handler) throws Exception 
	{
	    log.info("MyInterceptor.[preHandle][request:" + request + "]" + "[Methodç:" + request.getMethod()
	      + "]" + "[GetRequestUri:" +  request.getRequestURI() + "][Parameters:" + getParameters(request));
	    
	    return true;
	}
	
	@Override
	public void postHandle(
	  HttpServletRequest request, 
	  HttpServletResponse response,
	  Object handler, 
	  ModelAndView modelAndView) throws Exception {
	    
	    log.info("MyInterceptor.[postHandle][" + request + "]");
	    traceResponse(response);
	    
	}
	
	@Override
	public void afterCompletion(
	  HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) 
	  throws Exception 
	{
	    if (ex != null){
	        ex.printStackTrace();
	    }
	    
	    log.info("MyInterceptor.[afterCompletion][" + request + "][exception: " + ex + "]");
	}
	
	

	
	
	private String getParameters(HttpServletRequest request) {
	    StringBuffer posted = new StringBuffer();
	    Enumeration<?> e = request.getParameterNames();
	    log.info("MyInterceptor.[getParameters][" + request.getMethod() + "]");
	    if (e != null) {
	        posted.append("?");
	    }
	    while (e.hasMoreElements()) {
	        if (posted.length() > 1) {
	            posted.append("&");
	        }
	        String curr = (String) e.nextElement();
	        posted.append(curr + "=");
	        if (curr.contains("password") 
	          || curr.contains("pass")
	          || curr.contains("pwd")) {
	            posted.append("*****");
	        } else {
	            posted.append(request.getParameter(curr));
	            
	        }
	        log.info("[getParameters][" + request.getParameter(curr) + "]");
	    }
	    String ip = request.getHeader("X-FORWARDED-FOR");
	    String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
	    if (ipAddr!=null && !ipAddr.equals("")) {
	        posted.append("&_psip=" + ipAddr); 
	        log.info("[getParameters][" + ipAddr + "]");
	    }
	    return posted.toString();
	}

	private String getRemoteAddr(HttpServletRequest request) {
	    String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
	    if (ipFromHeader != null && ipFromHeader.length() > 0) {
	        log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
	        return ipFromHeader;
	    }
	    return request.getRemoteAddr();
	}
	
	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		log.info("===========================Request begin======================================");
		log.info("URI         : {}", request.getURI());
		log.info("Method      : {}", request.getMethod());
	    log.info("Headers     : {}", request.getHeaders() );
	    log.info("Request body: {}", new String(body, StandardCharsets.UTF_8));
	    log.info("==========================Request end=========================================");
	}
	
	
	private void traceRequest(HttpServletRequest request, byte[] body) throws IOException {
		log.info("===========================Request begin======================================");
		log.info("URI         : {}", ((HttpRequest) request).getURI());
		log.info("Method      : {}", request.getMethod());
	    log.info("Headers     : {}", request.getHeaders(null) );
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
	
	private void traceResponse(HttpServletResponse response) throws IOException {
		log.info("======================Response begin==========================================");
		log.info("Status code       : {}", response.getStatus());
		log.info("response.toString : {}", response.toString());
		log.info("=======================Response end===========================================");
	}

	

}
