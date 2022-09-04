/**
 * 
 */
package com.mymagic.repository;

 
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mymagic.model.Student;

@Repository 
public interface StudentRepository extends JpaRepository<Student,Long>
{
	Iterable<Student> findByFirstNameContaining(String name);
	Optional<Student> findById(Long b);
	
}







