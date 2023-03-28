package com.example.demo.interestList.controller;

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

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.Interest;
import com.example.demo.interestList.service.InterestListService;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.user.entity.Member;
import com.example.demo.user.service.MemberService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/inter")
public class InterController {
	
	@Autowired
	private InterestListService interesListService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private PlaceBoardService placeBoardService;
	
	@GetMapping("/list")
	public String getList(@RequestParam(name = "page", defaultValue = "1")int pageNumber, Model model) {
		Page<InterDTO> interList = interesListService.getList(pageNumber);
		model.addAttribute("list", interList);
		return "interboard/list";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("interest_no") int interest_no) {
		interesListService.remove(interest_no);
		return "redirect:/interboard/list";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("interest_no") int interest_no,Model model) {
		InterDTO interDTO = interesListService.read(interest_no);
		model.addAttribute("interDTO", interDTO);
		return "interboard/read";
	}
	@GetMapping("/find")
	public String find(@RequestParam("id")Member member, Model model) {
		List<InterDTO> interList = interesListService.find(member);
		model.addAttribute("interList", interList);
		return "interboard/find";
	}
	@PostMapping("/add")
	public String add(@RequestParam("interest_no")PlaceBoard placeBoard, HttpSession session) {
		Member member = (Member)session.getAttribute("member");
		interesListService.add(placeBoard, member);
		return "redirect:/inter/list";
	}

}
