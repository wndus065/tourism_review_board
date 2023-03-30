package com.example.demo.comment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.CommentService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService service;

	@PostMapping("/register")
	@ResponseBody
	public Map<String, Object> register(CommentDTO commentDto, HttpServletRequest request) {
		String id = (String) request.getSession().getAttribute("id");
		commentDto.setWriter(id);
		int commentNo = service.register(commentDto, id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (commentNo == 0) {
			map.put("success", false);
		} else {
			map.put("success", true);
			List<CommentDTO> commentList = service.getList(commentDto.getPlaceNo());
			map.put("data", commentList);
		}

		return map;
	}

	/* 삭제처리 */
	@DeleteMapping("/remove") 
	public Map<String, Boolean> remove(@RequestParam int commentNo) {
		HashMap<String, Boolean> map = new HashMap<String, Boolean>();
		boolean isSuccess = service.remove(commentNo);
		map.put("success", isSuccess);
		 
		return map; //삭제 처리 결과 전달
	}
	
	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getCommentList(@RequestParam("placeNo") int placeNo){
		Map<String, Object> response = new HashMap<>();
		try {
			List<CommentDTO> commentList = service.getList(placeNo);
			response.put("success", true);
			response.put("data", commentList);
		} catch (Exception e) {
			response.put("success", false);
		}
		
		return ResponseEntity.ok(response);
	}
}
