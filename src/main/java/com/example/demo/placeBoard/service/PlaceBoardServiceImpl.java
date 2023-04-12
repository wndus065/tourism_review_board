package com.example.demo.placeBoard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.user.entity.Member;

@Service
public class PlaceBoardServiceImpl implements PlaceBoardService {

	@Autowired
	private PlaceBoardRepository repository;

	@Override
	public int register(PlaceBoardDTO dto) {
		PlaceBoard entity = dtoToEntity(dto);
		System.out.println(entity);
		repository.save(entity);

		return entity.getNo();
	}

//	@Override
//	public boolean register(PlaceBoardDTO dto) {
//		int no = dto.getNo();
//		PlaceBoardDTO getDto = read(no);
//		if(getDto != null) {
//			return false;
//		}
//		PlaceBoard entity = dtoToEntity(dto);
//		repository.save(entity);
//		return true;
//	}
	
	@Override
	public Page<PlaceBoardDTO> getList(int page) {
		int pageNum = (page == 0) ? 0 : page - 1; // page는 index 처럼 0부터 시작
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		Page<PlaceBoard> entityPage = repository.findAll(pageable);
		Page<PlaceBoardDTO> dtoPage = entityPage.map( entity -> entityToDto(entity) );

		return dtoPage;
	}

	@Override
	public PlaceBoardDTO read(int no) {
		Optional<PlaceBoard> result = repository.findById(no);
		if (result.isPresent()) {
			PlaceBoard placeBoard = result.get();
			return entityToDto(placeBoard);
		} else {
			return null;
		}
	}

	@Override
	public void modify(PlaceBoardDTO dto) {
		Optional<PlaceBoard> result = repository.findById(dto.getNo());
		if (result.isPresent()) {
			PlaceBoard entity = result.get();
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			entity.getModDate();
			repository.save(entity);
		}
	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}
	
}
