package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;


@SpringBootTest
public class PlaceBoardRepositoryTest {

	@Autowired
	PlaceBoardRepository repository;
	
	@Test
	public void 글_30건등록() {
		for(int i=0; i<30; i++) {
			PlaceBoard placeboard = new PlaceBoard();
			repository.save(placeboard);	
		}
	}
	
	@Test
	public void 등록() {
		PlaceBoard placeboard = new PlaceBoard(0, "id2", "63빌딩", "서울", "서울에 갔다");
		repository.save(placeboard);	
	}
	
	@Test
	public void 데이터단건조회() {
		Optional<PlaceBoard> result = repository.findById(1);
		if(result.isPresent()) {
			PlaceBoard placeboard = result.get();
			System.out.println(placeboard);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<PlaceBoard> list = repository.findAll();
		for(PlaceBoard placeboard : list) {
			System.out.println(placeboard);
		}
	}
	
	@Test
	public void 데이터수정() {
		Optional<PlaceBoard> result = repository.findById(1);
		PlaceBoard placeboard = result.get();
		placeboard.setContent("내용이수정되었습니다~");
		repository.save(placeboard);	
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteById(1);
	}

}
