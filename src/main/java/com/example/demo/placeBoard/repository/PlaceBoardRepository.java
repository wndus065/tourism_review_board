package com.example.demo.placeBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.map.entity.MapEntity;

import com.example.demo.placeBoard.dto.PlaceBoardDTO;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

public interface PlaceBoardRepository extends JpaRepository<PlaceBoard, Integer> {

	List<PlaceBoard> findAllByWriter(Member writer);
	List<PlaceBoard> findAllByPlace(MapEntity place);

	List<PlaceBoard> findByContentContaining(@Param("keyword") String content);

	List<PlaceBoard> findByPlace(@Param("place") MapEntity place);
		
	List<PlaceBoard> findByTitleContaining(@Param("keyword") String title);

	List<PlaceBoard> findByWriter(@Param("writer") Member writer);

}
