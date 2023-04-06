package com.example.demo.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.user.dto.MemberDTO;
import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberRepository repository;

	@Override
	public Page<MemberDTO> getList(int page) {
		int pageNum = (page == 0) ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("regDate").descending());
		Page<Member> entityPage = repository.findAll(pageable);
		Page<MemberDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));

		return dtoPage;
	}

	@Override
	public MemberDTO read(String id) {
		Optional<Member> result = repository.findById(id);
		if (result.isPresent()) {
			Member member = result.get();
			return entityToDto(member);
		} else {
			return null;
		}
	}

	@Override
	public boolean register(MemberDTO dto) {
		String id = dto.getId();
		MemberDTO getDto = read(id);
		if (getDto != null) {
			return false;
		}
		Member entity = dtoToEntity(dto);
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashpassword = passwordEncoder.encode(entity.getPassword());
		entity.setPassword(hashpassword);
		
		repository.save(entity);
		return true;
	}
	
	@Override
	public void modify(MemberDTO dto) {
		Optional<Member> result = repository.findById(dto.getId());
		if(result.isPresent()) {
			Member entity = result.get();
			entity.setPassword(dto.getPassword());
			entity.setPhone(dto.getPhone());
			entity.setEmail(dto.getEmail());
			System.out.println(entity.toString());
			repository.save(entity);
		}
	}
	
	@Override
	public void remove(String id) {
		System.out.println(id+"회원을 삭제합니다.");
		repository.deleteById(id);
	}

}
