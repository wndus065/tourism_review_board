package com.example.demo.map.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.service.MapService;

@Controller
@RequestMapping("/map1")
public class MapController {
	
	@Autowired
	private MapService service;
	
	@GetMapping("/list")
	
	public String list(Model model) {
		
		List<MapDTO> markers = service.getAllMarkers();
		
		
		model.addAttribute("mapDTOList",markers);
		return "/map/map";
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String registermap(MapDTO dto, RedirectAttributes redirectAttributes) {
		boolean isSuccess = service.register(dto);
		if(isSuccess) {
			return "redirect:/map/main";
		}else {
			redirectAttributes.addFlashAttribute("msg","이미 등록된 주소입니다.");
			return "redirect:/map/register";
		}
	}
	
	
	
	
	
	
	 

}
