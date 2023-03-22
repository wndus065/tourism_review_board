package com.example.demo.placeBoard.dto;

import java.time.LocalDateTime;

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

	private int no;
	private String writer;
	private String place;
    private String title;
    private String content;
    private LocalDateTime regDate;
    private LocalDateTime modDate;
    
    
}
