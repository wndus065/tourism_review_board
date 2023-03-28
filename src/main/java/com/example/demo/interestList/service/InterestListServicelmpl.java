package com.example.demo.interestList.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;

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
	public Page<InterDTO> getList(int pageNumber) {
		int pageNum = (pageNumber == 0) ? 0 : pageNumber -1;
		Pageable pageable = PageRequest.of(pageNum, 10 , Sort.by("interest_no").descending());
		
		Page<Interest> interPage = interListRepository.findAll(pageable);
		Page<InterDTO> InterDto = interPage.map(entity -> entityToDto(entity));
		return InterDto;
	}

	@Override
	public void remove(int interest_no) {
		interListRepository.deleteById(interest_no);
		
		
		
	}

	@Override
	public InterDTO read(int interest_no) {
	Optional<Interest> entity = interListRepository.findById(interest_no);
	if(entity.isPresent()) {
		Interest interest = entity.get();
		InterDTO interDTO = entityToDto(interest);
		return interDTO;
	}
	return null;
	}

	@Override
	public List<InterDTO> find(Member member) {
	 List<Interest> list = interListRepository.findAllById(member.getId());
	 List<InterDTO> dtoList = new ArrayList<>();
	 for(Interest entity : list) {
		 InterDTO interDTO = new InterDTO();
		 interDTO.setInterest_no(entity.getInterest_no());
		 interDTO.setNo(entity.getNo());
		 interDTO.setId(entity.getId());
		 
	 }return dtoList;
	
	}

	@Override
	public void add(PlaceBoard no, Member id) {
		InterDTO dto = new InterDTO();
		dto.setNo(no);
		dto.setId(id);
		Interest interest = dtoToEntity(dto);
		interListRepository.save(interest);
		
		
	}



	

	

}
