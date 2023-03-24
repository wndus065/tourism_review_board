package com.example.demo.requestBoard.entity;

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

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestBoard extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int no;
	
	@ManyToOne
	private Member writer;
	
	@Column(length=50,nullable=false)
	private String place;
	
	@Column(length=100,nullable=false)
	private String address;
	
	@Column(length=200,nullable=false)
	private String comment;

}
