package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.repository.InterestRepository;



import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class InterestReposiotryTest {
	
	@Autowired
	InterestRepository repostiory;
	@Autowired
	MemberRepository memberRepository;
	@Autowired
	PlaceBoardRepository placeBoardRepository;
	
	
	@Test
	void 전체조회() {
		List<Interest> list = repostiory.findAll();
		for(Interest interest : list) {
			System.out.println(interest);
		}
					
	}
	
	@Test
	void 등록() {
		Member member1 = new Member("user1","pw","name","mem","123@123.com","123","roleUser");
		PlaceBoard placeBoard = new PlaceBoard(0,"id","ww","ww","ww");
		
		memberRepository.save(member1);
		placeBoardRepository.save(placeBoard);
		
		Interest enInterest = new Interest(0,member1,placeBoard);
		repostiory.save(enInterest);		
	}
	@Test
	void 테스트() {
		
		Page<Interest> page = repostiory.findByMemberId("id",pageable);
		System.out.println();
	}
		
	
}
