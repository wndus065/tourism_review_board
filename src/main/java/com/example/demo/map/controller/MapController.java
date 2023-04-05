package com.example.demo.map.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.service.MapService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private MapService service;
	
	@GetMapping("/main")	
	public String main(Model model) {
		
	List<MapDTO> markers = service.getAllMarkers();	
		
	model.addAttribute("mapDTOList",markers);
	model.addAttribute("currentPage", "map");
		return "/map/map";
	}
	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0")int page, Model model) {
		Page<MapDTO> list = service.getlist(page);
		model.addAttribute("list",list);
		model.addAttribute("currentPage", "map");
		
	}
	@GetMapping("/read")
	public void read(String place, @RequestParam(defaultValue= "0")int page, Model model) {
		MapDTO dto = service.read(place);
		model.addAttribute("dto",dto);
		model.addAttribute("page",page);
		model.addAttribute("currentPage", "map");
	}
	
	
	@GetMapping("/register")
	public void register() {
		
	}
	
	@PostMapping("/register")
	public String register(MapDTO dto, RedirectAttributes redirectAttributes) {
		boolean isSuccess = service.register(dto);
		if(isSuccess) {
			return "redirect:/map/list";
		}else {
			redirectAttributes.addFlashAttribute("msg","이미 등록된 장소입니다.");
			return "redirect:/map/register";
		}
	}
	
	@GetMapping("/modify")
	public void modify(String place, Model model) {
		MapDTO dto = service.read(place);
		model.addAttribute("dto",dto);
		model.addAttribute("currentPage", "map");
	}
	
	@PostMapping("/modify")
	public String modifyPost(MapDTO dto , RedirectAttributes redirectAttributes) {
		
//		redirectAttributes.addAttribute("place", dto.getPlace());
		service.modify(dto);
		return "redirect:/map/list";
	}
	
	@PostMapping("/remove")
	public String removePost(String place) {
		service.remove(place);
		System.out.println("지움");
		return "redirect:/map/list";
	}
	 

}
