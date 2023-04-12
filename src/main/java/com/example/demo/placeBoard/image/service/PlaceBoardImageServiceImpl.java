package com.example.demo.placeBoard.image.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.image.dto.PlaceBoardImageDTO;
import com.example.demo.placeBoard.image.entity.PlaceBoardImage;
import com.example.demo.placeBoard.image.repository.PlaceBoardImageRepository;

@Service
public class PlaceBoardImageServiceImpl implements PlaceBoardImageService {
	@Autowired
	private PlaceBoardImageRepository repository;

	@Override
	public void saveImages(PlaceBoard placeBoard, List<String> imagePaths) {
	    List<PlaceBoardImage> images = new ArrayList<>();
	    for (String path : imagePaths) {
	        PlaceBoardImage image = PlaceBoardImage.builder()
	                .placeBoard(placeBoard)
	                .imagePath(path)
	                .build();
	        images.add(image);
	    }
	    repository.saveAll(images);
	}

	@Override
	public List<PlaceBoardImageDTO> getImagesByPlaceBoard(PlaceBoard placeBoard) {
	    List<PlaceBoardImage> images = repository.findAllByPlaceBoard(placeBoard);
	    List<PlaceBoardImageDTO> dtos = new ArrayList<>();
	    for (PlaceBoardImage image : images) {
	        dtos.add(image.toDTO());
	    }
	    return dtos;
	}

}