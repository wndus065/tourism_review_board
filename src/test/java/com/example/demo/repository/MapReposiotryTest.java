package com.example.demo.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.repository.MapRepository;

@SpringBootTest
public class MapReposiotryTest {
	
	@Autowired
	MapRepository repostiory;
	
	@Test
	void 등록() {
		MapEntity map1 = new MapEntity("12312","롯데월드타워","잠실",37.5129146,127.1028611);
		repostiory.save(map1);
	}
		
	@Test
	void 나열() {
		List<MapEntity> list = repostiory.findAll();
		for(MapEntity entity : list) {
			System.out.println(entity);
		}
	}
	

	
}
