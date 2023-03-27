package com.example.demo.interestList.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.InterestList;
import com.example.demo.user.entity.Member;

public interface InterestListService {
	
//	void add(Place_no place_no, Member id);
	
	Page<InterDTO> getList(int pageNumber);
	
	void remove(int no);
	
	InterDTO read(int no);
	
	List<InterDTO> find(String id);
	
	
	
	default InterDTO entityToDto(InterestList entity) {
		InterDTO dto = InterDTO.builder()
				.no(entity.getNo())
				.id(entity.getId())
				.place_no(entity.getPlace_no())
				.build();
		return dto;
				
	}
	
	default InterestList dtoToEntity(InterDTO dto) {
		InterestList entity = InterestList.builder()
				.no(dto.getNo())
				.id(dto.getId())
				.place_no(dto.getPlace_no())
				.build();
		return entity;
	}

}
