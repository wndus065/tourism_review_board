package com.example.demo.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.service.MapService;

@SpringBootTest
public class MapServiceTest {
	@Autowired
	MapService service;
	
	@Test
	public void test() {
		boolean isSuccess = service.register(new MapDTO("서울","장소","주소",1.156,167.123));
		if(isSuccess) {
			System.out.println("등록되었습니다");
		}else {
			System.out.println("이미 등록된 주소입니다.");
		}
	}

}
