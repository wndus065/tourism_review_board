package com.example.demo.map.service;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		double point_x = dto.getPoint_x();
		double point_y = dto.getPoint_y();
		MapDTO getDto = read(point_x ,point_y);
		if(getDto != null) {
			System.out.println("이미 등록된 장소입니다.");
			return false;
		}
		MapEntity entity = dtoToEntity(dto);
		mapRepository.save(entity);
		return true;
	}

//	@Override
//	public MapDTO read(double point_x, double point_y) {
//		Optional<MapEntity> result = mapRepository.findById(point_x, point_y);
//		if(result.isPresent()) {
//			MapEntity map = result.get();
//			return entityToDto(map);
//		}else {
//		return null;
//	}
//}

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

	
	public List<MapDTO> getAllMarkers() {
		List<MapDTO> markers = new ArrayList<>();
		mapRepository.findAll().forEach(mapEntity ->{
			MapDTO mapDTO = new MapDTO();
			mapDTO.setPoint_x(mapEntity.getPoint_x());
			mapDTO.setPoint_y(mapEntity.getPoint_y());
			
			markers.add(mapDTO);
		});
		return markers;
	}
	
	public void saveMarker(MapDTO mapDTO) {
		MapEntity mapEntity = new MapEntity();
		mapEntity.setPoint_x(mapDTO.getPoint_x());
		mapEntity.setPoint_y(mapDTO.getPoint_y());
		mapEntity.setAddress(mapDTO.getAddress());
	
		mapRepository.save(mapEntity);
	}

	@Override
	public MapDTO read(double point_x, double point_y) {
		
		return null;
	}
	


    public List<MapDTO> getMapDTOList() {
        List<MapEntity> mapEntityList = mapRepository.findAll();
        return mapEntityList.stream()
                .map(mapEntity -> new MapDTO(mapEntity.getPlace_key(),mapEntity.getPlace(),mapEntity.getAddress(),mapEntity.getPoint_x(), mapEntity.getPoint_y()))
                .collect(Collectors.toList());
    }

	

	
	

	

	

	
}
