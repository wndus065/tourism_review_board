package com.example.demo.placeBoard.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.CommentService;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.service.PlaceBoardService;

@Controller
@RequestMapping("/placeboard")
public class PlaceBoardController {

	@Autowired
	private PlaceBoardService service;
	
	@Autowired
	private CommentService commentService;

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
		
		boolean no = service.register(dto);
		redirectAttributes.addFlashAttribute("msg", no);
		return "redirect:/placeboard/list";
	}

	@GetMapping("/read")
	public void read(int no, @RequestParam(defaultValue = "0") int page, Model model) { //파라미터 추가
		PlaceBoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		
//		List<CommentDTO> commentList = commentService.getList(dto.getNo());
//		model.addAttribute("commentList", commentList);
	}

	@GetMapping("/modify")
	public void modify(int no, Model model) {
		PlaceBoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
	}

	@PostMapping("/modify")
	public String modifyPost(PlaceBoardDTO dto, RedirectAttributes redirectAttributes) {
		service.modify(dto);
		redirectAttributes.addAttribute("no", dto.getNo());
		return "redirect:/placeboard/read";
	}

	@GetMapping("/remove") //?no=6
	public String removePost(int no) {
		service.remove(no);
		return "redirect:/placeboard/list";
	}
}
