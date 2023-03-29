package com.example.demo.interestList.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.Interest;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.entity.Member;

public interface InterestListService {
	
	void addInterest(String id, int no);
    List<PlaceBoard> getInterestList(String id);
	
	
	
	
	
	
	
	default InterDTO entityToDto(Interest entity) {
		InterDTO dto = InterDTO.builder()
				.interest_no(entity.getInterest_no())
				.id(entity.getId())
				.no(entity.getNo())
				.build();
		return dto;
				
	}
	
	default Interest dtoToEntity(InterDTO dto) {
		Interest entity = Interest.builder()
				.interest_no(dto.getInterest_no())
				.id(dto.getId())
				.no(dto.getNo())
				.build();
		return entity;
	}

	

}
