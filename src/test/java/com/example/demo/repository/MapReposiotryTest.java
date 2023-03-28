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
		MapEntity map1 = new MapEntity("","롯데월드","잠실",37.511329,127.098574);
		repostiory.save(map1);
		MapEntity map2 = new MapEntity("","63빌딩","여의도",37.519246,126.940945);
		repostiory.save(map2);
		MapEntity map3 = new MapEntity("","잠실구장","잠실",37.512303,127.072819);
		repostiory.save(map3);
	}
		
	@Test
	void 나열() {
		List<MapEntity> list = repostiory.findAll();
		for(MapEntity entity : list) {
			System.out.println(entity);
		}
	}
	

	
}
