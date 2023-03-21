package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.service.MemberService;

@Controller
//@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@GetMapping("/member/list")
	public void list(@RequestParam(defaultValue = "0") int page, Model model) {
		Page<MemberDTO> list = service.getList(page);
		model.addAttribute("list", list);
		System.out.println("전체 페이지 수 : " + list.getTotalPages());
		System.out.println("전체 게시물 수 : " + list.getTotalElements());
		System.out.println("현재 페이지 번호 : " + (list.getNumber() + 1));
		System.out.println("페이지에 표시할 게시물 수 : " + list.getNumberOfElements());
	}

}
