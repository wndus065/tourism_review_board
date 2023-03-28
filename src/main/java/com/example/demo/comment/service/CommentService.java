package com.example.demo.comment.service;

import org.springframework.data.domain.Page;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

public interface CommentService {
	
	default CommentDTO entityToDto(Comment entity) {
		CommentDTO dto = CommentDTO.builder()
				.commentNo(entity.getCommentNo())
				.placeNo(entity.getPlaceNo().getNo())
				.writer(entity.getWriter().getId())
				.comment(entity.getComment())
				.regDate(entity.getRegDate())
				.modDate(entity.getModDate())
				.grade(entity.getGrade())
				.build();
		return dto;
	}
	
	default Comment dtoToEntity(CommentDTO dto) {
		PlaceBoard placeBoard = PlaceBoard.builder()
				.no(dto.getPlaceNo())
				.build();
		
		Member member = Member.builder()
				.id(dto.getWriter())
				.build();
		
		Comment entity = Comment.builder()
				.commentNo(dto.getCommentNo())
				.placeNo(placeBoard)
				.writer(member)
				.comment(dto.getComment())
				.grade(dto.getGrade())
				.build();
		
		return entity;
	}
	
	Page<CommentDTO> getList(int pageNumber);
	
	CommentDTO read(int no);
	
	int register(CommentDTO dto);
	
	void modify(CommentDTO dto);
	void remove(int no);

}
