package com.example.demo.placeBoard.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.image.entity.PlaceBoardImage;

public interface PlaceBoardImageRepository extends JpaRepository<PlaceBoardImage, Integer> {
	List<PlaceBoardImage> findAllByPlaceBoard(PlaceBoard placeBoard);

}