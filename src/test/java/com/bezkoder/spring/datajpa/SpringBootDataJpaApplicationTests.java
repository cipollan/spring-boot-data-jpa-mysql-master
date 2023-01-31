package com.bezkoder.spring.datajpa;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = SpringBootDataJpaApplicationTests.class)
class SpringBootDataJpaApplicationTests {

	@Test
	void contextLoads() {
		
		System.out.print("contextLoads");
	}
	
	
	SpringBootDataJpaApplicationTests() 
	{
		
		System.out.print("SpringBootDataJpaApplicationTests ");
		
	}
	
	
	
	

}
