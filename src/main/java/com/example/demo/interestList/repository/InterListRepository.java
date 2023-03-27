package com.example.demo.interestList.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.interestList.entity.InterestList;

public interface InterListRepository extends JpaRepository<InterestList, Integer> {
	List<InterestList> findById(String id);
}
