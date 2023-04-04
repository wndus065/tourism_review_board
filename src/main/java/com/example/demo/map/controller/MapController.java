package com.example.demo.map.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.demo.map.api.ApiDTO;
import com.example.demo.map.api.ApiEntity;
import com.example.demo.map.api.ApiService;
import com.example.demo.map.dto.MapDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.map.service.MapService;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	private MapService service;
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/main")	
	public String main(Model model) {

		List<ApiDTO> apiList = apiService.getList();
	List<MapDTO> markers = service.getAllMarkers();	
		
	List<MapEntity> mapEntities = new ArrayList<>();
	for (ApiDTO apiDTO : apiList) {
	    for (ApiEntity apiEntity : apiDTO.getRow()) {
	        // 각 Entity의 변수를 가져와 MapEntity에 저장
	        MapEntity mapEntity = new MapEntity();
	        mapEntity.setPlace(apiEntity.getNameKor());
	        mapEntity.setAddress(apiEntity.getAddKor());
	        mapEntity.setPoint_x(apiEntity.getWgs84Y());
	        mapEntity.setPoint_y(apiEntity.getWgs84X());
	        // MapEntity 리스트에 추가
	        mapEntities.add(mapEntity);
	    }
	}
	
	model.addAttribute("apiList", apiList);
	model.addAttribute("mapDTOList",markers);
		return "/map/map";
	}
	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0")int page, Model model) {
		Page<MapDTO> list = service.getlist(page);
		model.addAttribute("list",list);
		
		
	}
	@GetMapping("/read")
	public void read(String place, @RequestParam(defaultValue= "0")int page, Model model) {
		MapDTO dto = service.read(place);
		model.addAttribute("dto",dto);
		model.addAttribute("page",page);
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
