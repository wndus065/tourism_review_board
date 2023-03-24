package com.example.demo.requestBoard.service;

import org.springframework.data.domain.Page;

import com.example.demo.requestBoard.dto.RequestBoardDTO;
import com.example.demo.requestBoard.entity.RequestBoard;
import com.example.demo.user.entity.Member;

public interface RequestBoardService {
	
	Page<RequestBoardDTO> getList(int pageNumber);
	
	default RequestBoardDTO entityToDto(RequestBoard entity) {
		RequestBoardDTO dto = RequestBoardDTO.builder()
				.no(entity.getNo())
//				.id(entity.getId())
				.writer(entity.getWriter().getId())
				.place(entity.getPlace())
				.address(entity.getAddress())
				.comment(entity.getComment())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.build();
		return dto;
	}
	
	RequestBoardDTO read(int no);
	
	int register(RequestBoardDTO dto);
	
	default RequestBoard dtoToEntity(RequestBoardDTO dto) {
		Member member = Member.builder()
				.id(dto.getWriter())
				.build();
		RequestBoard entity = RequestBoard.builder()
				.writer(member)
				.place(dto.getPlace())
				.address(dto.getAddress())
				.comment(dto.getComment())
				.build();
		
		return entity;
	}

}
