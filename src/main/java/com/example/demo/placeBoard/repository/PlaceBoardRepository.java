package com.example.demo.placeBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.placeBoard.entity.PlaceBoard;

public interface PlaceBoardRepository extends JpaRepository<PlaceBoard, Integer> {

}
