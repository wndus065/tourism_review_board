package com.example.demo.requestBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.requestBoard.entity.RequestBoard;
import com.example.demo.user.entity.Member;

public interface RequestBoardRepository extends JpaRepository<RequestBoard, Integer>{
	
	List<RequestBoard> findAllByWriter(Member writer);

}
