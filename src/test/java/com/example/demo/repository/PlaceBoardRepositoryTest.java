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
			PlaceBoard placeboard = new PlaceBoard(i, "둘리", "서울", "서울에 간다", "놀러갔다");
			repository.save(placeboard);	
		}
	}
	
//	@Test
//	public void 없는아이디로_글등록() {
//		Member member = Member.builder().id("user2").build();
//		placeboard placeboard = new placeboard(0,"1번글","내용입니다", member); //회원테이블에 없는 아이디를 사용하면 에러가 발생됨
//		repository.save(placeboard);	
//	}
//	
//	@Test
//	public void 있는아이디로_글등록() {
//		Member member1 = Member.builder().id("user1").build();
//		repository.save(new PlaceBoard(0,"1번글","내용입니다", member1));
//		repository.save(new PlaceBoard(0,"2번글","내용입니다", member1));
//		repository.save(new PlaceBoard(0,"3번글","내용입니다", member1));	
//		
//		Member member2 = Member.builder().id("user2").build(); //먼저 회원테이블에 데이터를 추가해야함.
//		repository.save(new PlaceBoard(0,"4번글","내용입니다", member2));	
//		repository.save(new PlaceBoard(0,"5번글","내용입니다", member2));	
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
		Optional<PlaceBoard> result = repository.findById(5);
		PlaceBoard placeboard = result.get();
		placeboard.setContent("내용이수정되었습니다~");
		repository.save(placeboard);	
	}
	
	@Test
	public void 데이터삭제() {
		repository.deleteById(1);
//		repository.deleteAll();
	}

}
