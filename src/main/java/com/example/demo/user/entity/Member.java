package com.example.demo.user.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.interest.entity.Interest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Member extends BaseEntity{
	
	@Id
	@Column(length=50,nullable=false)
	private String id;
	
	@Column(length=255,nullable=false)
	private String password;

	@Column(length=100,nullable=false)
	private String name;

	@Column(length=20,nullable=true)
	private String phone;

	@Column(length=100,nullable=false)
	private String email;

	@Column(length=30,nullable=false)
	private String regiNum;
	
	// 회원 등급
	@Column(length=30,nullable=false)
	private String role;
	
	

}
