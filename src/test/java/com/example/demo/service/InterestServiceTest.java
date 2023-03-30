package com.example.demo.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.repository.InterestRepository;
import com.example.demo.interest.service.InterestService;
import com.example.demo.interest.service.InterestServicelmpl;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

import jakarta.servlet.http.HttpServletRequest;

@SpringBootTest
public class InterestServiceTest {
	
	@Autowired
	InterestService service;

	
	
	@Test
	void 등록하기() {
		service.add("id", 2);
	}
	
	@Test
	void 유저목록조회하기() {			
		Page<InterestDTO> page = service.getList(0,"user1" );
		List<InterestDTO> list = page.getContent();
		for(InterestDTO dto : list) {
			System.out.println(dto);
		}
	}

}
