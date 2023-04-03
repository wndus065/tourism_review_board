package com.example.demo.map.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiServiceImpl implements ApiService{
	
	@Autowired
	private ApiRepository repository;
	
	@Override
	public List<ApiDTO> getList(){
		List<ApiEntity> apiEntity = repository.findAll();
		
		return apiEntity.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}
	
	

}
