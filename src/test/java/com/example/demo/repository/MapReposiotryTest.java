package com.example.demo.repository;

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
		MapEntity map1 = new MapEntity("","장소","주소",134.325,223.112);
		repostiory.save(map1);
	}
	

}
