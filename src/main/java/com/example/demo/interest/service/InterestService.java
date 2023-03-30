package com.example.demo.interest.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.interest.entity.Interest;
import com.example.demo.placeBoard.entity.PlaceBoard;

import com.example.demo.user.entity.Member;

import jakarta.servlet.http.HttpServletRequest;

public interface InterestService {
	
	void add(String memberId, int placeBoard_no);
//	
	void remove(int interestNo);
//	
//	InterestDTO read(int interestNo);
	
	Page<InterestDTO> getList(int pageNumber,String memberId);
	
//	InterestDTO getInterestById(String interestId);
	
	
	
	
	
	
	
	default Interest dtoToEntity(InterestDTO dto) {
		Member member = Member.builder().id(dto.getId()).build();
		PlaceBoard placeBoard = PlaceBoard.builder().no(dto.getNo()).build();
		Interest entity  = Interest.builder()
				.interest_no(dto.getInterest_no())
				.member(member)
				.placeBoard(placeBoard)
				.build();
		return entity;
				
	}
	
	default InterestDTO entityToDto(Interest entity) {
		
		InterestDTO dto = InterestDTO.builder()
				.interest_no(entity.getInterest_no())
				.id(entity.getMember().getId())
				.no(entity.getPlaceBoard().getNo())
				.build();
		return dto;
	}

	

}
