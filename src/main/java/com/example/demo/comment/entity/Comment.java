package com.example.demo.comment.entity;

import com.example.demo.placeBoard.entity.PlaceBoard;
import com.example.demo.user.entity.BaseEntity;
import com.example.demo.user.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
@Entity
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentNo;

	@ManyToOne
	private PlaceBoard placeNo;

	@ManyToOne
	private Member writer;

	@Column(length = 200, nullable = false)
	private String comment;

	@Column(nullable = false)
	private int grade;

}
