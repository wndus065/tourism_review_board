package com.example.demo.interestList.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "interestList")
public class InterestList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int interest_no;
	
	@ManyToOne
	@JoinColumn(name="id")
	private String id;
	@ManyToOne
	@JoinColumn(name="no")
	private int no;
	
	@Column(length = 100 , nullable = false)
	private String title;
	
	
	@Column(length = 500 , nullable = false)
	private String content;

}
