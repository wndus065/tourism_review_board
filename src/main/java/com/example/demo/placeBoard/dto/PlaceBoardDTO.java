package com.example.demo.placeBoard.dto;

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
@Builder
public class PlaceBoardDTO {

	private int placeNo;
	private String writer;
	private String place;
    private String title;
    private String content;
    
    
}
