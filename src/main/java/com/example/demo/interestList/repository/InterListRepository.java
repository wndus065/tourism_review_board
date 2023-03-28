package com.example.demo.interestList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.Interest;
import com.example.demo.user.entity.Member;

public interface InterListRepository extends JpaRepository<Interest, Integer> {

	List<Interest> findAllById(String id);

	
	
	
	   
	 
	   
	    
}
