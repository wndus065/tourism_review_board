package com.example.demo.placeBoard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlaceBoard {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(length = 12, nullable = false)
    private String placeKey;
    
    @Column(nullable = false)
    private int placeNo;
    
    @Column(length = 50, nullable = false)
    private String writer;
    
    @Column(length = 12, nullable = false)
    private String place;
    
    @Column(length = 50, nullable = false)
    private String title;
    
    @Column(length = 500)
    private String content;
	
	

}
