package com.example.demo.map.api;

import java.util.List;

public interface ApiService {
	
	default ApiDTO entityToDto(ApiEntity entity) {
		if(entity == null) {
			return null;
		}
		
		List<ApiEntity> row = List.of(entity);
		
		return ApiDTO.builder()
				.listTotalCount(1)
				.result(new ApiDTO.Result("INFO-000","정상처리 되었습니다."))
				.row(row)
				.build();
	}
	
	default ApiEntity dtoToEntity(ApiDTO dto) {
		if(dto == null||dto.getRow()==null||dto.getRow().isEmpty()) {
			return null;
		}
		
		return dto.getRow().get(0);
	}
	
	List<ApiDTO> getList();
//	void modify(ApiDTO dto);
//	void remove(String mainKey);
//	void register(ApiDTO dto);
	
}
