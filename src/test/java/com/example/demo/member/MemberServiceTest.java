package com.example.demo.member;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.service.MemberService;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	private MemberService service;

	@Test
	public void 서비스테스트() {
		System.out.println("--");
	}

	@Test
	public void 일번페이지_목록조회하기() {
		Page<MemberDTO> page = service.getList(1);
		List<MemberDTO> list = page.getContent();
		for (MemberDTO dto : list) {
			System.out.println(dto);
		}
	}

	@Test
	public void 회원등록() {
		for (int i = 0; i < 34; i++) {
			service.register(new MemberDTO("id" + i, "pw" + i, "name" + i, null, "mem" + i + "@aaa.aaa",
					"123456-1234567", null, null, "roleUser"));
		}
	}

}
