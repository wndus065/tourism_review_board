package com.example.demo.interest.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	


	@GetMapping("/list")
	public String getInterestList(@RequestParam(defaultValue = "1") int page, Model model,
			HttpServletRequest request) {
		String memberId = (String) request.getSession().getAttribute("id");
		if (memberId == null) {
			System.out.println("로그인정보가 없습니다.");
		}
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("interestNo").descending());
		Page<InterestDTO> interestList = interestService.getList(page, memberId);
		model.addAttribute("list", interestList);
		return "interboard/list";
	}
	@GetMapping("/add")
	public void add() {
		
	}
	@PostMapping("/add")
	public String add(@RequestParam("no") Integer placeBoardNo, RedirectAttributes attributes, HttpServletRequest request) {
	    String memberId = (String) request.getSession(false).getAttribute("id");
	    if (memberId == null) {
	        return "redirect:/login";
	    }
	    try {
	        interestService.add(memberId, placeBoardNo);
	        attributes.addFlashAttribute("msg", "관심 목록에 추가되었습니다.");
	        return "redirect:/placeboard/read?no=" + placeBoardNo;
	    } catch (IllegalArgumentException e) {
	        return "redirect:/placeboard/list";
	    }
	
		
	}
	@PostMapping("/remove")
	public String remove(int interest_no) {
		interestService.remove(interest_no);
		return "redirect:/interboard/list";
	}
	
	
}

   
   
 


