package com.example.demo.interestList.controller;

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

import com.example.demo.interestList.dto.InterDTO;
import com.example.demo.interestList.entity.Interest;
import com.example.demo.interestList.service.InterestListService;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.user.entity.Member;
import com.example.demo.user.service.MemberService;

import jakarta.servlet.http.HttpServletRequest;
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
    public String getInterestList(Model model, Member member) {
        String id = member.getId(); // 현재 로그인한 사용자의 아이디를 가져옵니다.
        List<PlaceBoard> interestList = interesListService.getInterestList(id); // 해당 사용자가 등록한 관심목록을 가져옵니다.
        model.addAttribute("interestList", interestList); // 모델에 관심목록을 담아서 뷰에 전달합니다.
        return "interest/list";
    }
   

}
