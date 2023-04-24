package com.example.demo.comment.dto;

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
public class CommentDTO {

	private int commentNo;
	private int placeNo;
	private String writer;
	private String comment;
	private LocalDateTime regDate;
	private LocalDateTime modDate;
	private int grade;

}
