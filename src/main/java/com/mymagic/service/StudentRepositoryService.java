package com.mymagic.service;



import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.stereotype.Service;


import com.mymagic.model.Student;
import com.mymagic.repository.StudentRepository;

@Service
public class  StudentRepositoryService  implements StudentRepository
{
	 
	 @Autowired
	 StudentRepository  studentRepository ;
	
	 
		
	public  StudentRepositoryService() {
		super();
		log.info(this.getClass() + "+--------------< Constructor StudentRepositoryService >---------------+"  );
	}
	
	
	@PostConstruct
    private void postConstruct() {
		log.info("+------------------- StudentRepositoryService.postConstruct ----------------------------+");
    }
	
	 @PreDestroy
	 public void preDestroy() {
		 log.info("+------------------- StudentRepositoryService.preDestroy ----------------------------+"); 
	    }


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Override
	public List<Student> findAll() {
		
		log.info(this.getClass() + " StudentRepositoryService.findAll (Begin)"  );
		List<Student> listAll = null;
		try {
			
			  listAll =  studentRepository.findAll();
			 
			 
		} catch (Exception e) {
			log.error(this.getClass() + " EXCEPTION StudentRepositoryService.findAll" + e.getMessage()  ) ;
		}
		
		return listAll; 
	}

	 
	public List<Student> findAll(Sort sort) {
		log.info(this.getClass() + " StudentRepositoryService.findAllsort"  );
		// TODO Auto-generated method stub
		return null;
	}

	 
	public List<Student> findAllById(Iterable<Long> ids) {
		log.info(this.getClass() + " StudentRepositoryService.findAllById"  );
		return null;
	}

	 
	public <S extends Student> List<S> saveAll(Iterable<S> entities)
	{
		log.info(this.getClass() + " StudentRepositoryService.saveAll"  );
		studentRepository.saveAll(entities);
		return (List<S>) entities;
	}

	 
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	 
	public <S extends Student> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public void deleteInBatch(Iterable<Student> entities) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	 
	public Student getOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Student> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Student> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Page<Student> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Student> S save(S entity) {
		studentRepository.save(entity);
		return entity;
	}

	 
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	 
	public void delete(Student entity) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAll(Iterable<? extends Student> entities) {
		// TODO Auto-generated method stub
		
	}

	 
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	 
	public <S extends Student> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public <S extends Student> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	public <S extends Student> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	 
	public Iterable<Student> findByFirstNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Optional<Student> findById(Long b) {
		// TODO Auto-generated method stub
		return null;
	}

	 
	public Iterable<Student> findByNameContaining(String name) {
		// TODO Auto-generated method stub
		return null;
	}
 

}

