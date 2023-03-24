package com.example.demo.requestBoard.dto;

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
public class RequestBoardDTO {
	
	private int no;
	private String writer;
	private String place;
	private String address;
	private String comment;
	
	private LocalDateTime regDate;
	private LocalDateTime modDate;

}
