package com.example.demo.interest.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.interest.entity.Interest;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;


public interface InterestRepository extends JpaRepository<Interest, Integer> {
	
	
	Page<Interest> findByMemberId(String memberId, Pageable pageable);

	
	Optional<Interest> findByMemberId(String memberId);

	List<Interest> findAllByPlaceBoard(PlaceBoard placeBoard);
	List<Interest> findAllByMember(Member member);
	
	List<Interest> findAllByMemberId(String memberId);
	
}
