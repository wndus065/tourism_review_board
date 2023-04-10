package com.example.demo.interest.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.interest.entity.Interest;
import com.example.demo.interest.repository.InterestRepository;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class InterestServicelmpl implements InterestService {
	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private PlaceBoardRepository placeBoardRepository;

	@Override
	public void add(String memberId, int placeBoardNo) {
		Member member = memberRepository.findById(memberId).orElse(null);
		PlaceBoard placeBoard = placeBoardRepository.findById(placeBoardNo).orElse(null);
		if (member == null || placeBoard == null) {
			System.out.println("일치하지 않는 정보입니다.");
			throw new IllegalArgumentException("Invalid member or placeBoard");
		}
		Interest entity = Interest.builder().member(member).placeBoard(placeBoard).build();
		interestRepository.save(entity);
	}

	@Override
	public void remove(int interest_no) {
		interestRepository.deleteById(interest_no);
	}

	@Override
	public Page<InterestDTO> getList(int page, String memberId) {

		int pageNum = (page == 0) ? 0 : page - 1;
		Pageable pageable = PageRequest.of(pageNum, 10, Sort.by("interestNo").descending());
		Page<Interest> entityPage = interestRepository.findByMemberId(memberId, pageable);
		Page<InterestDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		return dtoPage;
	}

	@Override
	public void delFkInter(int placeNo) {
		List<Interest> list = interestRepository.findAllByPlaceBoard(PlaceBoard.builder().no(placeNo).build());
		for (Interest delList : list) {
			interestRepository.delete(delList);
		}
	}

	@Override
	public void delFkInterM(String id) {
		List<Interest> list = interestRepository.findAllByMember(Member.builder().id(id).build());
		for (Interest delList : list) {
			interestRepository.delete(delList);
		}
	}
	
	@Override
	public boolean checkInter(String memberId) {
	    List<Interest> interList = interestRepository.getUsersInterList(memberId);
	    if (interList.isEmpty()) {
	        return false;
	    }
	    Member member = interList.get(0).getMember();
	    boolean interested = interList.stream().anyMatch(interest -> interest.getPlaceBoard().getWriter().getId().equals(member.getId()));
	    return interested;
	}


}