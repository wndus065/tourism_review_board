package com.example.demo.placeBoard.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;

@Service
public class PlaceBoardServiceImpl implements PlaceBoardService {

	@Autowired
	private PlaceBoardRepository repository;

	@Override
	public int register(PlaceBoardDTO dto) {
		
		System.out.println("DTO----------");
		System.out.println(dto);

		PlaceBoard entity = dtoToEntity(dto);
		System.out.println(entity);
		repository.save(entity);

		return entity.getPlaceNo();
	}

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
			PlaceBoard guestbook = result.get();
			return entityToDto(guestbook);
		} else {
			return null;
		}
	}
	
//	@Override
//	public void 
//	String title = request.getParameter("title");
//	String content = request.getParameter("content");
//	String author = request.getParameter("author");
//	String sql = "INSERT INTO posts (title, content, author) VALUES (?, ?, ?)";
//	PreparedStatement pstmt = conn.prepareStatement(sql);
//	pstmt.setString(1, title);
//	pstmt.setString(2, content);
//	pstmt.setString(3, author);
//	pstmt.executeUpdate();


	@Override
	public void modify(PlaceBoardDTO dto) {
		Optional<PlaceBoard> result = repository.findById(dto.getPlaceNo());
		if (result.isPresent()) {
			PlaceBoard entity = result.get();
			entity.setTitle(dto.getTitle());
			entity.setContent(dto.getContent());
			repository.save(entity);
		}
	}

	@Override
	public void remove(int no) {
		repository.deleteById(no);
	}
}
