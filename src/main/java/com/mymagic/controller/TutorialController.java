package com.mymagic.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.mymagic.model.Tutorial;
import com.mymagic.repository.TutorialRepository;
import com.mymagic.service.TutorialRepositoryService;


@RestController
@RequestMapping("/TutorialController")
public class TutorialController {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TutorialRepositoryService tutorialRepositoryService;
	
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- TutorialController.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() {
		 log.info("+------------------- TutorialController.preDestroy ----------------------------+"); 
	    }
	 
		@PostMapping("/createtutorial")
		public ResponseEntity<Tutorial> createtutorial(@RequestBody Tutorial tutorial) 
		{
			log.info(this.getClass() + " createtutorial  " + (tutorial.getId()));
			log.info(this.getClass() + " createtutorial  " + (tutorial.getTitle()));
			
			 
			try
			{
				tutorialRepositoryService.save(tutorial);
		    } 
		    catch (Exception e) 
		    {
			
			    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.getMessage()));
			    log.info(this.getClass() + " EXCEPTION BLOCK createstudent  exception:" + (e.toString()));
			
			    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
		
			
			 
			return new ResponseEntity<Tutorial>(tutorial, HttpStatus.OK);
			
		}


	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title) {
		try {
			//@Cacheable("tutorials")
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepositoryService.findAll().forEach(tutorials::add);
			else
				tutorialRepositoryService.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepositoryService.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepositoryService
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepositoryService.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<>(tutorialRepositoryService.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepositoryService.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepositoryService.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepositoryService.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
