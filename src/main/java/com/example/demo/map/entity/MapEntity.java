package com.example.demo.map.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
@Table(name = "map")

public class MapEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String place_key;
	
	@Column(length = 50 , nullable = false)
	private String place;
	
	@Column(length = 100 , nullable = false)
	private String address;
	
	@Column(nullable = false)
	private double point_x;
	
	@Column(nullable = false)
	private double point_y;
	
	
	
	
}
