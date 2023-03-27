package com.example.demo.interestList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.service.InterestListService;

@Controller
@RequestMapping("/interboard")
public class InterController {
	
	@Autowired
	private InterestListService service;
	
	
//	@GetMapping("/myinter")
//	public String myinter(Model model) {
//		List<InterDTO> interDto = ser)
//	}

}
