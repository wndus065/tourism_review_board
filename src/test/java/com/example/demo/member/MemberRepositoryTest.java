package com.example.demo.member;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;


@SpringBootTest
public class MemberRepositoryTest {

	@Autowired
	MemberRepository repository;
	
	@Test
	public void 회원등록() {
		for(int i=0;i<30;i++) {
			Member member = new Member("id"+i, "1234", "id"+i, "123-4567-8901", "aa@aaaa.aaa", "", "admin");
			repository.save(member);
		}
	}
	
	
	@Test
	public void 데이터단건조회() {
		Optional<Member> result = repository.findById("user1");
		if(result.isPresent()) {
			Member member = result.get();
			System.out.println(member);
		}
	}
	
	@Test
	public void 데이터전체조회() {
		List<Member> list = repository.findAll();
		for(Member member : list) {
			System.out.println(member);
		}
	}
	
	@Test
	public void 데이터수정() {
		Optional<Member> result = repository.findById("user1");
		Member member = result.get();
		member.setName("또치");
		repository.save(member);	
	}
	
	@Test
	public void 데이터삭제() {
//		repository.deleteById("user1"); //게시글테이블에서 참조하고 있으면 회원을 삭제할 수 없음
		repository.deleteAll();
	}

}
