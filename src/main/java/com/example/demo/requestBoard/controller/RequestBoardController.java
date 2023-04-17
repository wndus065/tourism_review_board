package com.example.demo.requestBoard.controller;

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
import com.example.demo.requestBoard.dto.RequestBoardDTO;
import com.example.demo.requestBoard.service.RequestBoardService;

@Controller
@RequestMapping("/request")
public class RequestBoardController {

	@Autowired
	private RequestBoardService service;

	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<RequestBoardDTO> list = service.getList(page);
		model.addAttribute("list", list);
		model.addAttribute("currentPage", "request");
		System.out.println("전체 페이지 수 : " + list.getTotalPages());
		System.out.println("전체 게시물 수 : " + list.getTotalElements());
		System.out.println("현재 페이지 번호 : " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수 : " + list.getNumberOfElements());
	}

	@GetMapping("/read")
	public void read(int no, @RequestParam(defaultValue = "0") int page, Model model) {
		RequestBoardDTO dto = service.read(no);
		model.addAttribute("dto", dto);
		model.addAttribute("page", page);
		model.addAttribute("currentPage", "request");
	}

	@GetMapping("/register")
	public String register(Principal pricipal, Model model) {
	    String id = pricipal.getName();
	    if (id == null) {
	        // 로그인 되어 있지 않은 경우 로그인 페이지로 리다이렉트
	        return "redirect:/login";
	    }
	    RequestBoardDTO dto = new RequestBoardDTO();
	    dto.setWriter(id);
	    model.addAttribute("dto", dto);
	    model.addAttribute("currentPage", "request");
	    return "/request/register";

	}

	@PostMapping("/register")
	public String registerPost(RequestBoardDTO dto, RedirectAttributes rttr) {
		int no = service.register(dto);
		rttr.addFlashAttribute("result", no);
		return "redirect:/placeboard/list";
	}

}
