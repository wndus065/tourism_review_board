package com.example.demo.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.Map;
import com.example.demo.map.repository.MapRepository;



public interface MapService {
	
	MapDTO read(String Place);
	
    
    
    
    
    default MapDTO entityToDto(Map entity) {
    	MapDTO dto = MapDTO.builder()
    			.place_key(entity.getPlace_key())
    			.place(entity.getPlace())
    			.address(entity.getAddress())
    			.point_x(entity.getPoint_x())
    			.point_y(entity.getPoint_y())
    			.build();
    	
    	return dto;
    			
    }
    
    default Map dtoToEntity(MapDTO dto) {
    	Map entity = Map.builder()
    			.place_key(dto.getPlace_key())
    			.place(dto.getPlace())
    			.address(dto.getAddress())
    			.point_x(dto.getPoint_x())
    			.point_y(dto.getPoint_y())
    			.build();
    	return entity;
    }
}
