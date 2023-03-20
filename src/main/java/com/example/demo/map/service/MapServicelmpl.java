package com.example.demo.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.repository.MapRepository;

public class MapServicelmpl implements MapService {
	
	private static final String NAVER_MAP_API_KEY = "ab9cyktgjv";
	private static final int DEFAULT_ZOOM_LEVEL = 10;
	
	@Autowired
	private MapRepository mapRepository;

	@Override
	public List<MapEntity> getAllMapEntity() {
		
		return mapRepository.findAll();
	}

	@Override
	public String getMapUrl() {
		List<MapEntity> attractions = mapRepository.findAll();
		if(attractions == null || attractions.isEmpty()) {
			
		
		return null;
	}
		MapEntity firstMap = attractions.get(0);
		double center_x = firstMap.getPoint_x();
		double center_y = firstMap.getPoint_y();
		String center = String format("%f,%f",center_x , center_y);
		String markers = "";
		for(MapEntity mapEntity : attractions) {
			markers += String.format("%f,%f",attractions.getPoint_x(), attractions.getPoint_y());
			markers += "|";
		}
		String mapUrl = String.format("https://map.naver.com/v5/static/n-sdk-leaflet.html?center=%s&level=%d&marker=%s&key=%s", center, DEFAULT_ZOOM_LEVEL, markers, NAVER_MAP_API_KEY);
		return mapUrl;
	}
}}

}
