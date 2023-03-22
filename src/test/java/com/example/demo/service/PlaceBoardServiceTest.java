package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.service.PlaceBoardService;

@SpringBootTest
public class PlaceBoardServiceTest {

	@Autowired
	PlaceBoardService service;

	@Test
	public void 방명록30개추가() {
		for(int i=0; i<30; i++) {
			service.register(new PlaceBoardDTO(i, "또치", "서울", "서울", "놀러왔따", null, null));	
		}
	}
	
	@Test
	public void 일번페이지_목록조회하기() {
		Page<PlaceBoardDTO> page = service.getList(1);
		List<PlaceBoardDTO> list = page.getContent();
		for(PlaceBoardDTO dto : list) {
			System.out.println(dto);
		}
	}
	
}
