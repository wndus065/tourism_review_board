package com.example.demo.comment.service;

import java.util.List;

import com.example.demo.comment.dto.CommentDTO;
import com.example.demo.comment.entity.Comment;
import com.example.demo.placeBoard.dto.PlaceBoardDTO;
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
	
	List<CommentDTO> getList(int placeNo);
	
	CommentDTO read(int no);
	
	int register(CommentDTO dto, String id);
	
//	void modify(CommentDTO dto);
	boolean modify(CommentDTO dto);
	boolean remove(int no);
	
	void delFkCom(int placeNo);
	void delFkComM(String id);

}
