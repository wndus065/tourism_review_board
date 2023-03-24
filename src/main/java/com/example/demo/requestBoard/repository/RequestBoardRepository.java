package com.example.demo.requestBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.requestBoard.entity.RequestBoard;

public interface RequestBoardRepository extends JpaRepository<RequestBoard, Integer>{

}
