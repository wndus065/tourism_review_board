package com.example.demo.placeBoard.service;

import org.springframework.data.domain.Page;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;

public interface PlaceBoardService {

	int register(PlaceBoardDTO dto);
	
	Page<PlaceBoardDTO> getList(int pageNumber);

	PlaceBoardDTO read(int placeNo);

	void modify(PlaceBoardDTO dto);

	void remove(int placeNo);
	
	default PlaceBoard dtoToEntity(PlaceBoardDTO dto) {
		
		PlaceBoard entity = PlaceBoard.builder()
				.placeNo(dto.getPlaceNo())
				.writer(dto.getWriter())
				.place(dto.getPlace())
				.title(dto.getTitle())				
				.content(dto.getContent())
				.build(); //값 변경
		return entity;
	}

	default PlaceBoardDTO entityToDto(PlaceBoard entity) {
		
		PlaceBoardDTO dto = PlaceBoardDTO.builder()
				.placeNo(entity.getPlaceNo())
				.writer(entity.getWriter())
				.place(entity.getPlace())
				.title(entity.getTitle())				
				.content(entity.getContent())
				.build();

		return dto;
	}
	
}
