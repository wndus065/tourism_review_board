package com.example.demo.placeBoard.service;

import org.springframework.data.domain.Page;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

public interface PlaceBoardService {

	int register(PlaceBoardDTO dto);

	Page<PlaceBoardDTO> getList(int no);


	PlaceBoardDTO read(int no);

	void modify(PlaceBoardDTO dto);

	void remove(int no);

	default PlaceBoardDTO entityToDto(PlaceBoard entity) {

		PlaceBoardDTO dto = PlaceBoardDTO.builder()
				.no(entity.getNo())
				.writer(entity.getWriter().getId())
				.place(entity.getPlace().getPlace())
				.title(entity.getTitle())
				.content(entity.getContent())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.imgPath(entity.getImgPath())
				.build();

		return dto;
	}

	default PlaceBoard dtoToEntity(PlaceBoardDTO dto) {
		Member member = Member.builder()
				.id(dto.getWriter()).build();
		MapEntity mapEntity = MapEntity.builder()
				.place(dto.getPlace()).build();
		PlaceBoard entity = PlaceBoard.builder()
				.no(dto.getNo())
				.writer(member)
				.place(mapEntity)
				.title(dto.getTitle())
				.content(dto.getContent())
				.build(); // 값 변경
		return entity;
	}
	void delFkPost(String writerNo);
	void delFkPostP(String place);
	
}
