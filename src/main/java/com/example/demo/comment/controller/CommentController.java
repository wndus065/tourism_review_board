package com.example.demo.comment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.comment.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private CommentService service;
	
	@GetMapping("/form")
	public String form() {
		System.out.println("댓글 폼이 출력됐습니다.");
		return "/comment/commentForm";
	}
	
	@GetMapping("/list")
	public String list() {
		System.out.println("댓글 리스트가 출력됐습니다.");
		return "/comment/commentList";
	}

}
