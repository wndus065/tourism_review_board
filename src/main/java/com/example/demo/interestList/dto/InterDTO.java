package com.example.demo.interestList.dto;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.Member;

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
	private int interest_no;
	
	private Member id;
	
	private PlaceBoard no;
	

}
