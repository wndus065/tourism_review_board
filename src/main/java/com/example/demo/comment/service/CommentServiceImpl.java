package com.example.demo.comment.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.comment.repository.CommentRepository;
import com.example.demo.placeBoard.entity.PlaceBoard;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repository;

	@Override
	public List<CommentDTO> getList(int placeNo) {
	    List<Comment> result = repository.findAllByPlaceNo(PlaceBoard.builder().no(placeNo).build());
	    List<CommentDTO> list = new ArrayList<>();
	    for (Comment entity : result) {
	        CommentDTO dto = entityToDto(entity);
	        list.add(dto);
	    }
	    
	    Collections.sort(list, new Comparator<CommentDTO>() {
	        @Override
	        public int compare(CommentDTO c1, CommentDTO c2) {
	            return c2.getRegDate().compareTo(c1.getRegDate());
	        }
	    });

	    return list;
	}


	@Override
	public CommentDTO read(int no) {
		Optional<Comment> result = repository.findById(no);
		if (result.isPresent()) {
			Comment comment = result.get();
			return entityToDto(comment);
		} else {
			return null;
		}
	}

	@Override
	public int register(CommentDTO dto, String id) {
		dto.setWriter(id);
		Comment entity = dtoToEntity(dto);
		repository.save(entity);

		return entity.getCommentNo();
	}

	@Override
	public void modify(CommentDTO dto) {
		Optional<Comment> result = repository.findById(dto.getCommentNo());
		if (result.isPresent()) {
			Comment entity = result.get();
			entity.setComment(dto.getComment());
			entity.setGrade(dto.getGrade());
			repository.save(entity);
		}
	}

	@Override
	public boolean remove(int no) {
		try {
			System.out.println("댓글을 삭제합니다.");
			repository.deleteById(no);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
