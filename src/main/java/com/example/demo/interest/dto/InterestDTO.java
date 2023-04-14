package com.example.demo.interest.dto;


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
public class InterestDTO {
	
	private int interest_no;
	
	private String id;
	
	private int no;
	
	private String place;
	
	private String writer;
	
	private String title;
}
