package com.example.demo.interest.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.interest.entity.Interest;


public interface InterestRepository extends JpaRepository<Interest, Integer> {
	
	
	Page<Interest> findByMemberId(String memberId, Pageable pageable);

	
	Optional<Interest> findByMemberId(String memberId);


		
	   
	 
	   
	    
}
