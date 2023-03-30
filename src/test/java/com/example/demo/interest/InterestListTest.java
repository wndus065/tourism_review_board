package com.example.demo.interest;

import java.security.Principal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import com.example.demo.interest.service.InterestService;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.service.PlaceBoardService;
import com.example.demo.user.entity.Member;
import com.example.demo.user.service.MemberService;

@SpringBootTest
public class InterestListTest {
	
	@Autowired
	InterestService interestService;
	@Autowired
	MemberService memberService;
	
	@Autowired
	PlaceBoardService placeBoardService;
	
	@Test
	void 테스트() {
	
	}
}
