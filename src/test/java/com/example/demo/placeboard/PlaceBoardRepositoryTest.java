package com.example.demo.placeboard;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.user.entity.Member;


@SpringBootTest
public class PlaceBoardRepositoryTest {

	@Autowired
	PlaceBoardRepository repository;
	
		
//	@Test
//	public void 등록() {
//		Member member1 = Member.builder().id("id20").build();
//		MapEntity mapEntity1 = MapEntity.builder().place("잠실구장").build();
//		PlaceBoard placeboard = new PlaceBoard(0, member1, mapEntity1, "서울여행", "서울여행 63빌딩에 갔다");
//		repository.save(placeboard);	
//	}
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
		repository.deleteById(3);
	}
	
//	@Test
//	public void 콘텐츠검색() {
//		List<PlaceBoard> result = repository.findByContentContaining("asdf");
//		System.out.println(result);
//	}
//	
//	@Test
//	public void 장소검색() {
//		MapEntity place = MapEntity.builder()
//                .place("롯데월드")
//                .build();
//		List<PlaceBoard> result2 = repository.findByPlace(place);
//		System.out.println(result2);
//	}
//	
//		
//	@Test
//	public void 작성자검색() {
//	    Member writer = Member.builder()
//	            .id("user1")
//	            .build();
//
//	    List<PlaceBoard> result = repository.findByWriter(writer);
//	    System.out.println(result);
//	}

}
