package com.mymagic.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mymagic.model.User;


@RestController 
@RequestMapping("/fe4be-controller")
public class Fe4BeController {
	
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public Fe4BeController() 
	{
		log.info(this.getClass() + " Fe4BeController"  );
		
	}
	
	@GetMapping("/hellogetcontroller")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		log.info(this.getClass() + " MysprprjApplication.hello value=" + (name));
		return String.format("Hello %s!", name);
	}
	
	
	@GetMapping("/hellogetuser")
	public User getUser(@RequestBody User name)
	{
		log.info(this.getClass() + " Fe4BeController.hellogetuser" );
		log.info(this.getClass() + " Fe4BeController.getName	=" + (name.getName()));
		log.info(this.getClass() + " Fe4BeController.getPassword=" + (name.getEmail()));
		User u = new User();
		u.setId(11111);
		u.setName(name.getName());
		u.setEmail("aaaa@gmail.com");
				
				;
		log.info(this.getClass() +  " Fe4BeController.hello value=" + (name));
		
		return u;
	}
	
	@PostMapping("/saveData")
	@ResponseBody
	public ResponseEntity<User> saveData(HttpServletRequest request,
	            HttpServletResponse response, 
	            Model model)
	{
	   log.info(this.getClass() + " fe4be-controller saveData:");
	   String requestgetmethod = request.getMethod();
	   log.info(this.getClass() + " fe4be-controller saveData:" + (requestgetmethod));
	   
	   HttpHeaders responseHeaders = new HttpHeaders();
       User user = new User();
	   ResponseEntity<User> re = new ResponseEntity<User> (user, responseHeaders, HttpStatus.OK);
        
	   return re;
	   
	}
	
	
	@PostMapping("/hellopostcontroller")
	public String helloP(@RequestParam(value = "name", defaultValue = "World") String name) 
	{
		log.info(this.getClass() + " MysprprjApplication.hello value=" + (name));
		return String.format("Hello %s!", name);
	}
	
}
