package com.example.demo.map.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@GetMapping("/markers")
	@ResponseBody
	public ResponseEntity<List<ApiEntity>> getMarkers() {
		List<ApiEntity> list = apiService.getList().stream()
				.map(ApiDTO::getRow)
				.flatMap(List::stream)
				.toList();
		return ResponseEntity.ok(list);
	}

}
