package com.example.demo.map.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiServiceImpl implements ApiService{
	
	@Autowired
	private ApiRepository repository;
	
	@Override
	public List<ApiDTO> getList(){
		
		String serviceKey = "4855574d5a736976353052516e6842";
		
		try {
		RestTemplate restTemplate = new RestTemplate();
	    String url = "http://openapi.seoul.go.kr:8088/" + serviceKey + "/json/SebcTourStreetKor/1/134/";
	    ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
	    String responseBody = response.getBody();
	    
	    // 받아온 데이터를 파싱하여 DB에 저장
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode rootNode = objectMapper.readTree(responseBody);
	    JsonNode rowNode = rootNode.get("SebcTourStreetKor").get("row");
	    List<ApiEntity> entityList = new ArrayList<>();
	    for (JsonNode itemNode : rowNode) {
	        ApiEntity entity = objectMapper.treeToValue(itemNode, ApiEntity.class);
	        entityList.add(entity);
	    }
	    repository.saveAll(entityList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	    
		List<ApiEntity> apiEntity = repository.findAll();
		
		return apiEntity.stream()
				.map(this::entityToDto)
				.collect(Collectors.toList());
	}
	
	@Override
	public void modify(ApiDTO dto) {
		ApiEntity updateEntity = dtoToEntity(dto);
		String mainKey = updateEntity.getMainKey();
		
		repository.findById(mainKey).ifPresent(entity -> {
			entity.setNmDp(updateEntity.getNmDp());
			entity.setAddKor(updateEntity.getAddKor());
			entity.setHKorCity(updateEntity.getHKorCity());
			entity.setNmDp(updateEntity.getNmDp());
            entity.setKorAlias(updateEntity.getKorAlias());
            entity.setNameKor(updateEntity.getNameKor());
            entity.setAddKor(updateEntity.getAddKor());
            entity.setLawSido(updateEntity.getLawSido());
            entity.setLawSgg(updateEntity.getLawSgg());
            entity.setLawHemd(updateEntity.getLawHemd());
            entity.setHKorCity(updateEntity.getHKorCity());
            entity.setHKorGu(updateEntity.getHKorGu());
            entity.setHKorDong(updateEntity.getHKorDong());
            entity.setWgs84X(updateEntity.getWgs84X());
            entity.setWgs84Y(updateEntity.getWgs84Y());
            
            repository.save(entity);
		});
	}
	
	@Override
	public void remove(String mainKey) {
		repository.deleteById(mainKey);
	}
	
	@Override
	public void register(ApiDTO dto) {
		ApiEntity newEntity = dtoToEntity(dto);
		String mainKey = newEntity.getMainKey();
		
		Optional<ApiEntity> existingEntity = repository.findById(mainKey);
		if(existingEntity.isPresent()) {
			ApiEntity entityToUpdate = existingEntity.get();
			entityToUpdate.setNmDp(newEntity.getNmDp());
	        entityToUpdate.setAddKor(newEntity.getAddKor());
	        entityToUpdate.setHKorCity(newEntity.getHKorCity());
	        entityToUpdate.setKorAlias(newEntity.getKorAlias());
	        entityToUpdate.setNameKor(newEntity.getNameKor());
	        entityToUpdate.setLawSido(newEntity.getLawSido());
	        entityToUpdate.setLawSgg(newEntity.getLawSgg());
	        entityToUpdate.setLawHemd(newEntity.getLawHemd());
	        entityToUpdate.setHKorGu(newEntity.getHKorGu());
	        entityToUpdate.setHKorDong(newEntity.getHKorDong());
	        entityToUpdate.setWgs84X(newEntity.getWgs84X());
	        entityToUpdate.setWgs84Y(newEntity.getWgs84Y());
	        repository.save(entityToUpdate);
		} else {
			repository.save(newEntity);
		}
	}

}
