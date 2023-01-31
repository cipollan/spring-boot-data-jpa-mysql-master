package com.mymagic.controller;


import java.util.ArrayList;
import org.springframework.cache.annotation.Cacheable;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mymagic.model.Student;
import com.mymagic.service.StudentRepositoryService;



@RestController
@RequestMapping("/StudentController")
public class StudentController {

	@Autowired
	StudentRepositoryService studentRepositoryService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@PostConstruct
    private void postConstruct() 
	{
		log.info("+-----------< StudentController.postConstruct Prova GIT>---------------------+");
		log.info("+-----------< StudentController.postConstruct Prova GIT>---------------------+");
		
    }
	
	 @PreDestroy
	 public void preDestroy() 
	 {
		 log.info("+------------------- StudentController.preDestroy ----------------------------+"); 
	 }


	@GetMapping("/students")
	@Cacheable(value="studentInfo")
	public ResponseEntity<List<Student>> getAllstudents(@RequestParam(required = false) String name) 
	{
		try {
			
			log.info(this.getClass() + " getAllstudents1   value=" + (name));
			List<Student> students = new ArrayList<Student>();
			log.info(this.getClass() + " getAllstudents2   value=" + (name));

			if (name == null)
			{
				log.info(this.getClass() + " getAllstudents3   value=" + (name));
				//studentRepositoryService.findAll().forEach(students::add);
				students = studentRepositoryService.findAll();
				
				for (Student st : students) 
				{
					//log.info(this.getClass() + " getAllstudents3 dentro for=" + (element.getFirstName()));
					//	log.info(this.getClass() + " getAllstudents3 dentro for=");
				    log.info(this.getClass() + " getAllstudentsxxxx dentro for=" + (st.getFirstName()));
			    }
				
				log.info(this.getClass() + " getAllstudents4   value=" + (name));
			}
			else
			{	
				studentRepositoryService.findByFirstNameContaining(name).forEach(students::add);
			}
				

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(students, HttpStatus.OK);
			
		} 
		catch (Exception e) 
		{
			
			log.info(this.getClass() + " EXCEPTION BLOCK getAllstudents  exception:" + (e.getMessage()));
			log.info(this.getClass() + " EXCEPTION BLOCK getAllstudents  exception:" + (e.toString()));
			
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getstudentById(@PathVariable("id") long id) 
	{
		Optional<Student> studentData = studentRepositoryService.findById(id);
		
		log.info(this.getClass() + " getstudentById  value=" + (id));

		if (studentData.isPresent()) {
			return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/create-student")
	public ResponseEntity<Student> createstudent(@RequestBody Student student) 
	{
		log.info(this.getClass() + " createstudent---" + (student.getId()));
		log.info(this.getClass() + " createstudent---" + (student.getFirstName()));
		log.info(this.getClass() + " createstudent---" + (student.getLastName()));
		log.info(this.getClass() + " createstudent---" + (student.getEmailId()));
		
		 
		try
		{
			studentRepositoryService.save(student);
	    } 
	    catch (Exception e) 
	    {
		
		    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.getMessage()));
		    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.toString()));
		
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
		
		 
		return new ResponseEntity<Student>(student, HttpStatus.OK);
		
	}
	
	@PostMapping("/createAllStudent")
	public ResponseEntity<List<Student>> createAllStudent(@RequestBody List<Student> students) 
	{
		log.info(this.getClass() + " createAllStudent   " + (students.size()));
		
		 
		try
		{
			studentRepositoryService.saveAll(students);
	    } 
	    catch (Exception e) 
	    {
		
		    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.getMessage()));
		    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.toString()));
		
		    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	
		
		 
		return new ResponseEntity<List<Student>> (students, HttpStatus.OK);
		
	}

	@PutMapping("/students/{id}")
	public ResponseEntity<Student> updatestudent(@PathVariable("id") long id, 
												@RequestBody Student student) 
	{
		Optional<Student> studentData = studentRepositoryService.findById(id);

		if (studentData.isPresent()) 
		{
			Student _student = studentData.get();
			_student.setEmailId(student.getEmailId());
			_student.setFirstName(student.getFirstName());
			_student.setLastName(student.getLastName());
			
			
			return new ResponseEntity<>(studentRepositoryService.save(_student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/students/{id}")
	public ResponseEntity<HttpStatus> deletestudent(@PathVariable("id") long id) {
		try {
			studentRepositoryService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/Dstudents")
	public ResponseEntity<HttpStatus> deleteAllstudents() 
	{
		try {
			studentRepositoryService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/students/all")
	@Cacheable("tutorials")
	public ResponseEntity<List<Student>> findAll() 
	{
		try {
			List<Student> students = studentRepositoryService.findAll();

			if (students.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(students, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}