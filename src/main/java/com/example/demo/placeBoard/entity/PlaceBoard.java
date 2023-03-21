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
    private int placeNo;
    
    @Column(length = 50, nullable = false) //글쓴이
    private String writer;
    
    @Column(length = 12, nullable = false) //장소
    private String place; 
    
    @Column(length = 50, nullable = false) //글제목
    private String title;
    
    @Column(length = 500) //글내용
    private String content;
	
	

}
