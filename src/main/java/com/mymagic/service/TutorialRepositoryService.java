package com.mymagic.service;


import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mymagic.model.Tutorial;
import com.mymagic.repository.TutorialRepository;


@Service
public class TutorialRepositoryService implements TutorialRepository 
{
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TutorialRepository  tutorialRepository ;
	

	@Override
	public List<Tutorial> findAll() {
		log.info(this.getClass() + " TutorialRepositoryService.findAll (Begin)"  );
		List<Tutorial> listAll = null;
		try {
			  listAll =  tutorialRepository.findAll();
		} 
		catch (Exception e) 
		{
			log.error(this.getClass() + " EXCEPTION TutorialRepositoryService.findAll" + e.getMessage()  ) ;
		}
		log.info(this.getClass() + " TutorialRepositoryService.findAll (End)"  );
		return listAll; 
	}

	@Override
	public List<Tutorial> findAll(Sort sort) {
		log.info(this.getClass() + " TutorialRepositoryService.findAll (Begin)"  );
		List<Tutorial> listAll = null;
		try {
			  listAll =  tutorialRepository.findAll(sort);
		} catch (Exception e) {
			log.error(this.getClass() + " EXCEPTION TutorialRepositoryService.findAll" + e.getMessage()  ) ;
		}
		
		return listAll; 
	}

	@Override
	public List<Tutorial> findAllById(Iterable<Long> ids) {
		log.info(this.getClass() + " TutorialRepositoryService.findAllById (Begin)"  );
		List<Tutorial> listAll = null;
		try {
			  listAll =  tutorialRepository.findAllById(ids);
		} catch (Exception e) {
			log.error(this.getClass() + " EXCEPTION TutorialRepositoryService.findAll" + e.getMessage()  ) ;
		}
		
		return listAll; 
	}

	@Override
	public <S extends Tutorial> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Tutorial> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Tutorial> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tutorial getOne(Long id) {
		log.info(this.getClass() + " TutorialRepositoryService.getOne (Begin)"  );
		Tutorial tutorial = null;
		try {
			tutorial =  tutorialRepository.getOne(id);
		} catch (Exception e) {
			log.error(this.getClass() + " EXCEPTION TutorialRepositoryService.getOne" + e.getMessage()  ) ;
		}
		
		return tutorial; 
	}

	@Override
	public <S extends Tutorial> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Tutorial> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Tutorial> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Tutorial> S save(S entity) {
		log.info(this.getClass() + " TutorialRepositoryService.save (Begin) " + entity.getId()  );
		tutorialRepository.save(entity);
		log.info(this.getClass() + " TutorialRepositoryService.save (End) " + entity.getId()  );
		return entity;
	}

	@Override
	public Optional<Tutorial> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Tutorial entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Tutorial> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Tutorial> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Tutorial> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Tutorial> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Tutorial> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Tutorial> findByPublished(boolean published) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tutorial> findByTitleContaining(String title) {
		// TODO Auto-generated method stub
		return null;
	}

}
