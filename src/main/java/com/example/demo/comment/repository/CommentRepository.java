package com.example.demo.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.comment.entity.Comment;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

public interface CommentRepository extends JpaRepository<Comment, Integer>{
	 List<Comment> findAllByPlaceNo(PlaceBoard placeNo);
	 List<Comment> findAllByWriter(Member writer);
}
