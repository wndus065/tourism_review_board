package com.example.demo.interestList.service;

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
import com.example.demo.interestList.entity.InterestList;
import com.example.demo.interestList.repository.InterListRepository;
import com.example.demo.user.entity.Member;

@Service
public class InterestListServicelmpl implements InterestListService {
	@Autowired
	 InterListRepository interListRepository;
	
	public InterestListServicelmpl(InterListRepository interListRepository) {
		this.interListRepository = interListRepository;
	}

//	@Override
//	public void add(Place_no place_no, Member id) {
//		InterestList interestList = new InterestList();
//		interestList.setPlace_no(place_no);
//		interestList.setId(id);
//		interListRepository.save(interestList);
//		
//	}

	@Override
	public Page<InterDTO> getList(int pageNumber) {
		int pageNum = (pageNumber == 0) ? 0 : pageNumber -1;
		Pageable pageable = PageRequest.of(pageNum, 10 , Sort.by("no").descending());
		
		Page<InterestList> interPage = interListRepository.findAll(pageable);
		Page<InterDTO> InterDto = interPage.map(entity -> entityToDto(entity));
		return InterDto;
	}

	@Override
	public void remove(int no) {
		interListRepository.deleteById(no);
		
		
		
	}

	@Override
	public InterDTO read(int no) {
		Optional<InterestList> result = interListRepository.findById(no);
		if(result.isPresent()) {
			InterestList entity = result.get();
			return entityToDto(entity);
		}else {
		return null;
		}
	}

	@Override
	public List<InterDTO> find(String id) {
		// TODO Auto-generated method stub
		return null;
	}


	

	

}
