package com.example.demo.requestBoard.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.requestBoard.dto.RequestBoardDTO;
import com.example.demo.requestBoard.entity.RequestBoard;
import com.example.demo.requestBoard.repository.RequestBoardRepository;
import com.example.demo.user.entity.Member;

@Service
public class RequestBoardServiceImpl implements RequestBoardService{
	
	@Autowired
	private RequestBoardRepository repository;
	
	@Override
	public Page<RequestBoardDTO> getList(int page){
		int pageNum = (page ==0)?0:page-1;
		Pageable pageable = PageRequest.of(pageNum, 10,Sort.by("regDate").descending());
		Page<RequestBoard> entityPage = repository.findAll(pageable);
		Page<RequestBoardDTO> dtoPage = entityPage.map(entity -> entityToDto(entity));
		
		return dtoPage;
	}
	
	@Override
	public RequestBoardDTO read(int no) {
		Optional<RequestBoard> result = repository.findById(no);
		if(result.isPresent()) {
			RequestBoard requestBoard = result.get();
			return entityToDto(requestBoard);
		} else {
			return null;
		}
	}
	
	@Override
	public int register (RequestBoardDTO dto) {
		RequestBoard entity = dtoToEntity(dto);
		repository.save(entity);
		
		return entity.getNo();
	}
	
	@Override
	public void delFkReq(String id) {
		List<RequestBoard> reqList = repository.findAllByWriter(Member.builder().id(id).build());
		for(RequestBoard list : reqList) {
			repository.delete(list);
		}
	}
	
}
