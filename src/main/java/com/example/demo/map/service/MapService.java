package com.example.demo.map.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.repository.MapRepository;



public interface MapService {
	
	List<MapEntity> getAllMapEntity();
    
    String getMapUrl();

}
