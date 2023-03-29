package com.example.demo.interestList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.interestList.dto.InterDTO;

import com.example.demo.interestList.entity.Interest;
import com.example.demo.interestList.repository.InterListRepository;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.repository.PlaceBoardRepository;
import com.example.demo.user.entity.Member;
import com.example.demo.user.repository.MemberRepository;

@Service
public class InterestListServicelmpl implements InterestListService {
	@Autowired
	private InterListRepository interListRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private PlaceBoardRepository placeBoardRepository;

	@Override
	public void addInterest(String id, int no) {
		// 관심목록 추가 로직 구현
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
        PlaceBoard placeBoard = placeBoardRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("Invalid no"));

        Interest interest = new Interest();
        interest.setId(member);
        interest.setNo(placeBoard);

        interListRepository.save(interest);
		
	}

	 @Override
	    public List<PlaceBoard> getInterestList(String id) {
	        // 관심목록 리스트 조회 로직 구현
	        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid id"));
	        List<Interest> interestList = interListRepository.findAll();
	        return interestList.stream().map(Interest::getNo).collect(Collectors.toList());
	    }
	


	

	

	

}
