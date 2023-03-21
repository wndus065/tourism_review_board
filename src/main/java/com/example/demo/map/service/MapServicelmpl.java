package com.example.demo.map.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.*;
import com.example.demo.map.repository.MapRepository;

public class MapServicelmpl implements MapService {
	
	@Autowired
	MapRepository mapRepository;

	@Override
	public MapDTO read(String Place) {
		// TODO Auto-generated method stub
		return null;
	}

	

	

	
}
