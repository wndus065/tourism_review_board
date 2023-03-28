package com.example.demo.interestList.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.InterestList;
import com.example.demo.user.entity.Member;

public interface InterestListService {
	
	void add(int no, Member id);
	
	Page<InterDTO> getList(int pageNumber);
	
	void remove(int interest_no);
	
	InterDTO read(int interest_no);
	
	List<InterDTO> find(String id);
	
	
	
	default InterDTO entityToDto(InterestList entity) {
		InterDTO dto = InterDTO.builder()
				.interest_no(entity.getInterest_no())
				.id(entity.getId())
				.no(entity.getNo())
				.build();
		return dto;
				
	}
	
	default InterestList dtoToEntity(InterDTO dto) {
		InterestList entity = InterestList.builder()
				.interest_no(dto.getInterest_no())
				.id(dto.getId())
				.no(dto.getNo())
				.build();
		return entity;
	}

}
