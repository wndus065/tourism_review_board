package com.example.demo.map.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "map")

public class MapEntity {
	@Column
	private String place_key;
	@Id
	@Column(length = 255, nullable = false)
	private String place;

	@Column(length = 255, nullable = false)
	private String address;

	@Column(nullable = false)
	private double point_x;

	@Column(nullable = false)
	private double point_y;

}
