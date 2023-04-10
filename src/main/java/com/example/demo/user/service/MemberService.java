package com.example.demo.user.service;

import org.springframework.data.domain.Page;

import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.entity.Member;

public interface MemberService {
	
	Page<MemberDTO> getList(int pageNumber);
	
	default MemberDTO entityToDto(Member entity) {
		MemberDTO dto = MemberDTO.builder()
				.id(entity.getId())
				.password(entity.getPassword())
				.name(entity.getName())
				.phone(entity.getPhone())
				.email(entity.getEmail())
				.regiNum(entity.getRegiNum())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.role(entity.getRole())
				.build();
		return dto;
	}
	
	// 회원 조회
	MemberDTO read(String id);
	
	boolean register(MemberDTO dto);
	
	default Member dtoToEntity(MemberDTO dto) {
		Member entity = Member.builder()
				.id(dto.getId())
				.password(dto.getPassword())
				.name(dto.getName())
				.phone(dto.getPhone())
				.email(dto.getEmail())
				.regiNum(dto.getRegiNum())
				.role(dto.getRole())
				.build();
		
		return entity;
	}

	void modify(MemberDTO dto);
	void remove(String id);
	
	void delFkMember(String id);

}
