package com.example.demo.map.service;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.repository.InterestRepository;
import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.*;
import com.example.demo.map.repository.MapRepository;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;

@Service
public class MapServicelmpl implements MapService {

	@Autowired
	private MapRepository mapRepository;
	
	@Autowired
	private PlaceBoardRepository placeRepository;
	
	@Autowired
	private CommentRepository comRepository;
	
	@Autowired
	private InterestRepository interRepository;

	@Override
	public boolean register(MapDTO dto) {				
		String place = dto.getPlace();	
		MapDTO getDto = read(place);
		
		if (getDto != null) {
			System.out.println("이미 등록된 장소입니다.");
			return false;
		}
		MapEntity entity = dtoToEntity(dto);
		mapRepository.save(entity);
		return true;
	}

	@Override
	public void remove(String place) {
		try {
	        mapRepository.deleteById(place);
	        System.out.println("삭제");
	    } catch (EmptyResultDataAccessException e) {
	        System.out.println("삭제할 데이터가 존재하지 않습니다.");
	       
	    }
	}

	@Override
	public void modify(MapDTO dto) {
		Optional<MapEntity> result = mapRepository.findById(dto.getPlace());
		if (result.isPresent()) {
			MapEntity entity = result.get();
			entity.setAddress(dto.getAddress());
			entity.setPlace_key(dto.getPlace_key());
			entity.setPlace(dto.getPlace());
			entity.setPoint_x(dto.getPoint_x());
			entity.setPoint_y(dto.getPoint_y());
			mapRepository.save(entity);
		}

	}

	@Override
	public Page<MapDTO> getlist(int page) {
		int pageNum = (page == 0) ? 0 : page - 1;

		org.springframework.data.domain.Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("place").descending());

		Page<MapEntity> entityPage = mapRepository.findAll(pageable);
		Page<MapDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;

	}

	public List<MapDTO> getAllMarkers() {
		List<MapDTO> markers = new ArrayList<>();
		mapRepository.findAll().forEach(mapEntity -> {
			MapDTO mapDTO = new MapDTO();
			mapDTO.setPoint_x(mapEntity.getPoint_x());
			mapDTO.setPoint_y(mapEntity.getPoint_y());
			mapDTO.setAddress(mapEntity.getAddress());
			mapDTO.setPlace(mapEntity.getPlace());

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

	public List<MapDTO> getMapDTOList() {
		List<MapEntity> mapEntityList = mapRepository.findAll();
		return mapEntityList
				.stream().map(mapEntity -> new MapDTO(mapEntity.getPlace_key(), mapEntity.getPlace(),
						mapEntity.getAddress(), mapEntity.getPoint_x(), mapEntity.getPoint_y()))
				.collect(Collectors.toList());
	}

	
		
	

	@Override
	public MapDTO read(String place) {
		Optional<MapEntity> result = mapRepository.findById(place);
		if(result.isPresent()) {
			MapEntity entity = result.get();
			return entityToDto(entity);
		}else {
			
			return null;	
		}
		
	}

	public List<MapDTO> pickPlace(){
		List<MapEntity> result = mapRepository.findAll();
		List<MapDTO> list = new ArrayList<>();
		for(MapEntity entity : result) {
			MapDTO dto = entityToDto(entity);
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public void delFkMap(String place) {
		List<PlaceBoard> postList = placeRepository.findAllByPlace(MapEntity.builder().place(place).build());
		for(PlaceBoard placeBoard : postList) {
			List<Comment> commentList = comRepository.findAllByPlaceNo(placeBoard);
		    for(Comment comment : commentList) {
		    	comRepository.delete(comment);
		    }

		    List<Interest> interestList = interRepository.findAllByPlaceBoard(placeBoard);
		    for(Interest interest : interestList) {
		    	interRepository.delete(interest);
		    }

		    placeRepository.delete(placeBoard);
		}

	}


}