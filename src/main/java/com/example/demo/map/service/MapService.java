package com.example.demo.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.map.api.ApiDTO;
import com.example.demo.map.api.ApiEntity;
import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.repository.MapRepository;



public interface MapService {
	
	boolean register (MapDTO dto);
	
	List<MapDTO> getAllMarkers();
	
	
	MapDTO read(String place);
	
	void remove(String place);
	
	void modify(MapDTO dto);
	
	Page<MapDTO> getlist(int pageNumber);
	
	
    
    default MapDTO entityToDto(MapEntity entity) {
    	MapDTO dto = MapDTO.builder()
    			.place_key(entity.getPlace_key())
    			.place(entity.getPlace())
    			.address(entity.getAddress())
    			.point_x(entity.getPoint_x())
    			.point_y(entity.getPoint_y())
    			.build();
    	
    	return dto;
    			
    }
    
    default MapEntity dtoToEntity(MapDTO dto) {
    	MapEntity entity = MapEntity.builder()
    			.place_key(dto.getPlace_key())
    			.place(dto.getPlace())
    			.address(dto.getAddress())
    			.point_x(dto.getPoint_x())
    			.point_y(dto.getPoint_y())
    			.build();
    	return entity;
    }
    
    default MapDTO apiToMap(ApiDTO apiDTO) {
    	if(apiDTO == null || apiDTO.getRow() == null || apiDTO.getRow().isEmpty()) {
    		return null;
    	}
    	
    	ApiEntity apiEntity = apiDTO.getRow().get(0);
    	
    	return MapDTO.builder()
    			.place(apiEntity.getNmDp())
    			.address(apiEntity.getAddKor())
    			.point_x(apiEntity.getWgs84Y())
    			.point_y(apiEntity.getWgs84X())
    			.place_key(apiEntity.getMainKey())
    			.build();
    }
    
    List<MapDTO> pickPlace();

    void delFkMap(String place);
    
}
