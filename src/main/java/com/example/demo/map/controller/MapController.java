package com.example.demo.map.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/map")
public class MapController {
	
	private final NaverMapService naverMapService;
	
	public MapController(NaverMapService naverMapService) {
		this.naverMapService = naverMapService;
	}
	
	@GetMapping("/search")
	
	
	
	
	 

}
