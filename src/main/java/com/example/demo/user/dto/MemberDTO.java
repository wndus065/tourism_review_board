package com.example.demo.user.dto;

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
public class MemberDTO {

	private String id;
	private String password;
	private String name;
	private String phone;
	private String email;
	private String regiNum;

	private LocalDateTime regDate;
	private LocalDateTime modDate;

	private String role;

}
