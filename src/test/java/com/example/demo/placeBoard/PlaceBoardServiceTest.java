package com.example.demo.placeBoard;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.service.PlaceBoardService;


@SpringBootTest
public class PlaceBoardServiceTest {

	@Autowired
	PlaceBoardService service;
	
	@Test
	public void 방명록30개추가() {
		for(int i=0; i<30; i++) {
			service.register(new PlaceBoardDTO(null, i, "둘리", "서울", "서울여행", "참 재미있었다."));	
		}
	}
}
