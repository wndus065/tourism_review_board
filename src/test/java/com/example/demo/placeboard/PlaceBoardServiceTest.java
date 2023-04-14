package com.example.demo.placeboard;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.user.entity.Member;

@SpringBootTest
public class PlaceBoardServiceTest {

	@Autowired
	PlaceBoardService service;


//	@Test
//	public void 방명록30개추가() {
//		for(int i=0; i<30; i++) {
//			service.register(new PlaceBoardDTO(i, "또치", "서울", "서울", "놀러왔따", null, null));	
//		}
//	}
//	
//	@Test
//	public void 등록() {
//		service.register(new PlaceBoardDTO(0, "id0", "63빌딩", "서울", "놀러왔따", null, null));	
//	}
//	
	

	@Test
	public void 일번페이지_목록조회하기() {
		Page<PlaceBoardDTO> page = service.getList(1);
		List<PlaceBoardDTO> list = page.getContent();
		for(PlaceBoardDTO dto : list) {
			System.out.println(dto);
		}
	}
	
	@Test
	public void 데이터수정() {
		PlaceBoardDTO result = service.read(2);
		result.setContent("내용이수정되었습니다~");
		service.modify(result);	
	}
	
	@Test
	public void 데이터삭제() {
		service.remove(2);
	}
	
	@Test
	public void 콘텐츠데이터검색() {
		List<PlaceBoard> content = service.searchByContent("내용이수정되었습니다~");
	    System.out.println(content);
	}
	
	@Test
	public void 제목검색() {
		List<PlaceBoard> title = service.searchByTitle("asdf");
	    System.out.println(title);
	}
	
	@Test
	public void 장소검색() {
		MapEntity place = MapEntity.builder()
                .place("롯데월드")
                .build();
	
		List<PlaceBoard> place1 = service.searchByPlace(place);
	    System.out.println(place1);
	}	
	
	@Test
	public void 작성자검색() {
		 Member writer = Member.builder()
		            .id("user1")
		            .build();	
		List<PlaceBoard> writer1 = service.searchByWriter(writer);
	    System.out.println(writer1);
	}

}
