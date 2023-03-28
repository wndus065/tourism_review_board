package com.example.demo.interestList.dto;

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
public class InterDTO {
	private int no;
	
	private String id;
	
	private int place_no;
	
	private String title;
	
	private String content;

}
