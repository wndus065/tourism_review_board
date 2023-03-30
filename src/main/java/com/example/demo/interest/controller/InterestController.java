package com.example.demo.interest.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.repository.InterestRepository;
import com.example.demo.interest.service.InterestService;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;
import com.example.demo.user.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/interboard")
public class InterestController {
	
	@Autowired
	private InterestService interestService;
	@Autowired
	private InterestRepository interestRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PlaceBoardRepository placeBoardRepository;
	@Autowired
	private PlaceBoardService placeBoardService;
	
	@GetMapping("/list")
	public String getInterestList(@RequestParam(defaultValue ="1")int pageNumber , Model model, HttpServletRequest request) {
		Page<InterestDTO> interestList = interestService.getList(pageNumber, request);
		model.addAttribute("interestList", interestList);
		return "interestList";
	}

   
   
 

}
