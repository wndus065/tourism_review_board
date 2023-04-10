package com.example.demo.placeBoard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.interest.dto.InterestDTO;
import com.example.demo.map.entity.MapEntity;
import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

public interface PlaceBoardRepository extends JpaRepository<PlaceBoard, Integer> {
//	@Query(value = "select p.place, p.writer, p.title from Place_board p left join interest_list i on p.no = i.no where i.id = :id", nativeQuery = true)
//	List<ObjectDTO> getPlace(@Param("id") String memberId);
	
	List<PlaceBoard> findAllByWriter(Member writer);
	List<PlaceBoard> findAllByPlace(MapEntity place);

}
