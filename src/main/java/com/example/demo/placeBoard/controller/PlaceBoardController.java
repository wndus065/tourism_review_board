package com.example.demo.placeBoard.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.service.PlaceBoardService;

@Controller
@RequestMapping("/placeboard")
public class PlaceBoardController {

	@Autowired
	private PlaceBoardService service;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) { //파라미터 추가
		Page<PlaceBoardDTO> list = service.getList(page);
		model.addAttribute("list", list);	
		System.out.println("전체 페이지 수: " + list.getTotalPages());
		System.out.println("전체 게시물 수: " + list.getTotalElements());
		System.out.println("현재 페이지 번호: " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수: " + list.getNumberOfElements());
	}

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String registerPost(PlaceBoardDTO dto, RedirectAttributes redirectAttributes, Principal principal) {
		
		int placeNo = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", placeNo);
		return "redirect:/placeboard/list";
	}

	@GetMapping("/read")
	public void read(int placeNo, @RequestParam(defaultValue = "0") int page, Model model) { //파라미터 추가
		PlaceBoardDTO dto = service.read(placeNo);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
	}

	@GetMapping("/modify")
	public void modify(int placeNo, Model model) {
		PlaceBoardDTO dto = service.read(placeNo);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(PlaceBoardDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("placeNo", dto.getPlaceNo());
		return "redirect:/placeboard/read";
	}

	@PostMapping("/remove")
	public String removePost(int placeNo) {
		service.remove(placeNo);
		return "redirect:/placeboard/list";
	}
}
