package com.mymagic.controller;



import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

 


@RestController
@RequestMapping("/ServiceDemoMainController")
public class ServiceDemoMainController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public ServiceDemoMainController() 
	{ 
		log.info(this.getClass() + " ServiceDemoMainController "  );
	}
	
	@GetMapping("/fish")
	@ResponseBody
	public Map<String,Integer> fish() 
	{
		HashMap<String,Integer> myMap = new HashMap<String,Integer>();
		myMap.put("Pike",2);
		myMap.put("Trout",4);
		myMap.put("Bass",4);
		 
		return myMap;
	}
	
	@GetMapping("/hello")
	public String helloP() 
	{
		String name = "GcpCourse";
		 
		return String.format("Hello %s!", name);
	}
	
}
