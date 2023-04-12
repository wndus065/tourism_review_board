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

	@Query("SELECT pb FROM PlaceBoard pb WHERE pb.title LIKE %:keyword% OR pb.place_place LIKE %:keyword% OR pb.content LIKE %:keyword% OR pb.writer_id LIKE %:keyword%")
	List<PlaceBoard> search(@Param("keyword") String keyword);

	List<PlaceBoard> findByContentContaining(String content);

	List<PlaceBoard> findByPlaceContaining(MapEntity place);

	List<PlaceBoard> findByTitleContaining(String title);

	List<PlaceBoard> findByWriterContaining(Member writer);

}
