package com.example.demo.requestBoard.service;

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
	
//	외래키 설정 이전의 register
//	@Override
//	public boolean register(RequestBoardDTO dto) {
//		int no = dto.getNo();
//		RequestBoardDTO getDto = read(no);
//		if(getDto != null) {
//			return false;
//		}
//		RequestBoard entity = dtoToEntity(dto);
//		
//		repository.save(entity);
//		return true;
//	}

	
//	외래키 적용 register
	@Override
	public int register (RequestBoardDTO dto) {
		RequestBoard entity = dtoToEntity(dto);
		repository.save(entity);
		
		return entity.getNo();
	}
}
