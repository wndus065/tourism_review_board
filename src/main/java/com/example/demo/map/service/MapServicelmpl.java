package com.example.demo.map.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.*;
import com.example.demo.map.repository.MapRepository;
@Service
public class MapServicelmpl implements MapService {
	
	@Autowired
	MapRepository mapRepository;

	@Override
	public boolean register(MapDTO dto) {
		String address = dto.getAddress();
		MapDTO getDto = read(address);
		if(getDto != null) {
			System.out.println("이미 등록된 장소입니다.");
			return false;
		}
		MapEntity entity = dtoToEntity(dto);
		mapRepository.save(entity);
		return true;
	}

	@Override
	public MapDTO read(String address) {
		Optional<MapEntity> result = mapRepository.findById(address);
		if(result.isPresent()) {
			MapEntity map = result.get();
			return entityToDto(map);
		}else {
		return null;
	}
}

	@Override
	public void remove(String address) {
		mapRepository.deleteById(address);
		
	}

	@Override
	public void modify(MapDTO dto) {
	Optional<MapEntity> result = mapRepository.findById(dto.getAddress());
	if(result.isPresent()) {
		MapEntity entity = result.get();
		entity.setAddress(dto.getAddress());
		entity.setPlace(dto.getPlace());
		entity.setPoint_x(dto.getPoint_x());
		entity.setPoint_y(dto.getPoint_y());
		mapRepository.save(entity);
	}
		
	}

	@Override
	public Page<MapDTO> getlist(int page) {
		int pageNum = (page==0) ? 0 : page -1;
		
		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("no").descending());
		
		Page<MapEntity> entityPage = mapRepository.findAll(pageable);
		Page<MapDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		
		return dtoPage;
		
	}

	
	

	

	

	
}
