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
	
	void add(String memberId, int placeBoard);	
	
	void remove(int interest_no);	
	
	Page<InterestDTO> getList(int no,String memberId);
	

	
	
	
	
	
	
	
	default Interest dtoToEntity(InterestDTO dto) {
		Member member = Member.builder().id(dto.getId()).build();
		PlaceBoard placeBoard = PlaceBoard.builder().no(dto.getNo()).place(dto.getPlace()).title(dto.getTitle()).writer(dto.getWriter()).build();
		Interest entity  = Interest.builder()
				.interestNo(dto.getInterest_no())
				.member(member)
				.placeBoard(placeBoard)
				.build();
		return entity;
				
	}
	
	default InterestDTO entityToDto(Interest entity) {
		
		InterestDTO dto = InterestDTO.builder()
				.interest_no(entity.getInterestNo())
				.id(entity.getMember().getId())
				.place(entity.getPlaceBoard().getPlace())
				.writer(entity.getPlaceBoard().getWriter())
				.title(entity.getPlaceBoard().getTitle())
				.no(entity.getPlaceBoard().getNo())
				.build();
		return dto;
	}

	

}
