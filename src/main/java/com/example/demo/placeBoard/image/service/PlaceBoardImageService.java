package com.example.demo.placeBoard.image.service;

import java.util.List;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.image.dto.PlaceBoardImageDTO;

public interface PlaceBoardImageService {
	void saveImages(PlaceBoard placeBoard, List<String> imagePaths);

	List<PlaceBoardImageDTO> getImagesByPlaceBoard(PlaceBoard placeBoard);

}