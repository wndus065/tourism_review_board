package com.example.demo.comment.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.service.CommentService;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Controller
@RestController
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private CommentService service;

	private final Logger logger = LoggerFactory.getLogger(CommentController.class);

	@PostMapping("/register")
	@ResponseBody

	public Map<String, Object> register(CommentDTO commentDto, Principal principal, Model model) {
		

		String id = principal.getName();
		
		model.addAttribute("userId", id);
		
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

	@GetMapping("/list")
	public ResponseEntity<Map<String, Object>> getCommentList(@RequestParam("placeNo") int placeNo) {
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

//	@PutMapping("/update")
//	public ResponseEntity<?> modify(@RequestBody CommentDTO dto) {
//		logger.info("Received request: " + dto);
//		service.modify(dto);
//		return ResponseEntity.ok().build();
//	}
	@PutMapping("/update")
	public ResponseEntity<Map<String, Object>> modify(@RequestBody CommentDTO dto) {
	    Map<String, Object> response = new HashMap<>();
	    boolean isSuccess = service.modify(dto);
	    response.put("success", isSuccess);
	    return ResponseEntity.ok(response);
	}


	@DeleteMapping("/remove")
	public ResponseEntity<Map<String, Object>> remove(int commentNo) {
	    Map<String, Object> response = new HashMap<>();
	    boolean isSuccess = service.remove(commentNo);
	    response.put("success", isSuccess);
	    return ResponseEntity.ok(response);
	}
}
