package com.example.demo.placeBoard.image.entity;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.placeBoard.image.dto.PlaceBoardImageDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceBoardImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private PlaceBoard placeBoard;

	@Column(nullable = false)
	private String imagePath;

	public PlaceBoardImageDTO toDTO() {
	    return PlaceBoardImageDTO.builder()
	            .id(this.id)
	            .imagePath(this.imagePath)
	            .build();
	}
}